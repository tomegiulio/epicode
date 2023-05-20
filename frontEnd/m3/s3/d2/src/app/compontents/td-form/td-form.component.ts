import { Component,  ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-td-form',
  templateUrl: './td-form.component.html',
  styleUrls: ['./td-form.component.scss']
})
export class TdFormComponent {
title = 'd2'
@ViewChild('form',{static: true}) form!:NgForm; //assegnamo il form come figlio #f(solitamente o #form)   di appcomponent che lo può modificare solo il ts di appcomponent

userForm = {//creiamo oggetto
  defUsername: '',
  alterego: '',
  potere: '',
  pianeta : '',
  nemico:''

}
user = {//creiamo oggetto

}


//mi serve un oggetto
ngOnInit(): void {
  this.form.statusChanges?.subscribe(status => {
    console.log('Stato del form: ', status)
  })
}

submit(){
  console.log('Form inviato: ', this.form, '- Nome: ', this.form.value.userInfo.defUsername, '- AlterEgo: ' , this.form.value.userInfo.alterego, '- Potere: ', this.form.value.userInfo.potere, '- Nemico: ', this.form.value.userInfo.nemico, '- Pianeta: ', this.form.value.userInfo.pianeta);


}

}
