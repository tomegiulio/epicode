import { Component } from '@angular/core';
import { PostInterface } from './interface/post-interface';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'w10d2';
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
