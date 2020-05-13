package com.media.service;

import org.springframework.context.ApplicationContext;

import com.media.bo.MessageBO;
import com.media.bo.ResponseBO;
import com.media.daoimpl.HeaderDAOImpl;
import com.media.daoimpl.MessageDAOImpl;

public class MessageService {

	public ResponseBO viewAllMessage(String email, ApplicationContext applicationContext) {
		ResponseBO responseBO = new ResponseBO();
		HeaderDAOImpl headerDAOImpl = (HeaderDAOImpl)applicationContext.getBean("headerDAOImpl");
		MessageDAOImpl messageDAOImpl = (MessageDAOImpl)applicationContext.getBean("messageDAOImpl");
		responseBO = messageDAOImpl.viewAllMessage(email);
		responseBO.setHeaderResponseBO(headerDAOImpl.getTopContent(email));		
		return responseBO;
	}

	public ResponseBO viewchatMessage(MessageBO messageBO, ApplicationContext applicationContext) {
		ResponseBO responseBO = new ResponseBO();
		MessageDAOImpl messageDAOImpl = (MessageDAOImpl)applicationContext.getBean("messageDAOImpl");
		responseBO = messageDAOImpl.viewchatMessage(messageBO);
		return responseBO;
	}

	public ResponseBO sendMessage(MessageBO messageBO, ApplicationContext applicationContext) {
		ResponseBO responseBO = new ResponseBO();
		MessageDAOImpl messageDAOImpl = (MessageDAOImpl)applicationContext.getBean("messageDAOImpl");
		responseBO = messageDAOImpl.sendMessage(messageBO);
		return responseBO;
	}

}
