package com.media.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.media.constants.TableConstants;

@Entity
@Table(name = TableConstants.MESSAGE_MASTER,schema="SYSTEM")
public class MessageBO {
	@Id
	@Column(name = "ID",unique=true)
	private int id;
	@Column(name = "FROM_USER")
	private String fromUser;
	@Column(name = "TO_USER")
	private String toUser;
	@Column(name = "MESSAGE")
	private String message;
	@Column(name = "Date_Time")
	@Temporal(value= TemporalType.TIMESTAMP)
	private Date dateTime;
	@Column(name = "STATUS")
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
