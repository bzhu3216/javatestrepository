package com.qh.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.qh.test.entity.Admin;
import com.qh.test.service.impl.AdminServiceImpl;

public class AdminAction extends ActionSupport implements ServletRequestAware {

	HttpServletRequest request;
	AdminServiceImpl adminService = new AdminServiceImpl();
	Admin admin;
	String error;

	public String login() throws Exception {
		HttpSession session = request.getSession();
		Admin currentUser = adminService.login(admin);
		if (currentUser == null) {
			error = "用户名或者密码错误！";
			return "error";
		} else {
			session.setAttribute("currentUser", currentUser);
			return "success";
		}
	}
	
	public String logout() throws Exception{
		request.getSession().invalidate();
		return "logout";
	}

	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Admin getAdmin() {
		return admin;
	}

	public AdminServiceImpl getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminServiceImpl adminService) {
		this.adminService = adminService;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
