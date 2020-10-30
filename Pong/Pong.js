class Vec {
    //For the width and height
    //To scale up mostly everything in this program
    constructor(x = 0, y = 0) {
        this.x = x;
        this.y = y;
    }
}

class Rect {
    //For the width and height
    constructor(w, h) {
        this.pos = new Vec; //position
        this.size = new Vec(w, h); //size of objects
    }
    get left() {
        //Postion (x) width - Size of Width / 2;
        return this.pos.x - this.size.x / 2; //Get to left
    }
    get right() {
        //Postion (x) width + Size of Width / 2;
        return this.pos.x + this.size.x / 2; //Get to right
    }
    get top() {
        //Postion (y) height - Size of height / 2;
        return this.pos.y - this.size.y / 2; //Get to top
    }
    get bottom() {
        //Postion (y) height + Size of height / 2;
        return this.pos.y + this.size.y / 2; //Get to bottom
    }
}

class Ball extends Rect {
    constructor() {
        super(10, 10);  //size of the ball
        this.vel = new Vec;
    }
}

class Player extends Rect {
    constructor() {
        super(20, 100); //size of the player board
        this.score = 0;
    }
}

class Pong {
    constructor(canvas) {
        this._canvas = canvas;
        this._context = canvas.getContext('2d');

        this.ball = new Ball;
        this.ball.pos.x = 100;
        this.ball.pos.y = 50;

        this.ball.vel.x = 100;
        this.ball.vel.y = 100;

        this.players = [
            new Player,
            new Player,
        ]

        this.players[0].pos.x = 40; //Computer
        this.players[1].pos.x = this._canvas.width - 40;    //User
        this.players.forEach(player => {
            player.pos.y = this._canvas.height / 2; //Up and down
        });


        let lastTime;

        const callback = (millis) => {
            if (lastTime) {
                this.update((millis - lastTime) / 1000);
            }
            lastTime = millis;
            requestAnimationFrame(callback);
        };
        callback();
    }

    collide(player, ball) {
        if (player.left < ball.right && player.right > ball.left
            && player.top < ball.bottom & player.bottom > ball.top) {
            ball.vel.x = -ball.vel.x;
        }
    }

    draw() {

        this._context.fillStyle = '#000';
        this._context.fillRect(0, 0,
            this._canvas.clientWidth, this._canvas.height);

        this.drawRect(this.ball);
        this.players.forEach(player => this.drawRect(player));
    }

    drawRect(rect) {

        this._context.fillStyle = '#fff';
        this._context.fillRect(rect.left, rect.top,
            rect.size.x, rect.size.y);
    }

    update(dt) {
        this.ball.pos.x += this.ball.vel.x * dt;
        this.ball.pos.y += this.ball.vel.y * dt;

        if (this.ball.left < 0 || this.ball.right > this._canvas.width) {
            // if (this.ball.vel.x < 0) {
            //     playerId = 1;
            // } else {
            //     playerId = 0;
            // }

            //All of the above into this one line
            let playerId = this.ball.vel.x < 0 | 0;

            console.log(playerId);
            this.ball.vel.x = -this.ball.vel.x;
        }

        if (this.ball.top < 0 || this.ball.bottom > this._canvas.height) {
            this.ball.vel.y = -this.ball.vel.y;
        }

        this.players[1].pos.y = this.ball.pos.y - 50;
        // this.players[0].pos.y = this.ball.pos.y;
        this.players.forEach(player => this.collide(player, this.ball));

        this.draw();
    }

}

const canvas = document.getElementById('pong');
// const context = canvas.getContext('2d');

const pong = new Pong(canvas);

canvas.addEventListener('mousemove', event => {
    pong.players[0].pos.y = event.offsetY;
})