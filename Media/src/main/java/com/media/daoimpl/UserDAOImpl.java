package com.media.daoimpl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.media.bo.UserSignupFormBO;
import com.media.constants.StoredProcedureConstants;
import com.media.dao.UserDAO;
import com.media.utils.HibernateUtils;

public class UserDAOImpl implements UserDAO{
	SessionFactory factory = HibernateUtils.getSessionFactory();
	Session session = factory.openSession(); 
	@Override
	public boolean signup(UserSignupFormBO userSignupFormBO) {
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
			 factory.close();  
			 session.close();
			 HibernateUtils.factory = null;
		}
		return save;
	}          
	@Override
	public  boolean signin(UserSignupFormBO userSignupFormBO) {
		boolean validUser = false;
		try {			
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(UserSignupFormBO.class);
			criteria.add(Restrictions.eq("email",userSignupFormBO.getEmail()));
			criteria.add(Restrictions.eq("password",userSignupFormBO.getPassword()));
			List results = criteria.list();
			if(!results.isEmpty()) {
				validUser = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			 factory.close();  
			 session.close();
			 HibernateUtils.factory = null;			
		}		
		return validUser;
	}

}
