let budjet=1000;
let operazioni='';


document.getElementById('budjetIniziale').innerHTML=`Il budjet iniziale e' di: ${budjet}$`;



do{ 
   let randomNumber=Math.floor(Math.random()*198)+ 1;
    budjet= budjet-randomNumber;
     operazioni+=' - '+randomNumber+ '$'+' = '+budjet+'$' + "<br>" 
     
}while (budjet>=300) 
document.getElementById('calcoli').innerHTML= (operazioni);



if(budjet<=300){
    {document.getElementById('calcoli').innerHTML+=('basta spendere soldi!')}
}


