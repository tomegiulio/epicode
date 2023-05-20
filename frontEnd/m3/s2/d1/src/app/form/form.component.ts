import { Component, OnInit } from '@angular/core';
import { TaskClass } from '../interface/task-class.task';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {
    onTaskCreated:any
  constructor() { }

  ngOnInit(): void {
  }
  addToJson(){


  }
}
