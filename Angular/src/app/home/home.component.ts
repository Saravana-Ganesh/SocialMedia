import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ConfigService } from '../services/config.service';
import { TopComponent } from '../top/top.component';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
   isLoggedIn  = localStorage.getItem('isLoggedIn');
   data : any;
   name: string;
  @ViewChild(TopComponent) childComponent: TopComponent;
  constructor(
    private router:Router,
    private configService:ConfigService,
  ) {}

  ngOnInit(): void {
    if(localStorage.getItem('email')=='' || localStorage.getItem('email')==undefined
    ||localStorage.getItem('isLoggedIn')=='false'){
      console.log('logged out');
      this.router.navigateByUrl('/login');
      return;
    }
    console.log('Home component')
    this.name = "";    
    this.data ={
      "email":localStorage.getItem('email')
    }
    this.configService.loadHomeContent(this.data).subscribe(
      res => {        
        this.name = res.accountMasterBO[0].name;
        localStorage.setItem('name',this.name);
        this.configService.setOverAllSharedData(res);
        this.childComponent.loadHeader(res.headerResponseBO);
      });      
  }
  findFriends(){    
    this.router.navigateByUrl('/findFriends');
    console.log('calling after router callesddddddddddd')
  }    
}               
                  