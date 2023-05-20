
(function () {
    let display = document.querySelector(".display");
    let buttons = document.querySelectorAll(".button");
    let clear= document.querySelector(".clear");
    let equals = document.querySelector(".equal");

    buttons.forEach(function(button) {
        button.addEventListener('click', function(e){
            let value = e.target.dataset.num;
            display.value += value
        })
    });

    equals.addEventListener('click', function(e){
        if(display.value === '') {
            display.value = "";
        } else {
            let answer =eval(display.value);
            display.value = answer;
        }
    })

    clear.addEventListener('click',function(e){
        display.value = "";}
        
    )
    
   
    

})();



