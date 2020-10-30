<<<<<<< HEAD
var play = document.querySelector(".play");
var pause = document.querySelector(".pause");
var btn = document.querySelector(".app");

btn.addEventListener("click", () => {
  if (play.classList.contains("active")) {
    play.classList.remove("active");
    pause.classList.add("active");
  } else {
    pause.classList.remove("active");
    play.classList.add("active");
  }
});
=======
var play = document.querySelector(".play");
var pause = document.querySelector(".pause");
var btn = document.querySelector(".app");

btn.addEventListener("click", () => {
  if (play.classList.contains("active")) {
    play.classList.remove("active");
    pause.classList.add("active");
  } else {
    pause.classList.remove("active");
    play.classList.add("active");
  }
});
>>>>>>> 768ede7a50cd743b4119394c60644fd61d2544db
