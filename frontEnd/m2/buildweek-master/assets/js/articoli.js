window.addEventListener('DOMContentLoaded', dettaglio(), accesso(), addProd());
var dettaglioarticoli = localStorage.key['titolo'];
var descrizione = localStorage.key['descrizione']
var prezzo = localStorage.key['prezzo']
var utente = localStorage.key['nome'];
var carrello = [];

function dettaglio() {
  if (localStorage.getItem("titolo")) {//con questo if viene applicato il logout dopo aver effettuato l'accesso
    document.getElementById('sguardo').innerHTML += ` ${localStorage.getItem("titolo")}`;
  };
  if (localStorage.getItem("descrizione")) {//con questo if viene applicato il logout dopo aver effettuato l'accesso
    document.getElementById('descrizione').innerHTML += ` ${localStorage.getItem("descrizione")}`;
  }
  if (localStorage.getItem("prezzo")) {//con questo if viene applicato il logout dopo aver effettuato l'accesso
    document.getElementById('prezzo').innerHTML += ` ${localStorage.getItem("prezzo")}`;
  }
  if (localStorage.getItem("immagine")) {//con questo if viene applicato il logout dopo aver effettuato l'accesso
    document.getElementById('immagine').innerHTML += `<img src="${localStorage.getItem("immagine")}" class="img-fluid rounded-start"> `;
  }

}

function accesso() {

  if (localStorage.getItem('nome')) {//con questo if viene applicato il logout dopo aver effettuato l'accesso
    document.getElementById('loginNav').innerText = 'Logout';

    document.getElementById('benvenuto').innerHTML = `<a href =#  class="text-decoration-none  text-dark" > Benvenuto/a ${localStorage.getItem("nome")}</a>`;
    document.getElementById('loginNav').addEventListener('click', function () {
      localStorage.clear();
      location.href = 'log.html'
    });
  }
}


function addProd() {//funzione per aggiungere gli elemnti del carrello nel localstorage
  var idUser = localStorage.getItem("nome");

  fetch("http://localhost:3000/carrello?idUtente=" + idUser) //recupera i dati dal JSON
    .then((response) => {
      return response.json();
    })
    .then((res) => {
      localStorage.setItem('idCarrello', res[0].id);
      localStorage.setItem('carrello', JSON.stringify(res));
    })
}


function aggiungiCarrello() {
  var carrello = JSON.parse(localStorage.getItem('carrello'));

  var articoloCarrello = {
    location: localStorage.getItem("immagine"),
    nome: localStorage.getItem("titolo"),
    prezzo: localStorage.getItem("prezzo")
  }
  carrello.push(articoloCarrello);
  localStorage.setItem('carrello', JSON.stringify(carrello));
  updateCarrello();
}


function updateCarrello() {//dal localstorage gli passa al json
var id = localStorage.getItem('idCarrello');

  fetch("http://localhost:3000/carrello/" + id, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
    body: localStorage.getItem('carrello')
  });
  
}
