package com.media.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.media.bo.FriendRequestMasterBO;
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
}
