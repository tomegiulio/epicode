import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ActivePostsComponent } from './active-posts/active-posts.component';
import { InactivePostsComponent } from './inactive-posts/inactive-posts.component';
import { PostCardComponent } from './post-card/post-card.component';
import { MaiuscoloPipe } from './pipe/maiuscolo.pipe';
import { ColoraDirective } from './directives/colora.directive';
import { DettaglioComponent } from './dettaglio/dettaglio/dettaglio.component';
import { UsersComponent } from './users/users/users.component';
import { UsersInfoComponent } from './usersInfo/users-info/users-info.component';





@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ActivePostsComponent,
    InactivePostsComponent,
    PostCardComponent,
    MaiuscoloPipe,
    ColoraDirective,
    DettaglioComponent,
    UsersComponent,
    UsersInfoComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
