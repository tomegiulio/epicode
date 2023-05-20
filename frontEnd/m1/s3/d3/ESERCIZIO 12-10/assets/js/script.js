
let arance=5;
function pausaSucco(mele,arance) {
    const frutta=`Succo con ${mele} mele e ${arance} arance`;
    return frutta;
}

document.getElementById('corretta').innerHTML =pausaSucco(4,5);
document.getElementById('sbagliata').innerHTML =pausaSucco(4,);




function calcolaAnni(nascita,annoCorrente){
    const risultato=(annoCorrente -= nascita);
    const eta=`Attualmente hai: ${risultato} anni`;
    return eta;
}

document.getElementById('eta').innerHTML =calcolaAnni(2000, 2022)


function calcolaAnniPersona(nome,annoCorrente,eta){
    const risultato=(annoCorrente -= eta);
    const anno=`L'anno di nascita di ${nome} e il ${risultato}`;
    return anno;
}

document.getElementById('persona1').innerHTML =calcolaAnniPersona('Maria',2022,30);
document.getElementById('persona2').innerHTML =calcolaAnniPersona('Anna',2022,26);



function pausaTorta(fetteMela, frutto1)
{const ricetta1=`Torta con ${fetteMela} fette di ${frutto1}`
return ricetta1;}

function tortaStrati(fetteArancia,frutto2)
{const ricetta= `${pausaTorta(9,'mela')} e ${fetteArancia} fette di ${frutto2}`
return ricetta}

document.getElementById('torta').innerHTML =tortaStrati(15,'arancia');




btn= document.getElementById('calcola');

btn.addEventListener('click', function(){
let cibo=Number(document.getElementById('cibo').value);
let abiti=Number(document.getElementById('abiti').value);
let detersivo=Number(document.getElementById('detersivi').value);
const somma = cibo+=abiti+=detersivo;
console.log(somma);
document.getElementById('totale').innerHTML=`Il risultato dell'operazione ${somma} €`;
})





 