// Carousel set up, imported from unpkg
// Repo: https://github.com/wethegit/wtc-controller-carousel
const carouselEl = document.querySelector(".carousel");
const myCarousel = new window.wtcControllerCarousel(carouselEl);
const reduceMotion = window.matchMedia("(prefers-reduced-motion: reduce)")
  .matches;
if (reduceMotion) myCarousel.paused = true;

// Fancy accelerate effect, you don't need any of this to work with the Carousel
// Tween Repo: https://github.com/wethegit/wtc-tween
const tween = window.wtcTween.default; // tween library
const bars = document.querySelector(".bars");
const acceleratebtn = document.querySelector(".accelerate-btn");
let stepD = 0; // used to calculate frames
let raf = null; // to save our animation
let state = false; // to save our acceleration state
let animating = false; /// to save the tween state

// frame animation
const step = function (now, i) {
  // each 2 seconds call the createNewObject() function
  if (!stepD || now - stepD >= 144) {
    stepD = now;
    myCarousel.offset = i * -500;
  }
  raf = requestAnimationFrame((d) => step(d, i + 1 > 3 ? 0 : i + 1));
};

const accelerateToggle = function () {
  if (animating || reduceMotion) return; // don't do anything if in the middle of a tween
  animating = true;
  bars.classList.toggle("is-active"); // toggle bars

  // accelerate! 🚀
  if (!state) {
    acceleratebtn.innerHTML = "Stop!";
    tween(
      [0, 0, 2],
      [50, 250, 144],
      (val) => {
        const [leftPercent, leftOffset, speed] = val;

        // this helps center the carousel
        carouselEl.style.left = `calc(${leftPercent}% - ${leftOffset}px)`;
        // oh yeah! ⚡️
        myCarousel.speed = speed;
      },
      {
        duration: 3000,
        onComplete: () => {
          // pause and reset carousel
          myCarousel.paused = true;
          myCarousel.offset = 0;
          // trigger stepped animation
          raf = requestAnimationFrame((d) => step(d, 0));
          animating = false;
        },
      }
    );
  }
  // decelerate 🙁
  else {
    acceleratebtn.innerHTML = "Accelerate";
    // cancel our steps
    cancelAnimationFrame(raf);
    // reset carousel
    myCarousel.speed = 2;
    myCarousel.paused = false;

    // nice and smooth centering
    tween(
      [50, 250],
      [0, 0],
      (val) => {
        const [leftPercent, leftOffset] = val;

        carouselEl.style.left = `calc(${leftPercent}% - ${leftOffset}px)`;
      },
      {
        duration: 3000,
        onComplete: () => {
          animating = false;
        },
      }
    );
  }

  // update state
  state = !state;
};

acceleratebtn.addEventListener("click", accelerateToggle);
