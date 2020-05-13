package com.media.daoimpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.media.bo.MessageBO;
import com.media.bo.ResponseBO;
import com.media.bo.SidePanelMessageBO;
import com.media.constants.StoredProcedureConstants;
import com.media.dao.MessageDAO;
import com.media.helper.QueryHelper;
import com.media.utils.HibernateUtils;

public class MessageDAOImpl implements MessageDAO {
	
	SessionFactory factory = HibernateUtils.getSessionFactory();
	Session session = factory.openSession(); 
	
	public ResponseBO viewAllMessage(String email) {
		ResponseBO responseBO = new ResponseBO();
		Query  query = session.createNativeQuery(QueryHelper.viewMessageSidePanel());
		query.setParameter(1,email);
		query.setParameter(2,email);
		List<Object[]>results = query.list();
		responseBO.setResults(results);
		return responseBO;
		
	}

	public ResponseBO viewchatMessage(MessageBO messageBO) {
		ResponseBO responseBO = new ResponseBO();
		Query  query = session.createNativeQuery(QueryHelper.viewAllMessageContent());
		query.setParameter(1,messageBO.getFromUser());
		query.setParameter(2,messageBO.getToUser());
		query.setParameter(3,messageBO.getFromUser());
		query.setParameter(4,messageBO.getToUser());
		query.setParameter(5,messageBO.getFromUser());
		query.setParameter(6,messageBO.getToUser());
		query.setParameter(7,messageBO.getFromUser());
		query.setParameter(8,messageBO.getToUser());
		List<Object[]>results = query.list();
		responseBO.setResults(results);
		return responseBO;
	}

	public ResponseBO sendMessage(MessageBO messageBO) {		
		messageBO.setDateTime(new Date());
		ResponseBO responseBO = new ResponseBO();
		Transaction t1 = session.beginTransaction();		  
		session.save(messageBO);
	    t1.commit();
		Transaction t2 = session.beginTransaction();		  
	    session.createStoredProcedureCall(StoredProcedureConstants.MESSAGE_MASTER).execute();
	    t2.commit();
		return null;
	}

}
