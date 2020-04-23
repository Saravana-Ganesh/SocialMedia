import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
   isLoggedIn  = localStorage.getItem('isLoggedIn');
  constructor(
    private router:Router,
  ) { }

  ngOnInit(): void {
  }
  findFriends(){
    this.router.navigateByUrl('/findFriends');
  }    
}               
                  