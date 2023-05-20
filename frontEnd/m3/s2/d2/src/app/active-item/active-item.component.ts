import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PostInterface } from '../interface/post-interface';

@Component({
  selector: 'app-root',
  templateUrl: './active-item.component.html',
  styleUrls: ['./active-item.component.scss']
})
export class ActiveItemComponent {
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


