package com.media.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.media.bo.AccountMasterBO;
import com.media.bo.FriendRequestMasterBO;
import com.media.bo.FriendsMasterBO;
import com.media.bo.ResponseBO;
import com.media.dao.FriendDAO;
import com.media.helper.QueryHelper;
import com.media.utils.HibernateUtils;

public class FriendDAOImpl implements FriendDAO {
	SessionFactory factory = HibernateUtils.getSessionFactory();
	Session session = factory.openSession(); 
	@Override
	public ResponseBO deleteFriendRequest(FriendRequestMasterBO friendRequestMasterBO) { 
		Transaction t1 = session.beginTransaction(); 
		session.update(friendRequestMasterBO);
		t1.commit();
		return null;
	}
	public ResponseBO acceptFriendRequest(FriendsMasterBO friendsMasterBO) {
		Query  query= session.createQuery(QueryHelper.acceptFriendRequest());
		query.setParameter("id", friendsMasterBO.getId());
		Transaction t1 = session.beginTransaction(); 
		session.save(friendsMasterBO);
		query.executeUpdate();
		t1.commit();
		return null;
	}
	
	public ResponseBO viewFriendRequests(AccountMasterBO accountMasterBO) {
		Query  query= session.createQuery(QueryHelper.viewFriendRequest());
		query.setParameter("fromUser", accountMasterBO.getEmail());
		List<AccountMasterBO> results = query.list();
		ResponseBO responseBO = new ResponseBO();
		responseBO.setAccountMasterBO(results);
		return responseBO;		
	}
	public ResponseBO viewFriends(AccountMasterBO accountMasterBO) {
		String email = accountMasterBO.getEmail();
		Query  query= session.createQuery(QueryHelper.viewFriends());
		query.setParameter("currentUserEmail", email);
		query.setParameter("currentUserEmail", email);
		query.setParameter("currentUserEmail", email);
		List<Object[]>results = query.list();
		List<Object> mutualFriends = new ArrayList();;
		for(int i=0;i<results.size();i++) {
			Object [] a = results.get(i);
			//Declare as J=2 because email comes as 2nd position from object array index
			for(int j=2;j<a.length;j++) {
				System.out.println(a[j]);
				Query  query2= session.createNativeQuery((QueryHelper.viewMutualFriends()));
				query2.setParameter(1, email);
				query2.setParameter(2, email);
				query2.setParameter(3, email);
				
				query2.setParameter(4, a[j].toString());
				query2.setParameter(5, a[j].toString());
				query2.setParameter(6, a[j].toString());
				
				mutualFriends.add(query2.list());
				
			}
		}
		System.out.println(query.list().get(0).toString());
		ResponseBO responseBO = new ResponseBO();
		responseBO.setResults(results);
		responseBO.setMutualFriends(mutualFriends);
		return responseBO;		
	}
	
}
