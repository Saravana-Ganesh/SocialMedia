package com.media.service;

import org.springframework.context.ApplicationContext;

import com.media.bo.UserSignupFormBO;
import com.media.daoimpl.UserDAOImpl;

public class UserService {
	
	public boolean signup(UserSignupFormBO userSignupFormBO,ApplicationContext applicationContext) {
		UserDAOImpl userDAOImpl = (UserDAOImpl)applicationContext.getBean("userDAOImpl");
		return userDAOImpl.signup(userSignupFormBO);
	}
}
