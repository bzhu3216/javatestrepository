package com.qh.test.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.qh.test.dao.AdminDao;
import com.qh.test.entity.Admin;
import com.qh.test.util.HibernateUitl;

public class AdminDaoImpl implements AdminDao{

	SessionFactory sessionFactory=HibernateUitl.getSessionFactory();
	
	public Admin login(Admin admin) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql="from Admin as a where a.userName=:userName and a.password=:password ";
		Query query=session.createQuery(hql);
		query.setString("userName", admin.getUserName());
		query.setString("password", admin.getPassword());
		Admin resultAdmin=(Admin)query.uniqueResult();
		session.getTransaction().commit();
		return resultAdmin;
	}

}
