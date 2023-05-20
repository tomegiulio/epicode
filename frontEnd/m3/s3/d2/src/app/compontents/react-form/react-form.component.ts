import { Component, } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms'

@Component({
  selector: 'app-react-form',
  templateUrl: './react-form.component.html',
  styleUrls: ['./react-form.component.scss']
})
export class ReactFormComponent  {
title ='d2'
form!: FormGroup;
usernameProibiti = ['Ray', 'Mario'];

constructor (private fb: FormBuilder) {

}

validUsername = (formC: FormControl) => {
  if (this.usernameProibiti.includes(formC.value)) {
    return {'usernameProibito': true};
  } else {
    return null
  }
}

ngOnInit(): void {
  //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
  //Add 'implements OnInit' to the class.
  this.form = this.fb.group({
      userInfo: this.fb.group({
          username: this.fb.control(null, [Validators.required, this.validUsername]),
      }),

      poteri: this.fb.array([]),
      pianeta: this.fb.array([]),
      nemico: this.fb.array([])

  });
  this.form.valueChanges.subscribe(value => {
      console.log(value);
  });
}
getErrorsC(name: string, error: string){
return this.form.get(name)?.errors![error];

}
getFormC(name: string){
return this.form.get(name);
}
getPoteriF(){
return (this.form.get('poteri') as FormArray).controls
}
addPoteri(){
const control = this.fb.control(null);
return (this.form.get('poteri') as FormArray).push(control);

}
getPianetaF(){
  return (this.form.get('pianeta') as FormArray).controls
  }
  addPianeta(){
  const control = this.fb.control(null);
  return (this.form.get('pianeta') as FormArray).push(control);
  }
  getNemicoF(){
    return (this.form.get('nemico') as FormArray).controls
    }
    addNemico(){
    const control = this.fb.control(null);
    return (this.form.get('nemico') as FormArray).push(control);
    }
submit(){
console.log(this.form);
//this.form.reset();
}
}
