package com.media.helper;

import com.media.bo.AccountMasterBO;

public class QueryHelper {
	public static String prepareHQLFriendRequestQuery(String email) {
		//To get the friend request list for that user
		return " select AM.name, FRM.fromUser from FriendRequestMasterBO FRM inner join AccountMasterBO AM " + 
				"on  AM.email = FRM.fromUser" + 
				" where FRM.toUser = '"+email+"' and status = 0  and AM.isActive=1";
	}
	
	public static String prepareHQLFriendRequestQuery(AccountMasterBO userSignupFormBO){		
		return " select FRM.fromUser from FriendRequestMasterBO FRM inner join AccountMasterBO AM " + 
				" on AM.email = FRM.fromUser" + 
				" where FRM.toUser = '"+userSignupFormBO.getEmail()+"' and status = 0  and AM.isActive=1";
	}
	
	public static String prepareHQLQuery(AccountMasterBO userSignupFormBO) {
		return "select AM from AccountMasterBO AM   where AM.email not"
				+ " in(select friendEmail from "
				+ "com.media.bo.FriendsMasterBO FM where FM.userEmail='"+userSignupFormBO.getEmail()+"') and"
			    + " AM.email not in (select toUser from com.media.bo.FriendRequestMasterBO frm where  "
			    + "frm.fromUser='"+userSignupFormBO.getEmail()+"') and AM.email "
			    + "not in (select fromUser from com.media.bo.FriendRequestMasterBO frm where "
			    + "frm.toUser='"+userSignupFormBO.getEmail()+"') and AM.email!='"+userSignupFormBO.getEmail()+"' "
			    +"  and AM.isActive=1";
	}
	
	public static String deleteFriendRequest() {
		return "delete from FriendRequestMasterBO where fromUser=:from_user" + 
				"and toUser =:to_user";
	}
}
