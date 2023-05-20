document.getElementById("btnAddRow").onclick = function addRow(){
    this.fname=document.getElementById('name').value;
    this.surname=document.getElementById('lname').value;
    this.birthDate=document.getElementById('birthday').value;
    let today = new Date();
    let selezionadata = new Date(this.birthDate)
    let years = (today.getFullYear() -selezionadata.getFullYear());
    
    if(this.fname===''||this.surname===''||this.birthDate===''){
        document.getElementById('errorspace').innerHTML=('Riepiere il campo di tutti i form')
    }else{
        
        document.getElementById("table").style.display = "block";
        var newRow = table.insertRow(table.rows.length);
        var cel1 = newRow.insertCell(0);
        var cel2 = newRow.insertCell(1);
        var cel3 = newRow.insertCell(2);
        cel1.innerHTML = this.fname;
        cel2.innerHTML = this.surname;
        cel3.innerHTML = years;
        const inputs = document.querySelectorAll('#name, #lname, #birthday')
        document.getElementById('errorspace').innerHTML=('');
        inputs.forEach(input => {
            input.value = '';
          });
    }
    
    
}
console.log(btnAddRow)