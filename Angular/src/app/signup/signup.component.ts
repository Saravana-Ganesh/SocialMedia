import { Component, OnInit } from '@angular/core';
import {FormsModule} from '@angular/forms';

import {Signup} from '../signup';
import {ConfigService} from '../services/config.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  countryCodes = ['+91','+97','+98','+92']
  model = new Signup('','','','','','')
  submitted = false;
  passwordMatch = false;
  constructor(
    private configService:ConfigService
  ) { }

  ngOnInit(): void {
  }
  checkPassword(){
    if(this.model.password!=this.model.confirmPassword){
      return this.passwordMatch = true;
    }
  } 
  onSubmit(){
    if(this.checkPassword()){
      return false;
    }
    this.submitted = true;
    console.log(JSON.stringify(this.model));     
    return this.configService.submitSignup(JSON.stringify(this.model)).subscribe(
      res => {
        console.log(res);
      });
    
  }
  get diagnostic() { 
    return JSON.stringify(this.model);
   }
   

}
