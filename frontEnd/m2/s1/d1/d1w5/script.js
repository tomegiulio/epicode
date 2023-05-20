var dataAttuale = new Date();
console.log(dataAttuale);
document.getElementById('dataCompleta').innerHTML=(dataAttuale);

var day= new Date();
var onlyday= day.getDate();



var day= new Date();
var onlyday= day.getDate();
document.getElementById('giorno').innerHTML=(onlyday);

var month= new Date();
var onlymonth= day.getMonth();
document.getElementById('mese').innerHTML=(`Mese: ${onlymonth}`);

var year= new Date();
var onlyYear=year.getFullYear();
document.getElementById('dataNumber').innerHTML=(`Oggi e il: ${onlyday}-${onlymonth}-${onlyYear} `);



var hour= new Date();
var onlyHour = hour.getHours(01);
console.log(onlyHour)

switch(onlyHour){
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    case 10:
    case 11:
        document.getElementById('saluto').innerHTML=(`Buongiorno`);
    break;

    case 12:
        document.getElementById('saluto').innerHTML=(`Buon appetito`);
    break;
    case 13:
    case 14:
    case 15:
    case 16:
        document.getElementById('saluto').innerHTML=(`Buon pomeriggio!`);
    break;
    
    case 17:
    case 18:
    case 19:
        document.getElementById('saluto').innerHTML=(`Buonasera`);
    break;
    
    case 20:
        document.getElementById('saluto').innerHTML=(`Buona cena!`);
    break;
    
    case 21:
    case 22:
    case 23:
        document.getElementById('saluto').innerHTML=(`Buonaserata`);
    break;
    
    case 24:
    case 01:
    case 02:
    case 03:
    case 04:
        document.getElementById('saluto').innerHTML=(`Buonanotte`);
    break;
    
}
   