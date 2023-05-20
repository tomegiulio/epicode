var utente = localStorage.key['nome'];
//se utente loggato modifica registra e login in nav bar inserendo saluto e logout
if(localStorage.getItem('nome')){
    document.getElementById('loginNav').innerText = 'Logout';

    document.getElementById('benvenuto').innerHTML =` <a href ="#"  class="text-decoration-none  text-dark" > Benvenuto/a ${localStorage.getItem("nome")}</a>`;
    document.getElementById('loginNav').addEventListener('click', function (){
        localStorage.clear();
    location.href = 'log.html'
    });
}

  