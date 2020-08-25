function Bubble(x, y) {
  this.x = x;
  this.y = y;
  this.col = color(255, 100);

  this.display = function () {
    strock(255);
    fill(this.col);
    ellipse(this.x, this.y, 60, 60);
  };

  this.clicked = function () {
    this.col = color(255, 0, 200);
  };

  this.move = function () {
    this.x = this.x + random(-1, 1);
    this.y = this.y + random(-1, 1);
  };
}
