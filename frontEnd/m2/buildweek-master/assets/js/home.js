window.addEventListener('DOMContentLoaded', getData(), accesso());
var articoli = [];
var utente = localStorage.key['nome'];
function getData() {
    fetch('http://localhost:3000/articoli').then((response) => {
        return response.json();
        //il json che ritorna viene scritto in data come rappresentazione che sarà poi riportato nell'array elenco 
    }).then((data) => {
        articoli = data;
        articoli.map(function (element, index) {//per ogni articolo si andranno a prendere i dati dal json e al click su di esso si aprirà una pagina dell'articolo specifico
            var card = document.getElementById('card');
            card.innerHTML += 
            `<div class="col">
                <div class="card h-100" onclick="myFunction(${index})">
                    <img src="${element.location}" class="card-img-top">
                    <div class="card-body">
                       <h5 class="card-title" >${element.nome}</h5>
                    </div>
                </div>
            </div>`
        })
    })
}
function accesso(){
if(localStorage.getItem('nome')){//con questo if viene applicato il logout dopo aver effettuato l'accesso
    document.getElementById('loginNav').innerText = 'Logout';
   
    document.getElementById('benvenuto').innerHTML = `<a href =#  class="text-decoration-none  text-dark" > Benvenuto/a ${localStorage.getItem("nome", utente)}</a>`;
    document.getElementById('loginNav').addEventListener('click', function (){
        localStorage.clear();
    location.href = 'log.html'

    });
}
}

function myFunction(index){
   
    if (!localStorage.getItem(articoli[index].nome,articoli[index].descrizione, articoli[index].prezzo, articoli[index].location )) {
        localStorage.setItem("titolo", articoli[index].nome);
        localStorage.setItem("descrizione", articoli[index].descrizione);
        localStorage.setItem("prezzo", articoli[index].prezzo);
        localStorage.setItem("immagine", articoli[index].location);
    }
    location.href = "articoli.html"
} 
