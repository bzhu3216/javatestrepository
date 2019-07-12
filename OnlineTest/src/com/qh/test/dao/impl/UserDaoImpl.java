package com.qh.test.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.qh.test.dao.UserDao;
import com.qh.test.entity.PageBean;
import com.qh.test.entity.User;
import com.qh.test.util.HibernateUitl;
import com.qh.test.util.StringUtil;

public class UserDaoImpl implements UserDao {
	SessionFactory sessionFactory = HibernateUitl.getSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public User login(User user) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from User as u where u.id=:id and u.pwd=:pwd";
		Query query = session.createQuery(hql);
		query.setString("id", user.getId());
		query.setString("pwd", user.getPwd());
		User u = (User) query.uniqueResult();
		session.getTransaction().commit();
		return u;
	}

	public void register(User user) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.merge(user);
		session.getTransaction().commit();
	}

	public List<User> getAllUsers(PageBean pagebean) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from User";
		Query query = session.createQuery(hql);
		if (pagebean != null) {
			query.setFirstResult(pagebean.getStart());
			query.setMaxResults(pagebean.getPageSize());
		}
		List<User> userlist = (List<User>) query.list();
		session.getTransaction().commit();
		return userlist;
	}

	public int userCount() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sql = "select count(*) from t_student";
		Query query = session.createSQLQuery(sql.toString());
		int count = ((BigInteger) query.uniqueResult()).intValue();
		session.getTransaction().commit();
		return count;
	}

	public User getUser(String id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, id);
		session.getTransaction().commit();
		return user;
	}

	public List<User> getUsersByIdOrName(User user, PageBean pagebean) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from User";
		if (StringUtil.isNotEmpty(user.getId())) {
			hql += " and id like '" + user.getId() + "%'";
		}
		if (StringUtil.isNotEmpty(user.getName())) {
			hql += " and name like '%" + user.getName() + "%'";
		}
		Query query = session.createQuery(hql.replaceFirst("and", "where"));
		if (pagebean != null) {
			query.setFirstResult(pagebean.getStart());
			query.setMaxResults(pagebean.getPageSize());
		}
		List<User> userlist = (List<User>) query.list();
		session.getTransaction().commit();
		return userlist;
	}

	public int usersCount(User user) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sql = "select count(*) from t_student";
		if (StringUtil.isNotEmpty(user.getId())) {
			sql += " and id like '" + user.getId() + "%'";
		}
		if (StringUtil.isNotEmpty(user.getName())) {
			sql += " and name like '%" + user.getName() + "%'";
		}
		Query query = session.createSQLQuery(sql.toLowerCase().replaceFirst("and", "where"));
		int count = ((BigInteger) query.uniqueResult()).intValue();
		session.getTransaction().commit();
		return count;
	}

	public void delUser(User user) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
	}

	public void save(User user) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.merge(user);
		session.getTransaction().commit();
	}
}
