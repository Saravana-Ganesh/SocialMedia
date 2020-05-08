import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';       
import { SignupComponent } from './signup/signup.component';
import { RouterModule } from '@angular/router';
import { SigninComponent } from './signin/signin.component';
import { HomeComponent } from './home/home.component';
import { TopComponent } from './top/top.component';
import { MessageComponent } from './message/message.component';
import { FindFriendComponent } from './find-friend/find-friend.component';
import { FriendsComponent } from './friends/friends.component';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    SigninComponent,
    HomeComponent,
    TopComponent,
    MessageComponent,
    FindFriendComponent,
    FriendsComponent        
  ],        
  imports: [   
    BrowserModule,
    HttpClientModule ,
    AppRoutingModule,
    FormsModule,
    RouterModule.forRoot([
      { path: '', component: SignupComponent },
      {path:'login',component:SigninComponent},
      {path:'home',component:HomeComponent},
      {path:'messages',component:MessageComponent},
      {path:'findFriends',component:FindFriendComponent},
      {path:'friends',component:FriendsComponent}
      
    ])
  ],  
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }        
