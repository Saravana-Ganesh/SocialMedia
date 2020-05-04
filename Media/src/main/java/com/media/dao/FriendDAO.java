package com.media.dao;

import com.media.bo.FriendRequestMasterBO;
import com.media.bo.ResponseBO;

public interface FriendDAO {

	ResponseBO deleteFriendRequest(FriendRequestMasterBO friendRequestMasterBO);

}
