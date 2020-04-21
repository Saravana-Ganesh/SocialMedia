package com.media.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.media.bo.UserSignupFormBO;
import com.media.service.UserService;



@RestController
public class UserController {
	@PostMapping(value="/signup",produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean signup(@RequestBody UserSignupFormBO userSignupFormBO) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService= (UserService)applicationContext.getBean("userService");
		return userService.signup(userSignupFormBO,applicationContext); 
	}
	@PostMapping(value="/signin",produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean signin(@RequestBody UserSignupFormBO userSignupFormBO) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService= (UserService)applicationContext.getBean("userService");
		return userService.signin(userSignupFormBO,applicationContext); 
	}
}          
         