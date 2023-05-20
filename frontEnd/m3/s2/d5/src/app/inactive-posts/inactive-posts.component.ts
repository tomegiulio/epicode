import { Component, OnInit } from '@angular/core';
import { Post } from '../post';
import { PostService } from '../post.service';

@Component({
  selector: 'app-inactive-posts',
  templateUrl: './inactive-posts.component.html',
  styleUrls: ['./inactive-posts.component.scss']
})
export class InactivePostsComponent implements OnInit {

  posts:Post[]=[]

  constructor(private postSRV:PostService) { }

  ngOnInit(): void {
    this.getPosts()
    }


  attiva(id:number) {
    console.log(id);
    
    this.postSRV.toggleDbPost(id).then(res=>{
      if(res.ok){
        this.getPosts()
      }else {
        console.log("err");
      }
    })
  }

  elimina(id:number) {
    this.postSRV.deleteDb(id).then(res=>{
      if(res.ok) {
        this.getPosts()
      }
    })
  }

  getPosts() {
    this.posts = this.postSRV.getPostsFiltrati(false)
    console.log(this.posts);
  }
}
