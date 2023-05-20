import { Component, OnInit } from '@angular/core';
import { Users } from 'src/app/users';
@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users:Users[]=[]
  constructor(){ }

  ngOnInit(): void {
    this.fetchUsers()
  }

  fetchUsers() {
    fetch("http://localhost:3000/users").then(res=>res.json()).then(res=>{
      this.users = res

    })
  }




}

