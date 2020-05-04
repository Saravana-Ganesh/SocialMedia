package com.media.dao;

import java.util.List;

import com.media.bo.FriendRequestMasterBO;
import com.media.bo.HeaderResponseBO;

public interface HeaderDAO {

	HeaderResponseBO getTopContent(String email);

	List<FriendRequestMasterBO> getFriendRequestContent(String email);

}
