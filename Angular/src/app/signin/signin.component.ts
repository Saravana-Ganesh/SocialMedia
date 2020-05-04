import { Component, OnInit } from '@angular/core';
import { Signup } from '../signup';
import { ConfigService } from '../services/config.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  public invalidPassword = true;
  constructor(
    private configService:ConfigService,
    private router:Router
  
  ) { }
 
  model = new Signup('-1','','-1','-1','','-1')
  ngOnInit(): void {
  }
  onSubmit(){
    console.log(JSON.stringify(this.model));     
    return this.configService.submitSignin(JSON.stringify(this.model)).subscribe(
      res => {
        //this.configService.overAllSharedData = res;
        console.log(res);
        this.invalidPassword = res.valid;
        if(this.invalidPassword==true){
            localStorage.setItem('email',this.model.email)
            localStorage.setItem('isLoggedIn','true');
            this.router.navigate(['home']);
        }
      });
    
  }

}
