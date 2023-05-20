import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {Route , RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';



import { AppComponent } from './app.component';
import { NabarComponent } from './nabar/nabar.component';
import { ActiveItemComponent } from './active-item/active-item.component';
import { DisabledItemComponent } from './disabled-item/disabled-item.component';
import { HomeComponent } from './home/home.component';
const routes: Route[] = [
  {
    path:"", //home
    component: HomeComponent
  },
  {
    path:"attivi",
    component: ActiveItemComponent
  },
  {
    path:"inattivi",
    component: DisabledItemComponent
  }

]
@NgModule({
  declarations: [
    AppComponent,
    NabarComponent,
    ActiveItemComponent,
    DisabledItemComponent,
    HomeComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
