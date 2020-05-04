package com.media.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.media.bo.AccountMasterBO;
import com.media.bo.FriendRequestMasterBO;
import com.media.bo.ResponseBO;
import com.media.dao.HomePageDAO;
import com.media.utils.HibernateUtils;

public class HomePageDAOImpl implements HomePageDAO{
	SessionFactory factory = HibernateUtils.getSessionFactory();
	Session session = factory.openSession(); 
	@Override
	public ResponseBO getHomeContent(String email) {
		ResponseBO responseBO = new ResponseBO(); 		
		Criteria criteria = session.createCriteria(AccountMasterBO.class);
		criteria.add(Restrictions.eq("email",email));
		List <AccountMasterBO>accountMasterBO = criteria.list();
	    responseBO.setAccountMasterBO(accountMasterBO);	   
		return responseBO;
	}
}
