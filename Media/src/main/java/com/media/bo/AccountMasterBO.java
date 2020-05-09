package com.media.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.media.constants.TableConstants;

@Entity
@Table(name = TableConstants.ACCOUNT_MASTER,schema="SYSTEM")
public class AccountMasterBO {
	@Id
	@Column(name = "Email", nullable = false,unique=true)
	private String email;
	@Column(name = "UserName")
	private String name;
	@Column(name = "CountryCode")
	private String code;
	@Column(name = "PhoneNumber")
	private String phone;
	@Column(name = "Password")
	private int password;
	@Column(name="isActive")
	private int isActive=1;
	@Column(name="userid")
	private Integer id=1;
	
	
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getIsActive() {
		return isActive;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password.hashCode();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
