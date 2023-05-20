//menu open/close//

const menu=document.querySelector('.menu');
const menuOpen=document.getElementById('btnOpen');

menuOpen.addEventListener('click', flex);
function flex(){
    menu.classList.toggle('displayBlock')
}



//bottoni h1//
const btnBigger=document.getElementById('btnBigger');
const btnColor=document.getElementById('btnColor');
const btnMaiuscolo=document.getElementById('btnMaiuscolo');
const btnHide=document.getElementById('btnHide');
const btnShow=document.getElementById('btnShow');
const titolo=document.getElementById('titolone');

btnBigger.addEventListener('click', bigger);
function bigger(){
    titolo.classList.toggle('bigger')
}

btnMaiuscolo.addEventListener('click', maiuscolo);
function maiuscolo(){
    titolo.classList.toggle('upperCase')
}

btnColor.addEventListener('click', red);
function red(){
    titolo.classList.toggle('colorRed')
}

btnHide.addEventListener('click', hide);
function hide(){
    titolo.classList= 'hidden';
}

btnShow.addEventListener('click', show);
function show(){
    titolo.classList='visible';
}
//spesa
var spesa=document.querySelectorAll("li");
for (var i=0; i<spesa.length; i++){
    spesa[i].addEventListener('click', function(){
        this.classList.toggle('lineThrought');
    }),
    
    spesa[i].addEventListener('mouseover', function(){
        this.classList+= 'colorRed';
    }),

    spesa[i].addEventListener('mouseout', function(){
        this.classList.add('colorBlack');
    });
}
