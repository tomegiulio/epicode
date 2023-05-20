var stoImparando = `STO IMPARANDO JAVASCRIPT`;

document.getElementById('maiuscola').innerHTML= stoImparando;
var minuscolo = stoImparando.toLowerCase();

document.getElementById('minuscola').innerHTML= minuscolo;
var split= Array.from(stoImparando);


document.getElementById('strArray').innerHTML= split;


var splitClean1 =split.splice(2,1);
var splitClean2 =split.splice(1,1);
var splitClean3 =split.splice(12,1);
var splitClean4 =split.splice(15,1);
var array=splitClean1.concat(splitClean2,splitClean3,splitClean4);
var estrai =array.join(' ');
document.getElementById('estraiCaratteri').innerHTML=estrai;

var concatJs =` JS`;
var concatt=stoImparando.concat(concatJs)
document.getElementById('concatena').innerHTML=concatt;

var estrai2=stoImparando.charAt(5,1)+stoImparando.charAt(6,1)+stoImparando.charAt(7,1)+stoImparando.charAt(8,1);
document.getElementById('estraiStringa').innerHTML=estrai2;



////fine



let persone=['Giovanni','Carla','Piero','Mirtilla'];
document.getElementById('array').innerHTML=persone;

document.getElementById('lunghezza').innerHTML=persone.length;

let pierino=persone.splice(2,1)
document.getElementById('elemento').innerHTML=pierino;

let mirtilloBoschivo=persone.splice(2,1)
document.getElementById('ultimo').innerHTML=mirtilloBoschivo;

var ultimo=persone.splice(2,0, 'Massimo','Mirtilla');
document.getElementById('modificato').innerHTML=persone;


//fine


const calcolAnni =  function(compleanno) {return 2022- compleanno;};
const compleanno =[2001, 1990, 2018, 2010, 2012];
document.getElementById('eta1').innerHTML=`Mario ha ${calcolAnni(compleanno[0])} anni`;
document.getElementById('eta2').innerHTML=`Anna ha ${calcolAnni(compleanno[1])} anni`;
document.getElementById('eta3').innerHTML=`Maria ha ${calcolAnni(compleanno[2])} anni`;
document.getElementById('eta4').innerHTML=`Nicola ha ${calcolAnni(compleanno[3])} anni`;
document.getElementById('eta5').innerHTML=`Giovanna ha ${calcolAnni(compleanno[4])} anni`;
document.getElementById('arrayEta').innerHTML=`Array: ${compleanno}`;


///fine


let animali=['coniglio','cane','gatto','criceto']
document.getElementById('intero').innerHTML=animali;

let leone= animali.push("leone");
document.getElementById("aggiunto").innerHTML=animali;

let leoneNo=animali.pop("leone");
document.getElementById('estratto').innerHTML=animali;

document.getElementById('verifica1').innerHTML=animali.includes('gatto');
document.getElementById('verifica2').innerHTML=animali.includes('orangoTango');