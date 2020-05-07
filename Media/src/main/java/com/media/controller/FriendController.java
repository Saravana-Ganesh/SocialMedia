package com.media.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.media.bo.AccountMasterBO;
import com.media.bo.FriendRequestMasterBO;
import com.media.bo.FriendsMasterBO;
import com.media.bo.ResponseBO;
import com.media.service.FriendService;

@RestController
public class FriendController {
	@PostMapping(value="/deleteFriendRequest",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBO deleteFriendRequest(@RequestBody FriendRequestMasterBO friendRequestMasterBO) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		FriendService friendService= (FriendService)applicationContext.getBean("friendService");
		return friendService.deleteFriendRequest(applicationContext,friendRequestMasterBO); 
	}
	
	@PostMapping(value="/acceptFriendRequest",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBO acceptFriendRequest(@RequestBody FriendsMasterBO friendsMasterBO) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		FriendService friendService= (FriendService)applicationContext.getBean("friendService");
		return friendService.acceptFriendRequest(applicationContext,friendsMasterBO); 
	}
	
	@PostMapping(value="/viewSentRequests",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBO viewSentRequests(@RequestBody AccountMasterBO accountMasterBO) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		FriendService friendService= (FriendService)applicationContext.getBean("friendService");
		return friendService.viewSentRequests(applicationContext,accountMasterBO); 
	}
}
