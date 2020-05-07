package com.media.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.media.constants.TableConstants;

@Entity
@Table(name = TableConstants.FRIEND_REQUEST_MASTER,schema="SYSTEM")
public class FriendRequestMasterBO {
	@Id
	@Column(name = "ID",unique=true)
	private int id = 0;
	
	@Column(name = "FROM_USER")
	private String fromUser;
		
	@Column(name = "TO_USER")
	private String toUser;
	
	@Column(name = "STATUS")
	private int status;
	
	@Column(name = "IS_DELETE")
	private int isDelete;
	
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
}
