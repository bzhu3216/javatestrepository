package com.qh.test.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.qh.test.entity.PageBean;
import com.qh.test.entity.User;
import com.qh.test.service.impl.TestServiceImpl;
import com.qh.test.service.impl.UserServiceImpl;
import com.qh.test.util.DateUtil;
import com.qh.test.util.PageUtil;
import com.qh.test.util.ResponseUtil;
import com.qh.test.util.StringUtil;

import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UserAction extends ActionSupport implements ServletRequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	////////////////////////////////
	// 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	///////////////////////////////////
	

	User user;
	User selUser;

	HttpServletRequest request;
	String error;
	UserServiceImpl userService = new UserServiceImpl();
	TestServiceImpl testServiceImpl = new TestServiceImpl();
	String page;

	String pageCode;
	String mainPage;
	String title;
	String id;
	
	int total;

	public String login() throws Exception {
		HttpSession session = request.getSession();
		User currentUser = userService.login(user);
		if (currentUser == null) {
			error = "学号或者密码错误!";
			return "error";
		} else {
			session.setAttribute("currentUser", currentUser);
			return "success";
		}
	}

	public String register() throws Exception {
		user.setId(DateUtil.getCurrentDateStr());
		userService.register(user);
		return "register";
	}

	List<User> userlist;

	public String getAllUsers() throws Exception {
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pagebean = new PageBean(Integer.parseInt(page), 3);
		userlist = userService.getAllUsers(pagebean);
		total = userService.userCount();
		pageCode = PageUtil.genPagation(request.getContextPath() + "/user!getAllUsers", total, Integer.parseInt(page),
				3);
		mainPage = "user/userlist.jsp";
		return "success";
	}

	public String getUsersByIdOrName() throws Exception {
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		if (selUser != null) {
			session.setAttribute("selUser", selUser);
		} else {
			Object o = session.getAttribute("selUser");
			if (o != null) {
				selUser = (User) o;
			} else {
				selUser = new User();
			}
		}
		PageBean pagebean = new PageBean(Integer.parseInt(page), 3);
		userlist = userService.getUsersByIdOrName(selUser, pagebean);
		session.setAttribute("userlist", userlist);
		total = userService.usersCount(selUser);
		pageCode = PageUtil.genPagation(request.getContextPath() + "/user!getUsersByIdOrName", total,
				Integer.parseInt(page), 3);
		mainPage = "user/userlist.jsp";
		return "success";
	}

	public String preSave() throws Exception {
		if (StringUtil.isNotEmpty(id)) {
			user = userService.getUser(id);
			title = "修改考生信息";
		} else {
			title = "添加考生信息";
		}
		mainPage = "user/usersave.jsp";
		return "success";
	}

	public String batchAdd() throws Exception {
		String path = request.getParameter("uploadFile");
		System.out.println(path+"aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		///////////////////////////
		/*
		// 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        
        // 中文处理
        upload.setHeaderEncoding("UTF-8"); 

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
       
         
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message",
                            "文件上传成功!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }
		
		*/
		///////////////////////////
		if (path.split("\\.")[path.split("\\.").length - 1].toString().equals("xls")) {
			User user1 = new User();
			HSSFWorkbook workBook = null;
			try {
				InputStream inputStream = new FileInputStream(new File(path));
				workBook = new HSSFWorkbook(inputStream);
				// System.out.println(workBook.getNumberOfSheets());
				for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
					Sheet sheet = workBook.getSheetAt(numSheet);
					for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
						Row row = sheet.getRow(rowNum);
						if (row != null) {
							user1.setId(row.getCell(0).toString());
							user1.setCardNo(row.getCell(1).toString());
							user1.setName(row.getCell(2).toString());
							user1.setPwd(row.getCell(3).toString().split("\\.")[0].toString());
							user1.setPrefession(row.getCell(4).toString());
							user1.setSex(row.getCell(5).toString());
							userService.register(user1);
						}
					}
				}
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			error = "文件后缀名有误，请重新上传后缀名为.xls的文件";
			mainPage = "user/batchAdd.jsp";
			return "success";
		}
		return "save";

	}

	public String preBatchAdd() throws Exception {
		title = "批量添加";
		mainPage = "user/batchAdd.jsp";
		return "success";
	}

	public String SaveUser() throws Exception {
		if (StringUtil.isEmpty(user.getId()))
			user.setId(DateUtil.getCurrentDateStr());
		userService.register(user);
		return "save";
	}

	public String delUser() throws Exception {
		//System.out.println("id:" + id);
		user = userService.getUser(id);
		JSONObject resultJson = new JSONObject();
		if (testServiceImpl.existUserByUserId(id)) {
			resultJson.put("error", "该考生参加过考试，不能删除");
		} else {
			userService.delUser(user);
			resultJson.put("success", true);
		}

		ResponseUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}

	public String preUpdatePassword() throws Exception {
		mainPage = "user/updatePassword.jsp";
		return "success";
	}

	public String updatePassword() throws Exception {
		User u = userService.getUser(user.getId());
		u.setPwd(user.getPwd());
		userService.save(u);
		mainPage = "user/updateSuccess.jsp";
		return "success";
	}

	public String logout() throws Exception {
		request.getSession().invalidate();
		return "logout";
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getSelUser() {
		return selUser;
	}

	public void setSelUser(User selUser) {
		this.selUser = selUser;
	}

	public List<User> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
}
