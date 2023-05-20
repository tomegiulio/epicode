var elenco = [];

var jsonHere;

window.addEventListener('DOMContentLoaded', init);

function init() {
    jsonHere = document.getElementById('jsonHere');
    printData();
}



function printData() {
    fetch('http://localhost:3000/user').then((response) => {
        return response.json();
    }).then((data) => {
        elenco = data;
        elenco.map(function(element) {
jsonHere.innerHTML += `<div class="row"><div class="col-4">${element.nome}</div><div class="col-4">${element.user}</div><div class="col-4">${element.email}</div></div>`;
});
    });
}
