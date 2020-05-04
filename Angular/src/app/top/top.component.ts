import { Component, OnInit, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { ConfigService } from '../services/config.service';

@Component({
  selector: 'app-top',
  templateUrl: './top.component.html',
  styleUrls: ['./top.component.css']
})
export class TopComponent implements OnInit { 
  friendRequestCount:bigint;
  data:any;
  load = false;
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
  }
  loadHeader(res){
    this.data = res;
    console.log('Header called');
    this.friendRequestCount = this.data.friendRequestMasterBO.length
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
