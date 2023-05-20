interface interfaccia {
    credito: number;
	numerochiamate: number;
    ricarica(volore:number):void;
    chiamata(minuti:number):void;
    numero404():number;
    getNumeroChiamate():number;
    azzeraChiamate():number;
}
class primo implements interfaccia  {
	credito: number;
	numerochiamate: number;
    constructor(_credito: number,_numerochiamate: number) {
		this.credito=_credito;
        this.numerochiamate=_numerochiamate
        
	}
    ricarica(valore:number){return this.credito=this.credito+valore};
    public chiamata(minuti:number){this.credito=this.credito-(minuti*0.2)
    return this.numerochiamate+=1};
    numero404(){return this.credito};
    getNumeroChiamate(){return this.numerochiamate};
    azzeraChiamate(){return this.numerochiamate=0};
}
class secondo extends primo {
	constructor(_credito: number,_numerochiamate: number) {
		super(_credito,_numerochiamate)
	}
    chiamata(minuti:number){this.credito=this.credito-(minuti*2000)
        return this.numerochiamate+=1}
    
}
class terzo extends primo {
	constructor(_credito: number,_numerochiamate: number) {
		super(_credito,_numerochiamate)
	}
    chiamata(minuti:number){this.credito=this.credito-(minuti*0.09)
        return this.numerochiamate+=1}
    
}


const uliotim= new primo(20,0);
console.log('nuovoutente')
console.log(uliotim);
console.log('ricarica')
console.log(uliotim.ricarica(20));
console.log(uliotim);
console.log('chiamata');
console.log(uliotim.chiamata(20));
console.log(uliotim.chiamata(20));
console.log(uliotim.chiamata(20));
console.log(uliotim)
console.log('numero404')
console.log(uliotim.numero404())
console.log(uliotim)
console.log('getnumerochiamate')
console.log(uliotim.getNumeroChiamate())
console.log(uliotim)
console.log('azzerachiamata')
console.log(uliotim.azzeraChiamate());
console.log(uliotim)
const berlusca=new secondo(2000,0)
console.log(berlusca)
console.log(berlusca.chiamata(1))
console.log(berlusca)

var btnSub1=document.getElementById('register1');

btnSub1?.addEventListener('click',function registra1(){
    let a:any=document.getElementById('formRicaricaIniziale1');
    a=a.value;
    const zorro=new primo(a,0)
        console.log(zorro);
    
    
   svuotasubmit()
})
function svuotasubmit(){
    let a:any=document.getElementById('formRicaricaIniziale1');
         a.value='' 
}
