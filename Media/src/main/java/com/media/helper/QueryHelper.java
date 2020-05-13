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
	
	public static String viewAllMessageContent() {
		/*
		 * The below native query is for getting chat content with specific user
		 */
		return "select t1.id,t1.from_user,t1.username as fromUserName,t1.to_user,t2.username as ToUserName, "
				+ "t1.message,(to_char(t1.date_time,'dd/mm/yyyy hh12:mi:ss AM')) from " + 
				" (select MM.id,MM.from_user,AM.username,MM.to_user,mm.message,mm.date_time from message_master MM inner join account_master AM " + 
				" on AM.email=MM.from_user " + 
				" where ((MM.from_user =? and MM.to_user=?) " + 
				" or (MM.to_user =? and MM.from_user =?)) "+ 
				" and  mm.is_delete=0 " + 
				" order by mm.date_time)t1 " + 
				" inner join " + 
				" (select MM.id, AM.username from message_master MM inner join account_master AM " + 
				" on AM.email=MM.to_user " + 
				" where ((MM.from_user =? and MM.to_user=?) " + 
				" or (MM.to_user =? and MM.from_user =?)) " + 
				" and  mm.is_delete=0 " + 
				" order by mm.date_time) t2 " + 
				" on t1.id=t2.id ";
	}
	
	public static String viewMessageSidePanel() {
		return  "select NVL(OUTT1.from_user,OUTT2.to_user) as Contact, " + 
				" AM.username as Name, " + 
				" case " + 
				"    when OUTT1.date_time>NVL(OUTT2.date_time,'01-JAN-97') " + 
				"        Then OUTT1.message " + 
				"    else " + 
				"        concat('You: ',OUTT2.message) " + 
				"    END as Message, " + 
				" case     " + 
				"        when OUTT1.date_time>NVL(OUTT2.date_time,'01-JAN-97')         " + 
				"            Then to_char(OUTT1.date_time,'mm/dd/yyyy hh12:mi AM') " + 
				"   else          " + 
				"        to_char(OUTT2.date_time,'mm/dd/yyyy hh12:mi AM') " + 
				"   END as DATE_TIME," + 
				" case " + 
				"    when OUTT1.date_time>NVL(OUTT2.date_time,'01-JAN-97') " + 
				"        Then OUTT1.status " + 
				"    else " + 
				"        1 " + 
				"    END as Status" + 
				" from " + 				 
				" (select t1.from_user,MM.message, t1.date_time ,t1.status from " + 
				" (select from_user,MAX(date_time) as date_time, status from message_master  " + 
				" where to_user=? " + 
				" group by from_user,status)t1 " + 
				" inner join message_master MM " + 
				" on MM.date_time = t1.date_time)OUTT1 " + 
				" FULL outer join " + 				 
				" (select t1.to_user,MM.message, t1.date_time,t1.status from " + 
				" (select to_user,MAX(date_time) as date_time,status from message_master  " + 
				" where from_user=? " + 
				" group by to_user,status)t1 " + 
				" inner join message_master MM " + 
				" on MM.date_time = t1.date_time) OUTT2 " + 
				" on outt2.to_user=OUTT1.from_user " + 
				" inner join account_master AM " + 
				" on am.email = NVL(OUTT1.from_user,OUTT2.to_user) " + 
				" order by DATE_TIME desc";
	}
}
