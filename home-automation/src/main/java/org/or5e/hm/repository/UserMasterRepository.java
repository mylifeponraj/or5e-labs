package org.or5e.hm.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.or5e.hm.vo.UserMaster;

public class UserMasterRepository extends BaseRepository{

	public UserMaster getUser(String userName, String userKey) {

		Session userMasterSession = sessionFactory.openSession();
		Criteria userMasterCriteria = userMasterSession.createCriteria(org.or5e.hm.dao.UserMaster.class);
		userMasterCriteria.add(Restrictions.eq("UserName", userName));
		userMasterCriteria.add(Restrictions.eq("UserKey", userKey.hashCode()));

		@SuppressWarnings("unchecked")
		List<org.or5e.hm.dao.UserMaster> userList = (List<org.or5e.hm.dao.UserMaster>)userMasterCriteria.list();

		userMasterSession.close();

		if(userList.size() == 1) {
			return map((org.or5e.hm.dao.UserMaster)userList.get(1));
		}
		return null;
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}
	public Boolean closeSession(Session session) {
		session.close();
		return Boolean.TRUE;
	}
	public UserMaster getUserDetails(Session session, Integer userID) {
		Criteria userMasterCriteria = session.createCriteria(org.or5e.hm.dao.UserMaster.class);
		userMasterCriteria.add(Restrictions.eq("UserID", userID));

		@SuppressWarnings("unchecked")
		List<org.or5e.hm.dao.UserMaster> userList = (List<org.or5e.hm.dao.UserMaster>)userMasterCriteria.list();

		if(userList.size() == 1) {
			return map((org.or5e.hm.dao.UserMaster)userList.get(1));
		}
		return null;
	}
	public void popupateMasterController(UserMaster userMaster, Session session) {
		
	}
	public void addUser(UserMaster userMasterVO) {

		org.or5e.hm.dao.UserMaster userMaster = map(userMasterVO);

		Session userMasterSession = sessionFactory.openSession();
		Transaction tx = userMasterSession.beginTransaction();
		userMasterSession.save(userMaster);
		tx.commit();

		userMasterSession.close();
	}
	@Override public String getName() {
		return "UserMasterRepository";
	}

	private UserMaster map(org.or5e.hm.dao.UserMaster userMasterDAO) {
		UserMaster userMaster = new UserMaster();
		userMaster.setAddress1(userMasterDAO.getAddress1());
		userMaster.setAddress2(userMasterDAO.getAddress2());
		userMaster.setCity(userMasterDAO.getCity());
		userMaster.setCountry(userMasterDAO.getCountry());
		userMaster.setLastLoggedIn(userMasterDAO.getLastLoggedIn());
		userMaster.setPincode(userMasterDAO.getPincode());
		userMaster.setState(userMasterDAO.getState());
		userMaster.setUserID(userMasterDAO.getUserID());
//		userMaster.setUserKey(userMasterDAO.getUserKey());
		userMaster.setUserName(userMasterDAO.getUserName());
		userMaster.setUserStatus(userMasterDAO.getUserStatus());
		userMaster.setUserType(userMasterDAO.getUserType());
		return userMaster;
	}
	private org.or5e.hm.dao.UserMaster map(org.or5e.hm.vo.UserMaster sourceVO) {
		org.or5e.hm.dao.UserMaster userMaster = new org.or5e.hm.dao.UserMaster();
		userMaster.setAddress1(sourceVO.getAddress1());
		userMaster.setAddress2(sourceVO.getAddress2());
		userMaster.setCity(sourceVO.getCity());
		userMaster.setCountry(sourceVO.getCountry());
		userMaster.setLastLoggedIn(sourceVO.getLastLoggedInTimestamp());
		userMaster.setPincode(sourceVO.getPincode());
		userMaster.setState(sourceVO.getState());
		userMaster.setUserID(sourceVO.getUserID());
		userMaster.setUserKey(sourceVO.getUserKey().hashCode()+"");
		userMaster.setUserName(sourceVO.getUserName());
		userMaster.setUserStatus(sourceVO.getUserStatus());
		userMaster.setUserType(sourceVO.getUserType());
		
		return userMaster;
	}
}
