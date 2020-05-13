package com.media.service;

import org.springframework.context.ApplicationContext;

import com.media.bo.AccountMasterBO;
import com.media.bo.FriendRequestMasterBO;
import com.media.bo.FriendsMasterBO;
import com.media.bo.ResponseBO;
import com.media.daoimpl.FriendDAOImpl;
import com.media.daoimpl.HeaderDAOImpl;

public class FriendService {

	public ResponseBO deleteFriendRequest(ApplicationContext applicationContext, FriendRequestMasterBO friendRequestMasterBO) {
		FriendDAOImpl friendDAOImpl= (FriendDAOImpl)applicationContext.getBean("friendDAOImpl");
		return friendDAOImpl.deleteFriendRequest(friendRequestMasterBO);
		
	}

	public ResponseBO acceptFriendRequest(ApplicationContext applicationContext, FriendsMasterBO friendsMasterBO) {
		FriendDAOImpl friendDAOImpl= (FriendDAOImpl)applicationContext.getBean("friendDAOImpl");
		return friendDAOImpl.acceptFriendRequest(friendsMasterBO);
	}

	public ResponseBO viewSentRequests(ApplicationContext applicationContext,AccountMasterBO accountMasterBO) {
		FriendDAOImpl friendDAOImpl= (FriendDAOImpl)applicationContext.getBean("friendDAOImpl");
		return friendDAOImpl.viewFriendRequests(accountMasterBO);
	}

	public ResponseBO viewFriends(ApplicationContext applicationContext, AccountMasterBO accountMasterBO) {
		ResponseBO responseBO = new ResponseBO();
		FriendDAOImpl friendDAOImpl= (FriendDAOImpl)applicationContext.getBean("friendDAOImpl");
		HeaderDAOImpl headerDAOImpl= (HeaderDAOImpl)applicationContext.getBean("headerDAOImpl");
		responseBO = friendDAOImpl.viewFriends(accountMasterBO);
		responseBO.setHeaderResponseBO(headerDAOImpl.getTopContent(accountMasterBO.getEmail()));
		return responseBO;
	}

}
