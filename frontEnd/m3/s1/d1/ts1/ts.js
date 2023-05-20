var estratto;
verifica();
function verifica() {
    var valore1 = 22;
    var valore2 = 12;
    if (!valore1 || !valore2 || valore1 == 0 || valore2 == 0) {
        console.log('Entrambi i giocatori devono inserire un valore diverso da zero!');
    }
    else if (valore1 == valore2) {
        console.log('I due giocatori devono inserire valori differenti');
        return;
    }
    generazione();
    confronto(valore1, valore2);
}
function generazione() {
    return estratto = Math.floor((Math.random() * 100) + 1);
}
function confronto(valore1, valore2) {
    if (valore1 == estratto) {
        console.log('Il giocatore 1 ha indovinato!');
    }
    else if (valore2 == estratto) {
        console.log('Il giocatore 2 ha indovinato!');
    }
    else if ((Math.abs(estratto - valore1)) < (Math.abs(estratto - valore2))) {
        console.log('Nessuno ha indovinato, ma il giocatore 1 si è avvicinato di più');
    }
    else {
        console.log('Nessuno ha indovinato, ma il giocatore 2 si è avvicinato di più');
    }
}
