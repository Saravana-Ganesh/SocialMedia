package com.media.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.media.bo.AccountMasterBO;
import com.media.bo.FriendRequestMasterBO;
import com.media.bo.ResponseBO;
import com.media.service.UserService;



@RestController
public class UserController {	
	@PostMapping(value="/signup",produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean signup(@RequestBody AccountMasterBO userSignupFormBO) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService= (UserService)applicationContext.getBean("userService");
		return userService.signup(userSignupFormBO,applicationContext); 
	}
	@PostMapping(value="/signin",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBO signin(@RequestBody AccountMasterBO userSignupFormBO) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService= (UserService)applicationContext.getBean("userService");
		return userService.signin(userSignupFormBO,applicationContext); 
	}
	@PostMapping(value="/findFriends",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBO findFriends(@RequestBody AccountMasterBO userSignupFormBO) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(userSignupFormBO.getEmail());
		UserService userService= (UserService)applicationContext.getBean("userService");
		return userService.findFriends(userSignupFormBO, applicationContext);
	}
	@PostMapping(value="/addFriend",produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean addFriend(@RequestBody FriendRequestMasterBO friendRequestMasterBO) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService= (UserService)applicationContext.getBean("userService");
		return userService.addFriend(friendRequestMasterBO, applicationContext);
	}   
}     

         