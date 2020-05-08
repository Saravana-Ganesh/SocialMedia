package com.media.service;

import org.springframework.context.ApplicationContext;

import com.media.bo.AccountMasterBO;
import com.media.bo.FriendRequestMasterBO;
import com.media.bo.FriendsMasterBO;
import com.media.bo.ResponseBO;
import com.media.daoimpl.FriendDAOImpl;

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
		FriendDAOImpl friendDAOImpl= (FriendDAOImpl)applicationContext.getBean("friendDAOImpl");
		return friendDAOImpl.viewFriends(accountMasterBO);
	}

}
