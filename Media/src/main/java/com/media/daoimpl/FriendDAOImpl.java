package com.media.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.media.bo.FriendRequestMasterBO;
import com.media.bo.ResponseBO;
import com.media.dao.FriendDAO;
import com.media.utils.HibernateUtils;

public class FriendDAOImpl implements FriendDAO {
	SessionFactory factory = HibernateUtils.getSessionFactory();
	Session session = factory.openSession(); 
	@Override
	public ResponseBO deleteFriendRequest(FriendRequestMasterBO friendRequestMasterBO) { 
		Transaction t1 = session.beginTransaction();   	    
		session.delete(friendRequestMasterBO);
		t1.commit();
		return null;
	}
	
}
