import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  constructor( private router:Router) { }

  ngOnInit(): void {
    if(localStorage.getItem('email')=='' || localStorage.getItem('email')==undefined
    ||localStorage.getItem('isLoggedIn')=='false'){
      console.log('logged out');
      this.router.navigateByUrl('/login');
    }
  }

}
