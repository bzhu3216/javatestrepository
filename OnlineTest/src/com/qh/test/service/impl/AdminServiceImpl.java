package com.qh.test.service.impl;

import com.qh.test.dao.impl.AdminDaoImpl;
import com.qh.test.entity.Admin;
import com.qh.test.service.AdminService;

public class AdminServiceImpl implements AdminService{

	AdminDaoImpl adminDao=new AdminDaoImpl();
	
	public Admin login(Admin admin) {
		return adminDao.login(admin);
	}

}
