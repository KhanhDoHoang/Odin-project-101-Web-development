const app = () => {
    var counter = 0;
    var timeleft = 10;

    function convertSeconds(s) {
        var min = floor(s / 60);
        var sec = s % 60;
        return nf(min, 2) + ':' + nf(sec, 2);
    }

    var ding;

    function preload() {
        ding = loadSound("ding.mp3");
    }
}

app();