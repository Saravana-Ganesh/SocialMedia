package com.media.service;

import org.springframework.context.ApplicationContext;

import com.media.bo.AccountMasterBO;
import com.media.bo.FriendRequestMasterBO;
import com.media.bo.HeaderResponseBO;
import com.media.bo.ResponseBO;
import com.media.daoimpl.HeaderDAOImpl;
import com.media.daoimpl.UserDAOImpl;

public class UserService {
	
	public boolean signup(AccountMasterBO userSignupFormBO,ApplicationContext applicationContext) {
		UserDAOImpl userDAOImpl = (UserDAOImpl)applicationContext.getBean("userDAOImpl");
		return userDAOImpl.signup(userSignupFormBO);
	}
	
	public ResponseBO signin(AccountMasterBO userSignupFormBO,ApplicationContext applicationContext) {
		UserDAOImpl userDAOImpl = (UserDAOImpl)applicationContext.getBean("userDAOImpl");
		return userDAOImpl.signin(userSignupFormBO);
	}
	
	public ResponseBO findFriends(AccountMasterBO userSignupFormBO,ApplicationContext applicationContext) {
		HeaderDAOImpl headerDAOImpl = new HeaderDAOImpl();
		ResponseBO responseBO = new ResponseBO();
		UserDAOImpl userDAOImpl = (UserDAOImpl)applicationContext.getBean("userDAOImpl");

		responseBO =  userDAOImpl.findFriends(userSignupFormBO);
		responseBO.setHeaderResponseBO(headerDAOImpl.getTopContent(userSignupFormBO.getEmail()));
		
		return responseBO;
	}

	public boolean addFriend(FriendRequestMasterBO friendRequestMasterBO, ApplicationContext applicationContext) {
		UserDAOImpl userDAOImpl = (UserDAOImpl)applicationContext.getBean("userDAOImpl");
		return userDAOImpl.addFriend(friendRequestMasterBO);
	}
}     
