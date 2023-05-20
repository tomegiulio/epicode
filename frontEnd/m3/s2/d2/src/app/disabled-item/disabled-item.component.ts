import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PostInterface } from '../interface/post-interface';

@Component({
  selector: 'app-disabled-item',
  templateUrl: './disabled-item.component.html',
  styleUrls: ['./disabled-item.component.scss']
})
export class DisabledItemComponent {
  posts: PostInterface[]=[]
  activePosts: PostInterface[]=[]
  disabledPosts: PostInterface[]=[]
  constructor(private http: HttpClient) { }
  getFalse():void{
    this.http
      .get('http://localhost:3000/post')
      .subscribe((post:any) => {
        this.posts = post

        this.activePosts=this.posts.filter(post=>post.active==true)
        this.disabledPosts=this.posts.filter(post=>post.active==false)


  })
}




}




