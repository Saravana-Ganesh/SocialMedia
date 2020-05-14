import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ConfigService } from '../services/config.service';
import { TopComponent } from '../top/top.component';
import { Observable, Subscription } from 'rxjs';
import { interval} from 'rxjs';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {
  name:string;
  data:any;
  myEmail:string;
  val:string;
  public friendNameInChat:string;
  public friendEmailInChat:string;
  @ViewChild(TopComponent) childComponent: TopComponent;
  chatSideContent=[];
  messageContent=[];
  container: HTMLElement;     
  constructor( 
    private router:Router,
    private configService:ConfigService,
    ) { }

  ngOnInit(): void {
    if(localStorage.getItem('email')=='' || localStorage.getItem('email')==undefined
    ||localStorage.getItem('isLoggedIn')=='false'){
      console.log('logged out');
      this.router.navigateByUrl('/login');
    }else{
      this.loadMessageContent();
     
    }
  
  }
  ngAfterViewInit(): void{
    this.loadOneToOneChatMessage();                                     
  }

  ngAfterViewChecked():void{
   
    console.log('called ngAfterViewChecked')
    var objDiv = document.getElementById('msgContainer');
    objDiv.scrollTop = objDiv.scrollHeight;    
  }

  loadMessageContent(){
    this.name = localStorage.getItem('name');
      this.myEmail = localStorage.getItem('email');
        this.data = {
          "email":localStorage.getItem('email')
        };
        this.configService.viewAllMessage(JSON.stringify(this.data)).subscribe(
          res=>{    
            console.log(res);
            this.drawchatSidePanel(res.results);
            this.childComponent.loadHeader(res.headerResponseBO);
          }
        );
  }
  drawchatSidePanel(data){
    this.chatSideContent=[];
    for(let i=0;i<data.length;i++){
      this.chatSideContent.push({
        "email":data[i][0],
        "name":data[i][1],
        "message":data[i][2],
        "dateTime":data[i][3],
        "readStatus":data[i][4]
      });
    }
    console.log(this.chatSideContent);
    this.friendEmailInChat = this.chatSideContent[0].email;
    this.friendNameInChat = this.chatSideContent[0].name;
    localStorage.setItem('friendEmailInChat',this.friendEmailInChat);
  }
  drawMessagePanel(data){
    this.messageContent=[];
    for(let i=0;i<data.length;i++){
      this.messageContent.push({
        "messageId":data[i][0],
        "fromUserEmail":data[i][1],
        "fromUserName":data[i][2],
        "message":data[i][5],
        "dateTime":data[i][6]
      });
    }   
    console.log(this.messageContent);    
  }

  viewchatMessage(email,name){
    this.friendNameInChat = name;
    this.friendEmailInChat=email;
    localStorage.setItem('friendEmailInChat',email);
    console.log('------------'+email)
    this.loadOneToOneChatMessage();
  }

  loadOneToOneChatMessage(){
    console.log('----------------------'+localStorage.getItem('friendEmailInChat')+'-----------')
    this.data={
      "fromUser":localStorage.getItem('email'),
      "toUser":localStorage.getItem('friendEmailInChat')
    }
    this.configService.viewchatMessage(JSON.stringify(this.data)).subscribe(
      res=>{
        this.drawMessagePanel(res.results);
      }
    );
  }

  sendMessage(message){
    if(message!=''&&message!=undefined&&message!=null){
      this.val='';
      console.log(message);
      this.data={
        "fromUser":localStorage.getItem('email'),
        "toUser":this.friendEmailInChat,
        "message":message
      }
      this.configService.sendMessage(JSON.stringify(this.data)).subscribe(
        res=>{
          console.log(res);
        }
      );
    }    
    this.val='';
    var objDiv = document.getElementById('msgContainer');
    objDiv.scrollTop = objDiv.scrollHeight;
  }
  // interval(500000).subscribe(x => this.loadMessageContent());
    // interval(500000).subscribe(x => this.loadOneToOneChatMessage());
}
