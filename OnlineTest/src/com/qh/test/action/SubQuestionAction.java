package com.qh.test.action;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.qh.test.entity.PageBean;
import com.qh.test.entity.Paper;
import com.qh.test.entity.SubQuestion;
import com.qh.test.service.impl.PaperServiceImpl;
import com.qh.test.service.impl.SubQuestionServiceImpl;
import com.qh.test.util.PageUtil;
import com.qh.test.util.ResponseUtil;
import com.qh.test.util.StringUtil;

import net.sf.json.JSONObject;
import sun.swing.StringUIClientPropertyKey;

public class SubQuestionAction extends ActionSupport implements ServletRequestAware {

	HttpServletRequest request;
	String mainPage;
	String page;
	int total;
	String pageCode;
	List<Paper> paperList;

	public List<Paper> getPaperList() {
		return paperList;
	}

	public void setPaperList(List<Paper> paperList) {
		this.paperList = paperList;
	}

	SubQuestionServiceImpl subquestionService = new SubQuestionServiceImpl();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public SubQuestionServiceImpl getSubquestionService() {
		return subquestionService;
	}

	public void setSubquestionService(SubQuestionServiceImpl subquestionService) {
		this.subquestionService = subquestionService;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public SubQuestion getSubquestion() {
		return subquestion;
	}

	public void setSubquestion(SubQuestion subquestion) {
		this.subquestion = subquestion;
	}

	public SubQuestion getSelSubQuestion() {
		return selSubQuestion;
	}

	public void setSelSubQuestion(SubQuestion selSubQuestion) {
		this.selSubQuestion = selSubQuestion;
	}

	public List<SubQuestion> getSubquestionList() {
		return subquestionList;
	}

	public void setSubquestionList(List<SubQuestion> subquestionList) {
		this.subquestionList = subquestionList;
	}

	SubQuestion subquestion;
	SubQuestion selSubQuestion;

	List<SubQuestion> subquestionList;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public String getAllSubQuestions() throws Exception {
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		if (selSubQuestion != null) {
			session.setAttribute("selSubQuestion", selSubQuestion);
		} else {
			Object o = session.getAttribute("selSubQuestion");
			if (o != null) {
				selSubQuestion = (SubQuestion) o;
			} else {
				selSubQuestion = new SubQuestion();
			}
		}
		PageBean pagebean = new PageBean(Integer.parseInt(page), 5);
		subquestionList = subquestionService.getAllSubQuestions(selSubQuestion, pagebean);
		total = subquestionService.subQuestionCount(selSubQuestion);
		System.out.println(total);
		pageCode = PageUtil.genPagation(request.getContextPath() + "/subquestion!getAllSubQuestions", total,
				Integer.parseInt(page), 5);
		mainPage = "question/showSubQuestions.jsp";
		return "success";
	}

	String subquestionId;

	public String getSubquestionId() {
		return subquestionId;
	}

	public void setSubquestionId(String subquestionId) {
		this.subquestionId = subquestionId;
	}

	public String getSubQuestionById() throws Exception {
		subquestion = subquestionService.getSubQuestionById(subquestionId);
		mainPage = "question/showSubQuestion.jsp";
		return "success";
	}

	PaperServiceImpl paperServiceImpl = new PaperServiceImpl();
	String title;

	public PaperServiceImpl getPaperServiceImpl() {
		return paperServiceImpl;
	}

	public void setPaperServiceImpl(PaperServiceImpl paperServiceImpl) {
		this.paperServiceImpl = paperServiceImpl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String preSave() throws Exception {
		paperList = paperServiceImpl.getAllPapers();
		if (StringUtil.isNotEmpty(subquestionId)) {
			subquestion = subquestionService.getSubQuestionById(subquestionId);
			title = "修改主观题试题信息";
		} else {
			title = "修改客观题试题信息";
		}
		mainPage = "question/subquestionSave.jsp";
		return "success";
	}

	public String saveSubQuestion() throws Exception {
		if (StringUtil.isNotEmpty(subquestionId)) {
			subquestion.setId(Integer.parseInt(subquestionId));
		} else {
			subquestion.setJoinTime(new Date(System.currentTimeMillis()));
		}
		subquestionService.saveSubQuestion(subquestion);
		return "save";
	}
	
	public String deleteSubQuesion() throws Exception{
		subquestion=subquestionService.getSubQuestionById(subquestionId);
		subquestionService.deleteSubQuesion(subquestion);
		JSONObject resultJson = new JSONObject();
		resultJson.put("success", true);
		ResponseUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}
}
