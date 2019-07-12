package com.qh.test.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.qh.test.entity.Paper;
import com.qh.test.entity.Question;
import com.qh.test.entity.SubQuestion;
import com.qh.test.service.impl.PaperServiceImpl;
import com.qh.test.service.impl.QuestionServiceImpl;
import com.qh.test.service.impl.SubQuestionServiceImpl;
import com.qh.test.service.impl.TestServiceImpl;
import com.qh.test.util.ResponseUtil;
import com.qh.test.util.StringUtil;

import net.sf.json.JSONObject;

public class PaperAction extends ActionSupport implements ServletRequestAware {

	HttpServletRequest request;

	List<Paper> paperList = new ArrayList<Paper>();

	PaperServiceImpl paperService = new PaperServiceImpl();
	SubQuestionServiceImpl subquestionServiceImpl = new SubQuestionServiceImpl();

	String mainPage;
	String paperId;
	Paper paper;
	String title;
	String error;

	int fullScore = 0;
	int sScore = 0;
	int mScore = 0;
	int fScore = 0;
	int tScore = 0;

	List<Question> squestionList = new ArrayList<Question>(); // 单选题
	List<Question> mquestionList = new ArrayList<Question>(); // 多选题
	List<SubQuestion> fquestionList = new ArrayList<SubQuestion>(); // 填空题
	List<SubQuestion> rquestionList = new ArrayList<SubQuestion>(); // 问答题

	QuestionServiceImpl questionService = new QuestionServiceImpl();

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAllPapers() throws Exception {
		paperList = paperService.getAllPapers();
		mainPage = "paper/showPapers.jsp";
		return "success";
	}

	public String preSave() throws Exception {
		if (StringUtil.isNotEmpty(paperId)) {
			paper = paperService.getPaper(paperId);
			title = "修改试卷";
			mainPage = "paper/paperSave.jsp";
		} else {
			title = "添加试卷";
			mainPage = "paper/paperAdd.jsp";
		}
		return "success";
	}

	public String addPaper() throws Exception {
		Paper paper1 = paperService.getPaperByName(paper.getPaperName());
		if (paper1 != null) {
			error = "该试卷已存在";
			mainPage = "paper/paperAdd.jsp";
			return "success";
		} else {
			paper.setJoinDate(new Date(System.currentTimeMillis()));
			paperService.addPaper(paper);
			return "save";
		}
	}

	public String savePaper() throws Exception {
		if (StringUtil.isNotEmpty(paperId)) {
			paper.setId(Integer.parseInt(paperId));
		} else {
			paper.setJoinDate(new Date(System.currentTimeMillis()));
		}
		paperService.savePaper(paper);
		return "save";
	}

	TestServiceImpl testServiceImpl=new TestServiceImpl();
	
	public String deletePaper() throws Exception {
		paper = paperService.getPaper(paperId);
		System.out.println("paper" + paper.getId());
		JSONObject resultJson = new JSONObject();
		if (questionService.existQuestionByPaperId(paperId)
				|| subquestionServiceImpl.existSubQuestionByPaperId(paperId)) {
			resultJson.put("error", "试卷下面有题目，不能删除");
		} else if (testServiceImpl.existPaperByPaperId(paperId)) {
			resultJson.put("error", "该试卷已被考试，不能删除");
		} else {
			paperService.deletePaper(paper);
			System.out.println("paper123:" + paper.getId());
			resultJson.put("success", true);
		}
		ResponseUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}

	public String getPapers() throws Exception {
		paperList = paperService.getAllPapers();
		mainPage = "test/selPaper.jsp";
		return "success";
	}

	Map<Integer, String> map3 = new HashMap<Integer, String>();
	Map<Integer, String> map4 = new HashMap<Integer, String>();

