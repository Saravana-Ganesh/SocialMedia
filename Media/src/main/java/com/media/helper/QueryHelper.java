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
	
	public static String viewFriends() {
		//Query for view friend requests
		return "select distinct AM.id as userid ,AM.name as name ,AM.email as email from AccountMasterBO AM " + 
				" inner join FriendsMasterBO FM " + 
				" on FM.friendEmail=AM.email or FM.userEmail = AM.email " + 
				" where (FM.friendEmail=:currentUserEmail or FM.userEmail=:currentUserEmail) " + 
				" and AM.email!=:currentUserEmail";
	}
	/*public static String viewMutualFriends() {
		//Query for view friend requests
		return "select t1.email from (select distinct AM.id as userid ,AM.name as name ,AM.email as email from AccountMasterBO AM " + 
				" inner join FriendsMasterBO FM " + 
				" on FM.friendEmail=AM.email or FM.userEmail = AM.email " + 
				" where (FM.friendEmail=:currentUserEmail or FM.userEmail=:currentUserEmail) "+
				"  and AM.email!=:currentUserEmail) t1 " + 				
				" inner join " + 				
				" (select distinct AM.id as userid ,AM.name as name ,AM.email as email from AccountMasterBO AM " + 
				" inner join FriendsMasterBO FM " + 
				" on FM.friendEmail=AM.email or FM.userEmail = AM.email " + 
				" where (FM.friendEmail=:friendUserEmail or FM.userEmail=:friendUserEmail) " + 
				" and AM.email!=:friendUserEmail) t2" + 		
				" on t1.email=t2.email";
				
	}*/
	public static String viewMutualFriends() {
		//Query for view friend requests
		//The below query is native oracle query
		//This should be converted to hql query in future
		return " select t1.email,t1.username from (select distinct am.userid,am.username,AM.email as email from account_master AM " + 
				" inner join friends_master FM " + 
				" on fm.friend_email=am.email or fm.user_email = am.email " + 
				" where (fm.friend_email=? or fm.user_email = ?)" + 
				" and am.email!=?) t1 " + 				 
				" inner join " + 
				" (select distinct am.userid,am.username,AM.email as email from account_master AM " + 
				" inner join friends_master FM " + 
				" on fm.friend_email=am.email or fm.user_email = am.email " + 
				" where (fm.friend_email=? or fm.user_email = ?)" + 
				" and am.email!= ?) t2" + 				
				" on t1.email=t2.email";
				
	}
}
