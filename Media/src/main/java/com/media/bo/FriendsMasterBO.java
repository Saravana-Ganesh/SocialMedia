package com.media.bo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.media.constants.TableConstants;

@Entity
@Table(name = TableConstants.FRIENDS_MASTER,schema="SYSTEM")
public class FriendsMasterBO {	
	@Id
	@Column(name = "ID",unique=true)
	private String id;
	
	@Column(name="USER_EMAIL")
	private String userEmail;
	
	@Column(name="FRIEND_EMAIL")
	private String friendEmail;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getFriendEmail() {
		return friendEmail;
	}
	public void setFriendEmail(String friendEmail) {
		this.friendEmail = friendEmail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
