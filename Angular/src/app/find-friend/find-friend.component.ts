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
  friendRequestPage = false;
  friendRequestExist = false;
  viewSentFriendRequest = false;
  viewSentFriendRequestExist = false;
  @ViewChild(TopComponent) childComponent: TopComponent;
  constructor(
    private configService:ConfigService,
  ) { }

  ngOnInit(): void {
    this.viewSentFriendRequest = false;
    window.addEventListener('scroll', this.scroll, true); //third parameter
    this.header = document.getElementById("myheader");
    this.sticky = this.header.offsetTop;
    this.data = {email:localStorage.getItem('email')};
    this.configService.findFriends(JSON.stringify(this.data)).subscribe(
      res =>{
        this.friendRequestPage = true;
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
    this.deleteFriendRequestID = requestID+'_delete';
    this.confirmFriendRequestID = requestID+'_accept';
    if(document.getElementById(this.deleteFriendRequestID).textContent=="Request Cancelled"){
      console.log('Invalid confirm request');
      return;
  }
    if(this.friendRequestPage==false){
      this.data={
        "id":requestID,
        "fromUser":email,
        "toUser": localStorage.getItem('email'),
        "isDelete":1
      }
    }else{
      this.data={
        "id":requestID,
        "fromUser":localStorage.getItem('email'),
        "toUser": email,
        "isDelete":1
      }
    }        
    return this.configService.deleteFriendRequest(JSON.stringify(this.data)).subscribe(
      res => {
        if(this.friendRequestPage==false){
          document.getElementById(this.deleteFriendRequestID).innerHTML = "Request Cancelled";
        }else{
            var link = document.getElementById(this.deleteFriendRequestID);
            link.style.visibility = 'hidden';
            document.getElementById(this.confirmFriendRequestID).innerHTML = "Request Cancelled"; 
        }        
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
    this.friendRequestPage = false;
    this.friendRequestExist = false;
    this.viewSentFriendRequest = true;
    this.data={
      email:localStorage.getItem('email')
    }
    return this.configService.viewSentRequests(JSON.stringify(this.data)).subscribe(
      res=>{
        res = res.accountMasterBO;
        this.changeBO(res);
        if(res.length>0){
          this.viewSentFriendRequestExist = true;
        }
      }
    )
  }
  changeBO(rawdata){
    this.friendRequestData=[];
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
