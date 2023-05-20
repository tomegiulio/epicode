"use strict";
class dipendente {
    constructor(_nome, _cognome, _codiceFiscale) {
        this.nome = _nome;
        this.cognome = _cognome;
        this.codiceFiscale = _codiceFiscale;
    }
}
class tax extends dipendente {
    constructor(_codredd, _redditoLordo, _tasseInps, _tasseIrpef, _nome, _cognome, _codiceFiscale) {
        super(_nome, _cognome, _codiceFiscale);
        this.codredd = _codredd;
        this.redditoLordo = _redditoLordo;
        this.tasseInps = _tasseInps;
        this.tasseIrpef = _tasseIrpef;
    }
    getUtileTasse() {
        return this.redditoLordo * this.codredd / 100;
    }
    getTasseInps() {
        return this.getUtileTasse() * this.tasseInps / 100;
    }
    getTasseIrpef() {
        return this.getUtileTasse() * this.tasseIrpef / 100;
    }
    getReddittoNetto() {
        return this.redditoLordo - (this.getTasseInps() + this.getTasseIrpef());
    }
}
const ulioEmployer = new tax(1, 2000, 50, 40, 'giulio', 'tome', 'tmogli00s16a757t');
console.log(ulioEmployer);
console.log(ulioEmployer.getUtileTasse());
console.log(ulioEmployer.getTasseInps());
console.log(ulioEmployer.getTasseIrpef());
console.log(ulioEmployer.getReddittoNetto());