	public String getDetailPaper() throws Exception {
		paper = paperService.getPaper(paperId);
		List<Question> questionList = paper.getQuestions();
		Iterator<Question> it = questionList.iterator();
		if (it.hasNext()) {
			Question q = it.next();
			int id = q.getId();
			map3.put(id, "1");
			System.out.println(q.getAnswer());
			if ("1".equals(q.getType())) {
				squestionList.add(q);
				if (q.getLevel().equals("难")) {
					sScore += 4;
				} else if (q.getLevel().equals("中")) {
					sScore += 3;
				} else {
					sScore += 2;
				}
			} else {
				mquestionList.add(q);
				if (q.getLevel().equals("难")) {
					mScore += 5;
				} else if (q.getLevel().equals("中")) {
					mScore += 4;
				} else {
					mScore += 3;
				}
			}
		}
		while (it.hasNext()) {
			Question q = it.next();
			int id = q.getId();
			if ("1".equals(map3.get(q.getId())))
				continue;
			System.out.println(q.getAnswer());
			if ("1".equals(q.getType())) {
				squestionList.add(q);
				if (q.getLevel().equals("难")) {
					sScore += 4;
				} else if (q.getLevel().equals("中")) {
					sScore += 3;
				} else {
					sScore += 2;
				}
			} else {
				mquestionList.add(q);
				if (q.getLevel().equals("难")) {
					mScore += 5;
				} else if (q.getLevel().equals("中")) {
					mScore += 4;
				} else {
					mScore += 3;
				}
			}
			map3.put(id, "1");
		}
		List<SubQuestion> subquestionList = paper.getSubquestions();
		Iterator<SubQuestion> it1 = subquestionList.iterator();
		/*
		 * if (it1.hasNext()) { id = subquestionList.get(0).getId();
		 * System.out.println(id); SubQuestion q = it1.next();
		 * System.out.println(q.getType()); if ("3".equals(q.getType())) {
		 * fquestionList.add(q); System.out.println("单选"); } else {
		 * rquestionList.add(q); System.out.println("多选"); } }
		 */
		if (it1.hasNext()) {
			SubQuestion q = it1.next();
			int id = q.getId();
			map4.put(id, "1");
			if ("3".equals(q.getType())) {
				fquestionList.add(q);
				fScore += 5;
				System.out.println("填空");
			} else {
				rquestionList.add(q);
				tScore += 10;
				System.out.println("简答");
			}
		}
		while (it1.hasNext()) {
			SubQuestion q = it1.next();
			int id = q.getId();
			if ("1".equals(map4.get(q.getId())))
				continue;
			if ("3".equals(q.getType())) {
				fquestionList.add(q);
				fScore += 5;
				System.out.println("填空");
			} else {
				rquestionList.add(q);
				tScore += 10;
				System.out.println("简答");
			}
			map4.put(id, "1");
		}
		fullScore = sScore + fScore + mScore + tScore;
		/*
		 * System.out.println("单选题满分：" + sScore); System.out.println("多选题满分：" + mScore);
		 * System.out.println("填空题满分：" + fScore); System.out.println("简答题满分：" + tScore);
		 * System.out.println("满分：" + fullScore);
		 */
		paper.setsScore(sScore);
		paper.setfScore(fScore);
		paper.setmScore(mScore);
		paper.settScore(tScore);
		paper.setFullScore(fullScore);
		PaperServiceImpl paperServiceImpl = new PaperServiceImpl();
		paperServiceImpl.savePaper(paper);
		mainPage = "test/test.jsp";
		return "success";
	}

