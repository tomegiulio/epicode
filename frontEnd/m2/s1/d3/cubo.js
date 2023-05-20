const rotate = () => {
    const cubes = document.querySelectorAll('.cube');
    Array.from(cubes).forEach(cube => cube.style.transform = `rotateY(${pos}deg)`);
}
let btnPrev = document.querySelector('.left-arrow');
let btnNext = document.querySelector('.right-arrow');
let btnStart = document.querySelector('.play-pause');

let pos = 0;



btnNext.addEventListener('click', () => {
    pos -= 90;
    rotate();
});
btnPrev.addEventListener("click", () => {
    pos += 90;
    rotate();
});
btnNext.addEventListener('mouseover', () => {
    pos -= 45;
    rotate();
});
btnPrev.addEventListener('mouseover', () => {
    pos += 45;
    rotate();
});
btnNext.addEventListener('mouseout', () => {
    pos += 45;
    rotate();
});
btnPrev.addEventListener('mouseout', () => {
    pos -= 45;
    rotate();
});

var cubeInterval = setInterval(nextCube,2000);
function nextCube(){
    pos -= 90;
    rotate();
}
var playing = true;
function pauseCube(){
    btnStart.innerHTML = '<i class="fas fa-play">';
    playing = false;
    clearInterval(cubeInterval);
}
function playCube(){
    btnStart.innerHTML = '<i class="fas fa-pause">';
    playing = true;
    cubeInterval = setInterval(nextCube,2000);
}
btnStart.onclick = function(){
    if(playing){ pauseCube(); }
    else{ playCube(); }
};