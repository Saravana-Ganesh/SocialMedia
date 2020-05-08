import { Component, OnInit, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { ConfigService } from '../services/config.service';

@Component({
  selector: 'app-top',
  templateUrl: './top.component.html',
  styleUrls: ['./top.component.css']
})
export class TopComponent implements OnInit { 
  friendRequestCount:number;
  data:any;
  load = false;
  friendRequestNoitification = false;
  name:string;
  constructor(
    private router: Router,
    private configService:ConfigService,
  ) { }

  ngOnInit(): void {
    if(localStorage.getItem('email')=='' || localStorage.getItem('email')==undefined
    ||localStorage.getItem('isLoggedIn')=='false'){
      console.log('logged out');
      this.router.navigateByUrl('/login');     
    }
    this.name = localStorage.getItem('name');
  }
  loadHeader(res){
    this.name = localStorage.getItem('name');
    this.data = res;
    console.log('Header called');
    console.log(res);
    this.friendRequestCount = this.data.friendRequestMasterBO.length
    if(this.friendRequestCount!=0){
      this.friendRequestNoitification = true;
    }
  }
  messages(){
    this.router.navigateByUrl('/messages');
  }
  home(){
    this.router.navigateByUrl('/home');
  }
  friendsPage(){
    this.router.navigateByUrl('/findFriends');
  }
  logout(){
    localStorage.setItem('email','');
    localStorage.setItem('isLoggedIn','false');
    this.router.navigateByUrl('/login');
  }
}
