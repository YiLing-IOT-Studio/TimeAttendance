var canvas = document.querySelector("#canvas");
var WIDTH = document.body.scrollWidth,
    HEIGHT = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
canvas.setAttribute("width", WIDTH);
canvas.setAttribute("height", HEIGHT);
canvas.width = WIDTH;
canvas.Height = HEIGHT;
var context = canvas.getContext("2d");
context.fillStyle = "white";
context.lineWidth = 0.3;
context.strokeStyle = "white";
var circleNum = 40;
var circleArr = [];
var maxR = 5;
var minR = 1;
var distance = 100;
//球的构造函数
function Circle(x, y, r, moveX, moveY, neighbor) {
    this.x = x;
    this.y = y;
    this.r = r;
    this.moveX = moveX;
    this.moveY = moveY;
    this.neighbor = neighbor;
}
//画悬浮球
function drawCircle(ctx, x, y, r) {
    ctx.beginPath();
    ctx.arc(x, y, r, 0, 2 * Math.PI);
    ctx.closePath();
    ctx.fill();
}
//画线
function drawLine(ctx, x, y, s, t) {
    ctx.beginPath();
    ctx.moveTo(x, y);
    ctx.lineTo(s, t);
    ctx.stroke();
}
//获得随机数
function randomNum(min, max) {
    return Math.floor(Math.random() * (max - min) + min);
}
//获得鼠标位置
function mousePosition(ev) {
    return { x: ev.pageX, y: ev.pageY };
}
//判断一个圆附近的圆
function neighborCircle() {
    var flag = true;
    for (var i = 0; i < circleArr.length; i++) {
        circleArr[i].neighbor = [];
    }
    for (var i = 0; i < circleArr.length; i++) {
        for (var j = 0; j < circleArr.length; j++) {
            if (adjustDis(circleArr[i].x, circleArr[i].y, circleArr[j].x, circleArr[j].y) < distance) {
                circleArr[i].neighbor.push(circleArr[j]);
            }
        }
    }
    for (var i = 0; i < circleArr.length; i++) {
        for (var j = 0; j < circleArr[i].neighbor.length; j++) {
            drawLine(context, circleArr[i].x, circleArr[i].y, circleArr[i].neighbor[j].x,
                circleArr[i].neighbor[j].y);
        }
    }
}
//计算距离
function adjustDis(x, y, s, t) {
    return Math.floor(Math.sqrt(Math.pow(x - s, 2) + Math.pow(y - t, 2)));
}
//初始化得到一个圆的数组
function init() {
    for (var i = 0; i < circleNum; i++) {
        var circle = new Circle(randomNum(0, WIDTH), randomNum(0, HEIGHT), randomNum(minR, maxR),
            randomNum(-10, 10) / 40, randomNum(-10, 10) / 40, []);
        circleArr.push(circle);
    }
}
//将canvas画上页面
function draw() {
    context.clearRect(0, 0, WIDTH, HEIGHT);
    for (var i = 0; i < circleNum; i++) {
        circleArr[i].x += circleArr[i].moveX;
        circleArr[i].y += circleArr[i].moveY;
        if (circleArr[i].x > WIDTH) {
            circleArr[i].x = 0;
        } else {
            if (circleArr[i].x < 0) {
                circleArr[i].x = WIDTH;
            }
        }
        if (circleArr[i].y > HEIGHT) {
            circleArr[i].y = 0;
        } else {
            if (circleArr[i].y < 0) {
                circleArr[i].y = HEIGHT;
            }
        }
        drawCircle(context, circleArr[i].x, circleArr[i].y, circleArr[i].r);
    }
}
//浏览器改变大小时
window.onresize = function() {
    var WIDTH = document.body.scrollWidth,
        HEIGHT = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
    canvas.setAttribute("width", WIDTH);
    canvas.setAttribute("height", HEIGHT);
    canvas.width = WIDTH;
    canvas.Height = HEIGHT;
    context.fillStyle = "white";
    context.lineWidth = 0.3;
    context.strokeStyle = "white";
};
//页面加载完成后，加载canvas
window.onload = function() {
    init();
    setInterval(function() {
            draw();
            neighborCircle();
        }, 16)
        // alert(document.cookie);

}