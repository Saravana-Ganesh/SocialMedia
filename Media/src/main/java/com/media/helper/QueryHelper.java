package com.media.helper;

import com.media.bo.AccountMasterBO;

public class QueryHelper {
	public static String prepareHQLFriendRequestQuery(String email) {
		//To get the friend request list for that user
		return " select FRM.id, AM.name, FRM.fromUser from FriendRequestMasterBO FRM inner join AccountMasterBO AM " + 
				"on  AM.email = FRM.fromUser" + 
				" where FRM.toUser = '"+email+"' and status = 0 and is_delete=0 and AM.isActive=1";
	}
	
	public static String prepareHQLFriendRequestQuery(AccountMasterBO userSignupFormBO){	
		//To get the friend request list for that user
		return " select FRM.id, FRM.fromUser from FriendRequestMasterBO FRM inner join AccountMasterBO AM " + 
				" on AM.email = FRM.fromUser" + 
				" where FRM.toUser = '"+userSignupFormBO.getEmail()+"' and status = 0  and is_delete=0 and AM.isActive=1";
	}
	
	public static String prepareHQLQuery(AccountMasterBO userSignupFormBO) {
		//Query for people you may know
		String a   = "select AM from AccountMasterBO AM   where AM.email not"
				+ " in(select friendEmail from "
				+ "com.media.bo.FriendsMasterBO FM where FM.userEmail='"+userSignupFormBO.getEmail()+"') and"
			    + " AM.email not in (select toUser from com.media.bo.FriendRequestMasterBO frm where  "
			    + "frm.fromUser='"+userSignupFormBO.getEmail()+"' and frm.isDelete=0) and AM.email "
			    + "not in (select fromUser from com.media.bo.FriendRequestMasterBO frm where "
			    + "frm.toUser='"+userSignupFormBO.getEmail()+"' and frm.isDelete=0) and AM.email!='"+userSignupFormBO.getEmail()+"' "
			    +"  and AM.isActive=1";
		System.out.println(a);
		return "select AM from AccountMasterBO AM   where AM.email not"
				+ " in(select friendEmail from "
				+ "com.media.bo.FriendsMasterBO FM where FM.userEmail='"+userSignupFormBO.getEmail()+"') and"
			    + " AM.email not in (select toUser from com.media.bo.FriendRequestMasterBO frm where  "
			    + " frm.fromUser='"+userSignupFormBO.getEmail()+"' and frm.isDelete=0) and AM.email "
			    + " not in (select fromUser from com.media.bo.FriendRequestMasterBO frm where "
			    + " frm.toUser='"+userSignupFormBO.getEmail()+"' and frm.isDelete=0) and AM.email!='"+userSignupFormBO.getEmail()+"' "
			    +"  and AM.isActive=1";
	}
	
	public static String deleteFriendRequest() {
		return "delete from FriendRequestMasterBO where fromUser=:from_user" + 
				" and toUser=:to_user";
	}
	
	public static String acceptFriendRequest() {
		//Query for update status in friend_request_master
		return "update FriendRequestMasterBO set status=1 where id=:id"; 

	}
	
	public static String viewFriendRequest() {
		//Query for view friend requests
		return "" + 
		" select frm.id as id,  AM.name as name , AM.email as email "
		+ " from FriendRequestMasterBO frm inner join  AccountMasterBO AM  " + 
		" on frm.toUser = AM.email " + 
		" where frm.fromUser = :fromUser and frm.status = 0 and frm.isDelete=0";
	}
}
