class vestiti{
    id:number;
    codProd:number;
    collezzione:string;
    capo:string;
    modello:number;
    quantita:number;
    colore:string;
    prezzoivaesclusa:number;
    prezzoivainclusa:number;
    disponibile:string;
    saldo:number;
    constructor(_id:number,_codProd:number,_collezzione:string,_capo:string,_modello:number,_quantita:number,_colore:string,_prezzoivaesclusa:number,
        _prezzoivainclusa:number,_disponibile:string,_saldo:number){
            this.id=_id;
            this.codProd=_codProd;
            this.collezzione=_collezzione;
            this.capo=_capo;
            this.modello=_modello;
            this.quantita=_quantita;
            this.colore=_colore;
            this.prezzoivaesclusa=_prezzoivaesclusa;
            this.prezzoivainclusa=_prezzoivainclusa;
            this.disponibile=_disponibile;
            this.saldo=_saldo;
        }
    public getSaldoCapo():number{
        return this.prezzoivaesclusa*(this.saldo/100);
    }
    public getAcquistoCapo():number{
        return this.prezzoivainclusa-this.getSaldoCapo();
    }
}
var madonna=[];
function fetchThis():void {
    fetch('http://localhost:3000/capo').then((response)=>{
        return response.json();
    }).then((data)=>{
        madonna=data;
        data.map(function(element:any){
            let capo= new vestiti(element.id,element.codprod,element.collezzione,element.capo,element.modello,element.quantita,element.colore,element.prezzoivaesclusa,element.prezzoivainclusa,element.disponibile,element.saldo)
            console.log(capo)
            console.log(new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'EUR' }).format(capo.getAcquistoCapo()))
        })
        
    });
    
    
}
fetchThis();
   


