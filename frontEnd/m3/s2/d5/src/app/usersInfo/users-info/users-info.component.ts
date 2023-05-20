import { Component, OnInit } from '@angular/core';
import { Users } from 'src/app/users';
import { PostService } from 'src/app/post.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-users-info',
  templateUrl: './users-info.component.html',
  styleUrls: ['./users-info.component.scss']
})
export class UsersInfoComponent implements OnInit {
  id!:number
  data!:Users
  constructor(private service:PostService, private route:ActivatedRoute){ }

  ngOnInit() {
    this.id=this.route.snapshot.params['id']
    this.fetchUser()

  }

  fetchUser() {

   this.service.fetchUser(this.id).then((data:Users) => {
    this.data=data;
    console.log(data)
   })


  }



}
