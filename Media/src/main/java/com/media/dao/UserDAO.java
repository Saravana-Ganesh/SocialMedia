package com.media.dao;

import com.media.bo.UserSignupFormBO;

public interface UserDAO {
	public abstract boolean signup(UserSignupFormBO userSignupFormBO);
}
