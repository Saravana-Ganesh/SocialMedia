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
  confirmFriendRequestID='';
  deleteFriendRequestID='';
  friendRequestExist = false;
  viewFriendRequest = false;
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
        this.changeBO(res.headerResponseBO.friendRequestMasterBO);
        this.result = res.accountMasterBO;
        this.friendRequestCount = res.headerResponseBO.friendRequestMasterBO.length;
        this.childComponent.loadHeader(res.headerResponseBO);
        if(this.friendRequestCount!=0){
          this.friendRequestExist = true;
        }
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
  deleteRequest(email,requestID){
    this.data={
      "id":requestID,
      "fromUser":email,
      "toUser": localStorage.getItem('email'),
      "isDelete":1
    }
    this.deleteFriendRequestID = requestID+'_delete';
    this.confirmFriendRequestID = requestID+'_accept';
    return this.configService.deleteFriendRequest(JSON.stringify(this.data)).subscribe(
      res => {
        var link = document.getElementById(this.deleteFriendRequestID);
        link.style.visibility = 'hidden';
        document.getElementById(this.confirmFriendRequestID).innerHTML = "Request Cancelled"; 
      });
  }
  confirmRequest(email,requestID){
    this.deleteFriendRequestID = requestID+'_delete';
    this.confirmFriendRequestID = requestID+'_accept';  
    if(document.getElementById(this.confirmFriendRequestID).textContent!=this.buttonTextConfirm){
        console.log('Invalid confirm request');
        return;
    }
    this.data={
      "id":requestID,
      "userEmail":localStorage.getItem('email'),
      "friendEmail":email
    }
    return this.configService.acceptFriendRequest(JSON.stringify(this.data)).subscribe(
      res=>{
        var link = document.getElementById(this.deleteFriendRequestID);
         link.style.visibility = 'hidden';
         document.getElementById(this.confirmFriendRequestID).innerHTML = "Friends"; 
      }
    )
  }
  viewSentRequests(){
    this.friendRequestExist = false;
    this.viewFriendRequest = true;
    this.data={
      email:localStorage.getItem('email')
    }
    return this.configService.viewSentRequests(JSON.stringify(this.data)).subscribe(
      res=>{
        console.log(res);
        res = res.accountMasterBO;
        this.changeBO(res);
      }
    )
  }
  changeBO(rawdata){
    //This method converts raw two dimensional array to JSON array
    for(let i=0;i<rawdata.length;i++){
      this.friendRequestData.push({
        "requestID":rawdata[i][0],
        "name":rawdata[i][1],
        "email":rawdata[i][2]
      })
    }
    console.log(this.friendRequestData);
  }

}
