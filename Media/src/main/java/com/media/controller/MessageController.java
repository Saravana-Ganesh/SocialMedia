package com.media.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.media.bo.AccountMasterBO;
import com.media.bo.MessageBO;
import com.media.bo.ResponseBO;
import com.media.service.HomePageService;
import com.media.service.MessageService;

@RestController
public class MessageController {
	@PostMapping(value="/viewAllMessage",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBO viewAllMessage(@RequestBody AccountMasterBO userSignupFormBO) {
		String email = userSignupFormBO.getEmail();
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		MessageService messageService= (MessageService)applicationContext.getBean("messageService");
		return messageService.viewAllMessage(email,applicationContext); 
	}
	
	@PostMapping(value="/viewchatMessage",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBO viewchatMessage(@RequestBody MessageBO messageBO) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		MessageService messageService= (MessageService)applicationContext.getBean("messageService");
		return messageService.viewchatMessage(messageBO,applicationContext); 
	}
	
	@PostMapping(value="/sendMessage",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBO sendMessage(@RequestBody MessageBO messageBO) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		MessageService messageService= (MessageService)applicationContext.getBean("messageService");
		return messageService.sendMessage(messageBO,applicationContext); 
	}
}
