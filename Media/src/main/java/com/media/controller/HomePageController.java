package com.media.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.media.bo.AccountMasterBO;
import com.media.bo.ResponseBO;
import com.media.service.HomePageService;

@RestController
public class HomePageController {
	@PostMapping(value="/home",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBO signup(@RequestBody AccountMasterBO userSignupFormBO) {
		String email = userSignupFormBO.getEmail();
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		HomePageService homePageService= (HomePageService)applicationContext.getBean("homePageService");
		return homePageService.viewHomeContent(email,applicationContext); 
	}
}