	public String getDetailPaperById() throws Exception {
		paper = paperService.getPaper(paperId);
		List<Question> questionList = paper.getQuestions();
		Iterator<Question> it = questionList.iterator();
		if (it.hasNext()) {
			Question q = it.next();
			int id = q.getId();
			map3.put(id, "1");
			System.out.println(q.getAnswer());
			if ("1".equals(q.getType())) {
				squestionList.add(q);
				if (q.getLevel().equals("难")) {
					sScore += 4;
				} else if (q.getLevel().equals("中")) {
					sScore += 3;
				} else {
					sScore += 2;
				}
			} else {
				mquestionList.add(q);
				if (q.getLevel().equals("难")) {
					mScore += 5;
				} else if (q.getLevel().equals("中")) {
					mScore += 4;
				} else {
					mScore += 3;
				}
			}
		}
		while (it.hasNext()) {
			Question q = it.next();
			int id = q.getId();
			if ("1".equals(map3.get(q.getId())))
				continue;
			System.out.println(q.getAnswer());
			if ("1".equals(q.getType())) {
				squestionList.add(q);
				if (q.getLevel().equals("难")) {
					sScore += 4;
				} else if (q.getLevel().equals("中")) {
					sScore += 3;
				} else {
					sScore += 2;
				}
			} else {
				mquestionList.add(q);
				if (q.getLevel().equals("难")) {
					mScore += 5;
				} else if (q.getLevel().equals("中")) {
					mScore += 4;
				} else {
					mScore += 3;
				}
			}
			map3.put(id, "1");
		}
		List<SubQuestion> subquestionList = paper.getSubquestions();
		Iterator<SubQuestion> it1 = subquestionList.iterator();
		if (it1.hasNext()) {
			SubQuestion q = it1.next();
			int id = q.getId();
			map4.put(id, "1");
			if ("3".equals(q.getType())) {
				fquestionList.add(q);
				fScore += 5;
				System.out.println("填空");
			} else {
				rquestionList.add(q);
				tScore += 10;
				System.out.println("简答");
			}
		}
		while (it1.hasNext()) {
			SubQuestion q = it1.next();
			int id = q.getId();
			if ("1".equals(map4.get(q.getId())))
				continue;
			if ("3".equals(q.getType())) {
				fquestionList.add(q);
				fScore += 5;
				System.out.println("填空");
			} else {
				rquestionList.add(q);
				tScore += 10;
				System.out.println("简答");
			}
			map4.put(id, "1");
		}
		fullScore = sScore + fScore + mScore + tScore;
		paper.setsScore(sScore);
		paper.setfScore(fScore);
		paper.setmScore(mScore);
		paper.settScore(tScore);
		paper.setFullScore(fullScore);
		PaperServiceImpl paperServiceImpl = new PaperServiceImpl();
		paperServiceImpl.savePaper(paper);
		mainPage = "paper/showPaper.jsp";
		return "success";
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public void setServletRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		this.request = req;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public List<Paper> getPaperList() {
		return paperList;
	}

	public void setPaperList(List<Paper> paperList) {
		this.paperList = paperList;
	}

	public PaperServiceImpl getPaperService() {
		return paperService;
	}

	public void setPaperService(PaperServiceImpl paperService) {
		this.paperService = paperService;
	}

	public List<Question> getSquestionList() {
		return squestionList;
	}

	public void setSquestionList(List<Question> squestionList) {
		this.squestionList = squestionList;
	}

	public List<Question> getMquestionList() {
		return mquestionList;
	}

	public void setMquestionList(List<Question> mquestionList) {
		this.mquestionList = mquestionList;
	}

	public List<SubQuestion> getFquestionList() {
		return fquestionList;
	}

	public void setFquestionList(List<SubQuestion> fquestionList) {
		this.fquestionList = fquestionList;
	}

	public List<SubQuestion> getRquestionList() {
		return rquestionList;
	}

	public void setRquestionList(List<SubQuestion> rquestionList) {
		this.rquestionList = rquestionList;
	}

	public QuestionServiceImpl getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionServiceImpl questionService) {
		this.questionService = questionService;
	}

	public int getsScore() {
		return sScore;
	}

	public void setsScore(int sScore) {
		this.sScore = sScore;
	}

	public int getmScore() {
		return mScore;
	}

	public void setmScore(int mScore) {
		this.mScore = mScore;
	}

	public int getfScore() {
		return fScore;
	}

	public void setfScore(int fScore) {
		this.fScore = fScore;
	}

	public int gettScore() {
		return tScore;
	}

	public void settScore(int tScore) {
		this.tScore = tScore;
	}

	public int getFullScore() {
		return fullScore;
	}

	public void setFullScore(int fullScore) {
		this.fullScore = fullScore;
	}

}
