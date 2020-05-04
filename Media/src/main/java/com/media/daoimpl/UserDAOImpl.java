package com.media.daoimpl;


import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.media.bo.AccountMasterBO;
import com.media.bo.FriendRequestMasterBO;
import com.media.bo.ResponseBO;
import com.media.constants.StoredProcedureConstants;
import com.media.dao.UserDAO;
import com.media.helper.QueryHelper;
import com.media.utils.HibernateUtils;

public class UserDAOImpl implements UserDAO{
	SessionFactory factory = HibernateUtils.getSessionFactory();
	Session session = factory.openSession(); 
	@Override
	public boolean signup(AccountMasterBO userSignupFormBO) {
		boolean save = false;
		try {			
			Transaction t1 = session.beginTransaction();   	    	        
		    session.save(userSignupFormBO);  
		    t1.commit();  
		    Transaction t2 = session.beginTransaction();   	 
		    session.createStoredProcedureCall(StoredProcedureConstants.IDENTITY_SIGNUP_MASTER).execute();
		    t2.commit();  
		    save = true;		    
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
/*			 factory.close();  
			 session.close();
			 HibernateUtils.factory = null;
*/		}
		return save;
	}          
	@Override
	public  ResponseBO signin(AccountMasterBO userSignupFormBO) {
		ResponseBO responseBO = new ResponseBO();
		try {			
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(AccountMasterBO.class);
			criteria.add(Restrictions.eq("email",userSignupFormBO.getEmail()));
			criteria.add(Restrictions.eq("password",userSignupFormBO.getPassword()));
			List<AccountMasterBO> results = criteria.list();
			if(!results.isEmpty()) {
				responseBO.setValid(true);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			/* factory.close();  
			 session.close();
			 HibernateUtils.factory = null;			*/
		}		
		return responseBO;
	}
	@Override
	public ResponseBO findFriends(AccountMasterBO userSignupFormBO){
		ResponseBO responseBO = new ResponseBO();
		Query query = session.createQuery(QueryHelper.prepareHQLQuery(userSignupFormBO));
		List <AccountMasterBO> accountList = query.getResultList();
		responseBO.setAccountMasterBO(accountList);
		return responseBO;             
	}      
	@Override
	public boolean addFriend(FriendRequestMasterBO friendRequestMasterBO) {
		Transaction t1 = session.beginTransaction();   	    	        
	    session.save(friendRequestMasterBO);  
	    t1.commit();  
	    Transaction t2 = session.beginTransaction();   	 
	    session.createStoredProcedureCall(StoredProcedureConstants.FRIEND_REQUEST_MASTER).execute();
	    t2.commit();  
		return true;
	}  
	
               
}
