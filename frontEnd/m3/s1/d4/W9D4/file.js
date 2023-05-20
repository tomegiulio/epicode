"use strict";
class vestiti {
    constructor(_id, _codProd, _collezzione, _capo, _modello, _quantita, _colore, _prezzoivaesclusa, _prezzoivainclusa, _disponibile, _saldo) {
        this.id = _id;
        this.codProd = _codProd;
        this.collezzione = _collezzione;
        this.capo = _capo;
        this.modello = _modello;
        this.quantita = _quantita;
        this.colore = _colore;
        this.prezzoivaesclusa = _prezzoivaesclusa;
        this.prezzoivainclusa = _prezzoivainclusa;
        this.disponibile = _disponibile;
        this.saldo = _saldo;
    }
    getSaldoCapo() {
        return this.prezzoivaesclusa * (this.saldo / 100);
    }
    getAcquistoCapo() {
        return this.prezzoivainclusa - this.getSaldoCapo();
    }
}
var madonna = [];
function fetchThis() {
    fetch('http://localhost:3000/capo').then((response) => {
        return response.json();
    }).then((data) => {
        madonna = data;
        data.map(function (element) {
            let capo = new vestiti(element.id, element.codprod, element.collezzione, element.capo, element.modello, element.quantita, element.colore, element.prezzoivaesclusa, element.prezzoivainclusa, element.disponibile, element.saldo);
            console.log(capo);
            console.log(new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'EUR' }).format(capo.getAcquistoCapo()));
        });
    });
}
fetchThis();
