import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Post } from '../post';


@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.scss']
})
export class PostCardComponent implements OnInit {

  @Output() eliminaEvent = new EventEmitter();

  @Input() p!:Post

  constructor( ) { }

  ngOnInit(): void {
  }

  elimina(){
    this.eliminaEvent.emit(this.p.id)
  }

}
