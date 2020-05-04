package com.media.service;

import org.springframework.context.ApplicationContext;

import com.media.bo.FriendRequestMasterBO;
import com.media.bo.ResponseBO;
import com.media.daoimpl.FriendDAOImpl;

public class FriendService {

	public ResponseBO deleteFriendRequest(ApplicationContext applicationContext, FriendRequestMasterBO friendRequestMasterBO) {
		FriendDAOImpl friendDAOImpl= (FriendDAOImpl)applicationContext.getBean("friendDAOImpl");
		return friendDAOImpl.deleteFriendRequest(friendRequestMasterBO);
		
	}

}
