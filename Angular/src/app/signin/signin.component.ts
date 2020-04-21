import { Component, OnInit } from '@angular/core';
import { Signup } from '../signup';
import { ConfigService } from '../services/config.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  public invalidPassword = true;
  constructor(
    private configService:ConfigService
  
  ) { }
 
  model = new Signup('-1','','-1','-1','','-1')
  ngOnInit(): void {
  }
  onSubmit(){
    console.log(JSON.stringify(this.model));     
    return this.configService.submitSignin(JSON.stringify(this.model)).subscribe(
      res => {
        console.log(res);
        this.invalidPassword = res;
      });
    
  }

}
