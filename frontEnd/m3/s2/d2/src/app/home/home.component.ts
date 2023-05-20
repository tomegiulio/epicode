import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PostInterface } from '../interface/post-interface';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  posts: PostInterface[]=[]
  activePosts: PostInterface[]=[]
  disabledPosts: PostInterface[]=[]
  constructor(private http: HttpClient) { }
  getData():void{
    this.http
      .get('http://localhost:3000/post')
      .subscribe((post:any) => {
        this.posts = post

        this.activePosts=this.posts.filter(post=>post.active==true)
        this.disabledPosts=this.posts.filter(post=>post.active==false)


  })
}
}


