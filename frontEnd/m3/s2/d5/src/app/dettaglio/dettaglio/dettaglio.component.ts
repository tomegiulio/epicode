import { Component, OnInit, } from '@angular/core';
import { Post } from 'src/app/post';
import { PostService } from 'src/app/post.service';
import { ActivatedRoute } from '@angular/router';



@Component({
  selector: 'app-dettaglio',
  templateUrl: './dettaglio.component.html',
  styleUrls: ['./dettaglio.component.scss']
})
export class DettaglioComponent implements OnInit {

  constructor(private service:PostService, private route:ActivatedRoute) { }
  id!:number
  data!:Post
  ngOnInit(): void {
    this.id=this.route.snapshot.params['id']
    this.fetchPostt()
  }
  fetchPostt() {

    this.service.fetchPostt(this.id).then((data:Post) => {
     this.data=data;
     console.log(data)
    })


   }

}
