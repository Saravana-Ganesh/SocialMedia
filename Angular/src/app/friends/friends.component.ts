import { Component, OnInit, ViewChild } from '@angular/core';
import { ConfigService } from '../services/config.service';
import { TopComponent } from '../top/top.component';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {
  header:any={};
  sticky:any={};
  data:any;
  result:any;
  friendsLabel = "Friends";
  friendsCount:number;
  @ViewChild(TopComponent) childComponent: TopComponent;
  constructor(
    private configService:ConfigService,
  ) { }

  ngOnInit(): void {
    window.addEventListener('scroll', this.scroll, true); //third parameter
    this.header = document.getElementById("myheader");
    this.sticky = this.header.offsetTop;
    this.data = {email:localStorage.getItem('email')};
    this.configService.viewFriends(JSON.stringify(this.data)).subscribe(
      res =>{
        console.log(res);
        this.childComponent.loadHeader(res.headerResponseBO);
        this.processResponse(res.results,res.mutualFriends);
      });
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
  }
  processResponse(rawdata,mutualFriends){
    let content = " mutual friends";
    let contentContainMutualFriend = " is a mutual friend";
    this.result = [];
    for(let i=0;i<rawdata.length;i++){
      this.result.push({
        "requestID":rawdata[i][0],
        "name":rawdata[i][1],
        "email":rawdata[i][2],
        "mutualFriends":mutualFriends[i].length,
        "mutualFriendExist":mutualFriends[i].length>0,
        "mutualFriendText":(mutualFriends[i].length>1?mutualFriends[i].length+content:(mutualFriends[i].length==1?mutualFriends[i][0][1]+contentContainMutualFriend:'No Mutual Friends Yet'))
      });
    }
    this.friendsCount = this.result.length;
    console.log(this.result);
  }
}
