package com.media.service;

import org.springframework.context.ApplicationContext;

import com.media.bo.AccountMasterBO;
import com.media.bo.FriendRequestMasterBO;
import com.media.bo.FriendsMasterBO;
import com.media.bo.MessageBO;
import com.media.bo.ResponseBO;
import com.media.daoimpl.FriendDAOImpl;
import com.media.daoimpl.HeaderDAOImpl;
import com.media.daoimpl.MessageDAOImpl;

public class FriendService {

	public ResponseBO deleteFriendRequest(ApplicationContext applicationContext, FriendRequestMasterBO friendRequestMasterBO) {
		FriendDAOImpl friendDAOImpl= (FriendDAOImpl)applicationContext.getBean("friendDAOImpl");
		return friendDAOImpl.deleteFriendRequest(friendRequestMasterBO);
		
	}

	public ResponseBO acceptFriendRequest(ApplicationContext applicationContext, FriendsMasterBO friendsMasterBO) {
		ResponseBO responseBO = new ResponseBO();
		MessageBO messageBO = new MessageBO();
		FriendDAOImpl friendDAOImpl= (FriendDAOImpl)applicationContext.getBean("friendDAOImpl");
		MessageDAOImpl messageDAOImpl= (MessageDAOImpl)applicationContext.getBean("messageDAOImpl");
		responseBO = friendDAOImpl.acceptFriendRequest(friendsMasterBO);
		if(responseBO.isValid()) {
			messageBO.setFromUser(friendsMasterBO.getFriendEmail());
			messageBO.setToUser(friendsMasterBO.getUserEmail());
			messageBO.setMessage("");
			messageDAOImpl.sendMessage(messageBO);
		}
		return responseBO;
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
