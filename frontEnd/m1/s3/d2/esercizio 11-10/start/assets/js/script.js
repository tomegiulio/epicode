{let name = 'Aldo'; 
console.log(name);
let role = 'cliente';
console.log(role);
let number=3.14;
console.log(number);
document.getElementById('concatena').innerHTML = ` ${name} ${role} ${number}`}

{let name = 'Aldo'; 
console.log(name);
let role = 'cliente';
console.log(role);
let number=3.14;
console.log(number);
document.getElementById('concatena2').innerHTML = ` ${name} ${role} ${number}`}

name = 'Mario'
document.getElementById('var').innerHTML = `${name}`
document.getElementById('final').innerHTML = `${name}`

name = 'Carla'
document.getElementById('let').innerHTML = `${name}`

name = 'Mario'
document.getElementById('let2').innerHTML = `${name}`

name = 'Carla'
document.getElementById('let3').innerHTML = `${name}`
document.getElementById('final2').innerHTML = `${name}`



number = 15;
document.getElementById('iniziale').innerHTML = 'Valore iniziale: ' +(number);

number +=15;
let add = number+1;
document.getElementById('valore1').innerHTML = `Addizzione e incremento: ${number}, ${add}`;

number-=25;
var sottrazzione = number-1;
document.getElementById('valore2').innerHTML = `Sottrazzione e decremento: ${number}, ${sottrazzione}`;

number*=9;
document.getElementById('valore3').innerHTML = `Moltiplicazione: ${number}`;

number/=9;
document.getElementById('valore4').innerHTML = `Divisione: ${number}`;

document.getElementById('valore5').innerHTML = `Concanetazione: 15 è un numero`
