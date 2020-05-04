package com.media.dao;

import com.media.bo.AccountMasterBO;
import com.media.bo.FriendRequestMasterBO;
import com.media.bo.ResponseBO;

public interface UserDAO {
	public abstract boolean signup(AccountMasterBO userSignupFormBO);
	public abstract ResponseBO signin(AccountMasterBO userSignupFormBO);
	public abstract ResponseBO findFriends(AccountMasterBO userSignupFormBO);
	boolean addFriend(FriendRequestMasterBO friendRequestMasterBO);
}
