import { Component, OnInit, ViewChild } from '@angular/core';
import { ConfigService } from '../services/config.service';
import { TopComponent } from '../top/top.component';

@Component({
  selector: 'app-find-friend',
  templateUrl: './find-friend.component.html',
  styleUrls: ['./find-friend.component.css']
})
export class FindFriendComponent implements OnInit {
  header:any={};
  sticky:any={};
  data:any;
  result:any;
  buttonTextAddFriend = "Add Friend";
  buttonTextConfirm = "Confirm";
  buttonTextDelete = "Delete"
  friendRequestCount : number;
  friendRequestData =[];
  @ViewChild(TopComponent) childComponent: TopComponent;
  constructor(
    private configService:ConfigService,
  ) { }

  ngOnInit(): void {
    window.addEventListener('scroll', this.scroll, true); //third parameter
    this.header = document.getElementById("myheader");
    this.sticky = this.header.offsetTop;
    this.data = {email:localStorage.getItem('email')};
    this.configService.findFriends(JSON.stringify(this.data)).subscribe(
      res =>{
        console.log(res);
        this.changeBO(res);
        this.result = res.accountMasterBO;
        this.friendRequestCount = res.headerResponseBO.friendRequestMasterBO.length;
        this.childComponent.loadHeader(res.headerResponseBO);
      }
    );
  }
  scroll = (event): void => {
    //handle your scroll here
    //notice the 'odd' function assignment to a class field
    //this is used to be able to remove the event listener
    console.log('Scroll event called');
    if (window.pageYOffset > this.sticky) {
      this.header.classList.add("sticky");
    } else {
      this.header.classList.remove("sticky");
    }
  };
  addFriend(email){
    
    console.log(email);
    this.data = {
      "fromUser":localStorage.getItem('email'),
      "toUser":email
    }
    return this.configService.addFriend(JSON.stringify(this.data)).subscribe(
      res => {
        console.log(res); 
        if(res==true)  {
          document.getElementById(email).innerHTML = "Friend Request Sent";     
        }        
      });
    
  }
  changeBO(rawdata){
    rawdata = rawdata.headerResponseBO.friendRequestMasterBO;
    for(let i=0;i<rawdata.length;i++){
      this.friendRequestData.push({
        "name":rawdata[i][0],
        "email":rawdata[i][1]
      })
    }
    console.log(this.friendRequestData);
  }

}
