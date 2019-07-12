package com.qh.test.service.impl;

import java.util.List;

import com.qh.test.dao.impl.UserDaoImpl;
import com.qh.test.entity.PageBean;
import com.qh.test.entity.User;
import com.qh.test.service.UserService;

public class UserServiceImpl implements UserService {

	UserDaoImpl userDao = new UserDaoImpl();

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public User login(User user) throws Exception {
		User u = userDao.login(user);
		return u;
	}

	public void register(User user) throws Exception {
		userDao.register(user);
	}

	public List<User> getAllUsers(PageBean pagebean) throws Exception {
		return userDao.getAllUsers(pagebean);
	}

	public int userCount() throws Exception {
		return userDao.userCount();
	}

	public User getUser(String id) throws Exception {
		return userDao.getUser(id);
	}

	public List<User> getUsersByIdOrName(User user, PageBean pageBean) throws Exception {
		return userDao.getUsersByIdOrName(user, pageBean);
	}

	public int usersCount(User user) throws Exception {
		return userDao.usersCount(user);
	}

	public void delUser(User user) throws Exception {
		userDao.delUser(user);
	}

	public void save(User user) throws Exception {
		userDao.save(user);
	}

}
