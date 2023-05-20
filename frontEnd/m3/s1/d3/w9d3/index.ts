abstract class dipendente{
    nome:string;
    cognome:string;
    codiceFiscale:any;
    constructor(_nome:string,_cognome:string,_codiceFiscale:any){
        this.nome=_nome;
        this.cognome=_cognome;
        this.codiceFiscale=_codiceFiscale;
    }
}
class tax extends dipendente{
    codredd:number;
    redditoLordo:number;
    tasseInps:number;
    tasseIrpef:number;
    constructor(_codredd:number,_redditoLordo:number,_tasseInps:number,_tasseIrpef:number,_nome:string,_cognome:string,_codiceFiscale:any){
        super(_nome,_cognome,_codiceFiscale)
        this.codredd=_codredd;
        this.redditoLordo=_redditoLordo;
        this.tasseInps=_tasseInps;
        this.tasseIrpef=_tasseIrpef;
    }
    public getUtileTasse():number{
        return this.redditoLordo*this.codredd/100
    }
    public getTasseInps():number{
        return this.getUtileTasse()* this.tasseInps/100
    }
    public getTasseIrpef():number{
        return this.getUtileTasse()* this.tasseIrpef/100
    }
    public getReddittoNetto():number{
        return this.redditoLordo - (this.getTasseInps() + this.getTasseIrpef())
    }
}
const ulioEmployer=new tax(1,2000,50,40,'giulio','tome','tmogli00s16a757t')
console.log(ulioEmployer);
console.log(ulioEmployer.getUtileTasse());
console.log(ulioEmployer.getTasseInps());
console.log(ulioEmployer.getTasseIrpef());
console.log(ulioEmployer.getReddittoNetto())
