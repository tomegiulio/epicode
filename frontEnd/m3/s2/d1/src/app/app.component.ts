import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TaskClass } from './interface/task-class.task';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'w10d1';
  tasks: TaskClass[]=[]
  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    console.log('ngOnInit')
    this.http
      .get('http://localhost:3000/task')
      .subscribe((task:any) => {
        this.tasks = task
      })
  }

}

