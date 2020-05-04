package com.media.service;

import org.springframework.context.ApplicationContext;

import com.media.bo.ResponseBO;
import com.media.dao.HomePageDAO;
import com.media.daoimpl.HeaderDAOImpl;
import com.media.daoimpl.HomePageDAOImpl;

public class HomePageService {

	public ResponseBO viewHomeContent(String email, ApplicationContext applicationContext) {
		ResponseBO responseBO = new ResponseBO();
		HeaderDAOImpl headerDAOImpl = new HeaderDAOImpl();

		HomePageDAO homePageDAO = (HomePageDAOImpl)applicationContext.getBean("homePageDAOImpl");		
		responseBO =  homePageDAO.getHomeContent(email);
		
		responseBO.setHeaderResponseBO(headerDAOImpl.getTopContent(email));
		
		return responseBO;
	}
	
}
