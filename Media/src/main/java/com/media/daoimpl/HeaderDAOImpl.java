package com.media.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.media.bo.FriendRequestMasterBO;
import com.media.bo.ResponseBO;
import com.media.bo.HeaderResponseBO;
import com.media.dao.HeaderDAO;
import com.media.helper.QueryHelper;
import com.media.utils.HibernateUtils;

public class HeaderDAOImpl implements HeaderDAO {
	SessionFactory factory = HibernateUtils.getSessionFactory();
	Session session = factory.openSession(); 
	@Override
	public HeaderResponseBO getTopContent(String email) {
		HeaderResponseBO headerResponseBO = new HeaderResponseBO();
		headerResponseBO.setFriendRequestMasterBO(getFriendRequestContent(email));
		return headerResponseBO;
	}
	
	@Override
	public List<FriendRequestMasterBO> getFriendRequestContent(String email) {
		Query query = session.createQuery(QueryHelper.prepareHQLFriendRequestQuery(email));
		List <FriendRequestMasterBO> friendRequestMasterBO = query.getResultList();
		return friendRequestMasterBO;
	}
	
	

}
