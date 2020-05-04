package com.media.bo;

import java.util.List;

public class ResponseBO {
	private boolean isValid = false;
	
	private List<AccountMasterBO> accountMasterBO;
	private List<FriendRequestMasterBO> friendRequestMasterBO;
	
	private HeaderResponseBO headerResponseBO;


	public List<AccountMasterBO> getAccountMasterBO() {
		return accountMasterBO;
	}

	public void setAccountMasterBO(List<AccountMasterBO> accountMasterBO) {
		this.accountMasterBO = accountMasterBO;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public List<FriendRequestMasterBO> getFriendRequestMasterBO() {
		return friendRequestMasterBO;
	}

	public void setFriendRequestMasterBO(List<FriendRequestMasterBO> friendRequestMasterBO) {
		this.friendRequestMasterBO = friendRequestMasterBO;
	}

	public HeaderResponseBO getHeaderResponseBO() {
		return headerResponseBO;
	}

	public void setHeaderResponseBO(HeaderResponseBO headerResponseBO) {
		this.headerResponseBO = headerResponseBO;
	}
}
