package com.media.daoimpl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.media.bo.UserSignupFormBO;
import com.media.constants.StoredProcedureConstants;
import com.media.dao.UserDAO;
import com.media.utils.HibernateUtils;

public class UserDAOImpl implements UserDAO{

	@Override
	public boolean signup(UserSignupFormBO userSignupFormBO) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession(); 
		boolean save = false;
		try {			
			Transaction t1 = session.beginTransaction();   	    	        
		    session.save(userSignupFormBO);  
		    t1.commit();  
		    Transaction t2 = session.beginTransaction();   	 
		    session.createStoredProcedureCall(StoredProcedureConstants.IDENTITY_SIGNUP_MASTER).execute();
		    t2.commit();  
		    System.out.println("successfully saved");    
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

}
