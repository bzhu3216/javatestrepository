package com.qh.test.dao;

import java.util.List;

import com.qh.test.entity.PageBean;
import com.qh.test.entity.User;

public interface UserDao {
	public User login(User user) throws Exception;

	public void register(User user) throws Exception;

	public List<User> getAllUsers(PageBean pagebean) throws Exception;

	public int userCount() throws Exception;

	public User getUser(String id) throws Exception;

	public List<User> getUsersByIdOrName(User user, PageBean pagebean) throws Exception;

	public int usersCount(User user) throws Exception;

	public void delUser(User user) throws Exception;
	
	public void save(User user) throws Exception;
}
