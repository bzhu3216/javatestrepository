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
import com.qh.test.entity.Question;
import com.qh.test.service.QuestionService;
import com.qh.test.service.impl.PaperServiceImpl;
import com.qh.test.service.impl.QuestionServiceImpl;
import com.qh.test.util.PageUtil;
import com.qh.test.util.ResponseUtil;
import com.qh.test.util.StringUtil;

import net.sf.json.JSONObject;

public class QuestionAction extends ActionSupport implements ServletRequestAware {

	HttpServletRequest request;

	String page;
	Question selQuestion;
	Question question;
	List<Question> questionList;
	String pageCode;
	String mainPage;
	List<Paper> paperList;
	String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Paper> getPaperList() {
		return paperList;
	}

	public void setPaperList(List<Paper> paperList) {
		this.paperList = paperList;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	String questionId;

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

	public QuestionServiceImpl getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionServiceImpl questionService) {
		this.questionService = questionService;
	}

	PaperServiceImpl paperService = new PaperServiceImpl();
	QuestionServiceImpl questionService = new QuestionServiceImpl();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	int total;

	public Question getSelQuestion() {
		return selQuestion;
	}

	public void setSelQuestion(Question selQuestion) {
		this.selQuestion = selQuestion;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public String questionManage() {
		mainPage = "question/questionManage.jsp";
		return "success";
	}

	public String getAllQuestions() throws Exception {
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		if (selQuestion != null) {
			session.setAttribute("selQuestion", selQuestion);
		} else {
			Object o = session.getAttribute("selQuestion");
			if (o != null) {
				selQuestion = (Question) o;
			} else {
				selQuestion = new Question();
			}
		}
		PageBean pagebean = new PageBean(Integer.parseInt(page), 5);
		questionList = questionService.getAllQuestions(selQuestion, pagebean);
		total = questionService.questionCount(selQuestion);
		pageCode = PageUtil.genPagation(request.getContextPath() + "/question!getAllQuestions", total,
				Integer.parseInt(page), 5);
		mainPage = "question/showQuestions.jsp";
		return "success";
	}

	public String preSave() throws Exception {
		paperList = paperService.getAllPapers();
		if (StringUtil.isNotEmpty(questionId)) {
			question = questionService.getQuestionById(questionId);
			title = "修改客观题试题信息";
		} else {
			title = "添加客观题试题信息";
		}
		mainPage = "question/questionSave.jsp";
		return "success";
	}

	public String saveQuestion() throws Exception {
		if (StringUtil.isNotEmpty(questionId)) {
			question.setId(Integer.parseInt(questionId));
		} else {
			question.setJoinTime(new Date(System.currentTimeMillis()));
		}
		question.setAnswer(question.getAnswer().toUpperCase());
		questionService.saveQuestion(question);
		return "save";
	}

	public String getQuestionById() throws Exception {
		question = questionService.getQuestionById(questionId);
		mainPage = "question/showQuestion.jsp";
		return "success";
	}

	public String delete() throws Exception {
		question = questionService.getQuestionById(questionId);
		questionService.deleteQuestion(question);
		JSONObject resultJson = new JSONObject();
		resultJson.put("success", true);
		ResponseUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public PaperServiceImpl getPaperService() {
		return paperService;
	}

	public void setPaperService(PaperServiceImpl paperService) {
		this.paperService = paperService;
	}
}
