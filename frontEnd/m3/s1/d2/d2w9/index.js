"use strict";
class bank1 {
    constructor(_prelievo, _deposito, _contoCorrente) {
        this.prelievo = _prelievo;
        this.deposito = _deposito;
        this.contoCorrente = _contoCorrente;
    }
    prelevare() {
        return this.contoCorrente = this.contoCorrente - this.prelievo;
    }
    depositare() {
        return this.contoCorrente = this.contoCorrente + this.deposito;
    }
    interessi() {
        return this.contoCorrente = this.contoCorrente + this.contoCorrente * 0.10;
    }
}
class bank2 extends bank1 {
    interessi() {
        console.log('noooo');
    }
}
let ulioBank = new bank1(100, 200, 20000);
console.log(ulioBank);
console.log(ulioBank.interessi());
console.log(ulioBank.prelevare());
console.log(ulioBank.depositare());
let ulioBank2 = new bank2(200, 400, 1000000000);
console.log(ulioBank2);
console.log(ulioBank2.depositare());
console.log(ulioBank2.prelevare());
console.log(ulioBank2.interessi());
console.log(ulioBank2.prelevare());
