package com.qh.test.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.qh.test.dao.impl.TestDaoImpl;
import com.qh.test.entity.PageBean;
import com.qh.test.entity.Paper;
import com.qh.test.entity.Question;
import com.qh.test.entity.Statistics_Error;
import com.qh.test.entity.Statistics_Grade;
import com.qh.test.entity.SubQuestion;
import com.qh.test.entity.Test;
import com.qh.test.service.impl.PaperServiceImpl;
import com.qh.test.service.impl.QuestionServiceImpl;
import com.qh.test.service.impl.TestServiceImpl;
import com.qh.test.util.PageUtil;
import com.qh.test.util.ResponseUtil;
import com.qh.test.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestAction extends ActionSupport implements ServletRequestAware {

	HttpServletRequest request;
	String mainPage;
	String page;
	int total;
	String pageCode;
	Test s_test;
	Test test;

	int fullScore = 0;
	int sScore = 0;
	int mScore = 0;
	int fScore = 0;
	int tScore = 0;

	int totalCount; // 总做题数
	int difficultCount; // 难题数
	int middleCount; // 中等题数
	int easyCount; // 简单题数
	int diffiErrorCount; // 难题错误数
	int middErroCount; // 中等题错误数
	int easyErroCount; // 简单题错误数

	Statistics_Error statistics_Error = new Statistics_Error();

	TestDaoImpl testDaoImpl = new TestDaoImpl();
	QuestionServiceImpl questionServiceImpl = new QuestionServiceImpl();

	String testId;

	Paper paper;
	List<Question> squestionList = new ArrayList<Question>(); // 单选题
	List<Question> mquestionList = new ArrayList<Question>(); // 多选题
	List<SubQuestion> fquestionList = new ArrayList<SubQuestion>(); // 填空题
	List<SubQuestion> rquestionList = new ArrayList<SubQuestion>(); // 简答题
	Map<String, String> map1 = new HashMap<String, String>();
	Map<String, String> map2 = new HashMap<String, String>();
	Map<Integer, String> map3 = new HashMap<Integer, String>();
	Map<Integer, String> map4 = new HashMap<Integer, String>();

	List<Statistics_Grade> grades;
	List<Test> testList;

	TestServiceImpl testServiceImpl = new TestServiceImpl();

	public String getAllTests() throws Exception {
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		if (s_test != null) {
			session.setAttribute("s_test", s_test);
		} else {
			Object o = session.getAttribute("s_test");
			if (o != null) {
				s_test = (Test) o;
			} else {
				s_test = new Test();
			}
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 3);
		testList = testServiceImpl.getTests(s_test, pageBean);
		total = testServiceImpl.testCount(s_test);
		pageCode = PageUtil.genPagation(request.getContextPath() + "/test!getAllTests", total, Integer.parseInt(page),
				3);
		mainPage = "test/showTests.jsp";
		return "success";
	}

	public String testEnd() throws Exception {
		int ObjScore = 0, singleScore = 0, moreScore = 0;
		Map<String, String[]> keyMap = new HashMap<String, String[]>();
		keyMap = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = keyMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			String keyStr = entry.getKey();
			String values[] = entry.getValue();
			String key;
			String value = "";
			String level = "";
			if (keyStr.equals("test.user.id") || keyStr.equals("test.paper.id")) {
				continue;
			}
			if (keyStr.split("-")[1].equals("r")) { // 单选
				key = keyStr.split("-")[2];
				level = keyStr.split("-")[3];
				value = values[0];
				System.out.println("key:" + key + " value:" + value + " level:" + level);
				singleScore += this.calScore(key, value, level, "1");
				System.out.println(totalCount);
				System.out.println("singleScore" + singleScore);
				this.saveStatisticsError();
			} else if (keyStr.split("-")[1].equals("c")) { // 多选
				key = keyStr.split("-")[2];
				level = keyStr.split("-")[3];
				for (String s : values) {
					value += s + ",";
				}
				value = value.substring(0, value.length() - 1);
				moreScore += this.calScore(key, value, level, "2");
				System.out.println(totalCount);
				this.saveStatisticsError();
			} else if (keyStr.split("-")[1].equals("f")) {
				key = keyStr.split("-")[2];
				value = values[0];
				// System.out.println("key:" + key + " value:" + value + " level:" + level +
				// "填空123");
			} else {
				key = keyStr.split("-")[2];
				value = values[0];
				// System.out.println("key:" + key + " value:" + value + " level:" + level +
				// "简答题123");
			}
		}
		ObjScore = singleScore + moreScore;
		test.setSingleScore(singleScore);
		test.setMoreScore(moreScore);
		test.setScore(ObjScore);
		test.setTestDate(new Date(System.currentTimeMillis()));
		test.setfScore(-1);
		test.setsScore(-1);
		test.settScore(-1);
		test.setTotalScore(ObjScore);
		JSONArray jsonArray = JSONArray.fromObject(keyMap);
		test.setKeyMap(jsonArray.toString());
		testServiceImpl.savaTest(test);
		mainPage = "test/testResult.jsp";
		return "success";
	}

	private int calScore(String questionId, String userAnswer, String level, String type) throws Exception {
		this.getStatisticsError();
		Question question = questionServiceImpl.getQuestionById(questionId);
		System.out.println("key:" + questionId + " userAnswer:" + userAnswer + " level:" + level + " type:" + type);
		totalCount += 1;
		if (userAnswer.equals(question.getAnswer())) {
			System.out.println(question.getAnswer());
			if ("1".equals(type)) {
				if (level.equals("难")) {
					difficultCount += 1;
					return 4;
				} else if (level.equals("中")) {
					middleCount += 1;
					return 3;
				} else {
					easyCount += 1;
					return 2;
				}
			} else {
				if (level.equals("难")) {
					difficultCount += 1;
					return 5;
				} else if (level.equals("中")) {
					middleCount += 1;
					return 4;
				} else {
					easyCount += 1;
					return 3;
				}
			}
		} else {
			if (level.equals("难")) {
				difficultCount += 1;
				diffiErrorCount += 1;
			} else if (level.equals("中")) {
				middleCount += 1;
				middErroCount += 1;
			} else {
				easyCount += 1;
				easyErroCount += 1;
			}
			return 0;
		}
	}

	public String getTests() throws Exception {
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		if (s_test != null) {
			session.setAttribute("s_test", s_test);
		} else {
			Object o = session.getAttribute("s_test");
			if (o != null) {
				s_test = (Test) o;
			} else {
				s_test = new Test();
			}
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 3);
		testList = testServiceImpl.getTests(s_test, pageBean);
		total = testServiceImpl.testCount(s_test);
		pageCode = PageUtil.genPagation(request.getContextPath() + "/test!getTests", total, Integer.parseInt(page), 3);
		mainPage = "test/myTest.jsp";
		return "success";
	}

	public String getDetailTestById() throws Exception {
		test = testServiceImpl.getDetailTestById(testId);
		String jsonString = test.getKeyMap().substring(1, test.getKeyMap().length() - 1);
		JSONObject jsonObject = new JSONObject().fromObject(jsonString);
		Iterator iterat = jsonObject.keys();
		paper = test.getPaper();
		System.out.println("paperId:" + paper.getId() + " paperName:" + paper.getPaperName());
		List<Question> questionList = paper.getQuestions();
		Iterator<Question> it = questionList.iterator();
		while (iterat.hasNext()) {
			// System.out.println("key:" + iterat.next());
			String keyStr = (String) iterat.next();
			String key = "";
			String value = jsonObject.getString(keyStr).substring(1, jsonObject.getString(keyStr).length() - 1)
					.replaceAll("\"", "");
			if (keyStr.equals("test.user.id") || keyStr.equals("test.paper.id")) {
				continue;
			}
			if (keyStr.split("-")[1].equals("r")) { // 单选
				key = keyStr.split("-")[2];
				// System.out.println("单选 " + "key:" + key + " value:" + value);
				map1.put(key, value);
			} else if (keyStr.split("-")[1].equals("c")) { // 多选
				key = keyStr.split("-")[2];
				// System.out.println("多选 " + "key:" + key + " value:" + value);
				map1.put(key, value);
			} else if (keyStr.split("-")[1].equals("f")) {
				key = keyStr.split("-")[2];
				// System.out.println("填空 " + "key:" + key + " value:" + value);
				map2.put(key, value);
			} else {
				key = keyStr.split("-")[2];
				// System.out.println("简答 " + "key:" + key + " value:" + value);
				map2.put(key, value);
			}
		}
		if (it.hasNext()) {
			Question q = it.next();
			int id = q.getId();
			map3.put(id, "1");
			if (map1.get(q.getId() + "") != null) {
				q.setUserAnswer(map1.get(q.getId() + ""));
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
			} else {
				q.setUserAnswer(" ");
			}
		}
		while (it.hasNext()) {
			Question q = it.next();
			int id = q.getId();
			if ("1".equals(map3.get(q.getId())))
				continue;
			if (map1.get(q.getId() + "") != null) {
				q.setUserAnswer(map1.get(q.getId() + ""));
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
			} else {
				q.setUserAnswer(" ");
			}
			map3.put(id, "1");
		}
		List<SubQuestion> subquestionList = paper.getSubquestions();
		Iterator<SubQuestion> it1 = subquestionList.iterator();
		if (it1.hasNext()) {
			SubQuestion q = it1.next();
			int id = q.getId();
			map4.put(id, "1");
			if (map2.get(q.getId() + "") != null) {
				q.setUserAnswer(map2.get(q.getId() + ""));
				if ("3".equals(q.getType())) {
					fquestionList.add(q);
					fScore += 5;
					System.out.println("填空");
				} else {
					rquestionList.add(q);
					tScore += 10;
					System.out.println("简答");
				}
			} else {
				q.setUserAnswer(" ");
			}
		}
		while (it1.hasNext()) {
			SubQuestion q = it1.next();
			int id = q.getId();
			if ("1".equals(map4.get(q.getId())))
				continue;
			if (map2.get(q.getId() + "") != null) {
				q.setUserAnswer(map2.get(q.getId() + ""));
				if ("3".equals(q.getType())) {
					fquestionList.add(q);
					fScore += 5;
					// System.out.println("填空");
				} else {
					rquestionList.add(q);
					tScore += 10;
					// System.out.println("简答");
				}
			} else {
				q.setUserAnswer(" ");
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
		mainPage = "test/showDetailTest.jsp";
		return "success";
	}

	public String getOwnTestById() throws Exception {
		test = testServiceImpl.getDetailTestById(testId);
		String jsonString = test.getKeyMap().substring(1, test.getKeyMap().length() - 1);
		JSONObject jsonObject = new JSONObject().fromObject(jsonString);
		Iterator iterat = jsonObject.keys();
		paper = test.getPaper();
		System.out.println("paperId:" + paper.getId() + " paperName:" + paper.getPaperName());
		List<Question> questionList = paper.getQuestions();
		Iterator<Question> it = questionList.iterator();
		while (iterat.hasNext()) {
			// System.out.println("key:" + iterat.next());
			String keyStr = (String) iterat.next();
			String key = "";
			String value = jsonObject.getString(keyStr).substring(1, jsonObject.getString(keyStr).length() - 1)
					.replaceAll("\"", "");
			if (keyStr.equals("test.user.id") || keyStr.equals("test.paper.id")) {
				continue;
			}
			if (keyStr.split("-")[1].equals("r")) { // 单选
				key = keyStr.split("-")[2];
				// System.out.println("单选 " + "key:" + key + " value:" + value);
				map1.put(key, value);
			} else if (keyStr.split("-")[1].equals("c")) { // 多选
				key = keyStr.split("-")[2];
				// System.out.println("多选 " + "key:" + key + " value:" + value);
				map1.put(key, value);
			} else if (keyStr.split("-")[1].equals("f")) {
				key = keyStr.split("-")[2];
				// System.out.println("填空 " + "key:" + key + " value:" + value);
				map2.put(key, value);
			} else {
				key = keyStr.split("-")[2];
				// System.out.println("简答 " + "key:" + key + " value:" + value);
				map2.put(key, value);
			}
		}
		if (it.hasNext()) {
			Question q = it.next();
			int id = q.getId();
			map3.put(id, "1");
			if (!"".equals(map1.get(q.getId() + ""))) {
				q.setUserAnswer(map1.get(q.getId() + ""));
			} else {
				q.setUserAnswer(" ");
			}
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
			if (!"".equals(map1.get(q.getId() + ""))) {
				q.setUserAnswer(map1.get(q.getId() + ""));
			} else {
				q.setUserAnswer(" ");
			}
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
			if (!"".equals(map2.get(q.getId() + ""))) {
				q.setUserAnswer(map2.get(q.getId() + ""));
			} else {
				q.setUserAnswer(" ");
			}
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
			if (!"".equals(map2.get(q.getId() + ""))) {
				q.setUserAnswer(map2.get(q.getId() + ""));
			} else {
				q.setUserAnswer(" ");
			}
			if ("3".equals(q.getType())) {
				fquestionList.add(q);
				fScore += 5;
				// System.out.println("填空");
			} else {
				rquestionList.add(q);
				tScore += 10;
				// System.out.println("简答");
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
		mainPage = "test/showOwnTest.jsp";
		return "success";
	}

	public String readOver() throws Exception {
		this.getStatistics_Error();
		test = testServiceImpl.getDetailTestById(testId);
		System.out.println("Score:" + test.getScore());
		int SubScore = 0, fScore = 0, sScore = 0, totalScore = 0;
		Map<String, String[]> keyMap = new HashMap<String, String[]>();
		keyMap = request.getParameterMap();
		System.out.println("Map:" + keyMap.entrySet());
		Iterator<Entry<String, String[]>> it = keyMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			String keyStr = entry.getKey();
			String values[] = entry.getValue();
			String key;
			String value = "";
			if (keyStr.equals("test.user.id") || keyStr.equals("test.paper.id")) {
				continue;
			}
			if (keyStr.split("-")[0].equals("f")) {
				key = keyStr.split("-")[1];
				if (StringUtil.isNotEmpty(values[0]))
					fScore += Integer.parseInt(values[0]);
				// System.out.println("key:" + key + " fScore:" + fScore + " level:" + "填空123");
			} else if (keyStr.split("-")[0].equals("t")) {
				key = keyStr.split("-")[1];
				if (StringUtil.isNotEmpty(values[0]))
					sScore += Integer.parseInt(values[0]);
				// System.out.println("key:" + key + " sScore:" + sScore + " level:" +
				// "简答题123");
			} else {
				continue;
			}
		}
		SubScore = fScore + sScore;
		totalScore = SubScore + test.getScore();
		System.out.println("SubScore:" + SubScore);
		test.setfScore(fScore);
		test.setsScore(sScore);
		test.settScore(SubScore);
		test.setTotalScore(totalScore);
		testServiceImpl.savaTest(test);
		mainPage = "test/readOverResult.jsp";
		return "success";
	}

	public String delTest() throws Exception {
		test = testServiceImpl.getDetailTestById(testId);
		JSONObject resultJson = new JSONObject();
		testServiceImpl.delTest(test);
		resultJson.put("success", true);
		ResponseUtil.write(resultJson, ServletActionContext.getResponse());
		return null;
	}

	public String getAllGrades() throws Exception {
		HttpSession session = request.getSession();
		testServiceImpl.saveGrades();
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 2);
		grades = testServiceImpl.getAllGrades(pageBean);
		total = testServiceImpl.gradeCount();
		pageCode = PageUtil.genPagation(request.getContextPath() + "/test!getAllGrades", total, Integer.parseInt(page),
				2);
		mainPage = "test/showGrades.jsp";
		return "success";
	}

	public String getStatisticsErrors() throws Exception {
		statistics_Error = testServiceImpl.getStatisticsErrors();
		mainPage = "test/showError.jsp";
		return "success";
	}

	public void getStatisticsError() throws Exception {
		statistics_Error = testServiceImpl.getStatisticsErrors();
		totalCount = statistics_Error.getTotalCount();
		difficultCount = statistics_Error.getDifficultCount();
		middleCount = statistics_Error.getMiddleCount();
		easyCount = statistics_Error.getEasyCount();
		diffiErrorCount = statistics_Error.getDiffiErrorCount();
		middErroCount = statistics_Error.getMiddErroCount();
		easyErroCount = statistics_Error.getEasyErroCount();
	}

	public void saveStatisticsError() throws Exception {
		statistics_Error.setTotalCount(totalCount);
		statistics_Error.setDifficultCount(difficultCount);
		statistics_Error.setMiddleCount(middleCount);
		statistics_Error.setEasyCount(easyCount);
		statistics_Error.setDiffiErrorCount(diffiErrorCount);
		statistics_Error.setMiddErroCount(middErroCount);
		statistics_Error.setEasyErroCount(easyErroCount);
		statistics_Error.setDifficultPercent(StringUtil.getPercentFormat(diffiErrorCount * 1.00 / difficultCount));
		statistics_Error.setMiddlePercent(StringUtil.getPercentFormat(middErroCount * 1.00 / middleCount));
		statistics_Error.setEasyPercent(StringUtil.getPercentFormat(easyErroCount * 1.00 / easyCount));
		testServiceImpl.saveStatisticsError(statistics_Error);
	}

	public String getDifficultyDiagram() throws Exception {
		statistics_Error = testServiceImpl.getStatisticsErrors();
		mainPage = "statError/showDifficulty.jsp";
		return "success";
	}

	public String getErrorDiagram() throws Exception {
		statistics_Error = testServiceImpl.getStatisticsErrors();
		mainPage = "statError/showError.jsp";
		return "success";
	}

	public List<Statistics_Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Statistics_Grade> grades) {
		this.grades = grades;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
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

	public String getMainPage() {
		return mainPage;
	}

	public Test getS_test() {
		return s_test;
	}

	public void setS_test(Test s_test) {
		this.s_test = s_test;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public QuestionServiceImpl getQuestionServiceImpl() {
		return questionServiceImpl;
	}

	public void setQuestionServiceImpl(QuestionServiceImpl questionServiceImpl) {
		this.questionServiceImpl = questionServiceImpl;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public int getFullScore() {
		return fullScore;
	}

	public void setFullScore(int fullScore) {
		this.fullScore = fullScore;
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

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public List<Test> getTestList() {
		return testList;
	}

	public void setTestList(List<Test> testList) {
		this.testList = testList;
	}

	public TestServiceImpl getTestServiceImpl() {
		return testServiceImpl;
	}

	public void setTestServiceImpl(TestServiceImpl testServiceImpl) {
		this.testServiceImpl = testServiceImpl;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public Statistics_Error getStatistics_Error() {
		return statistics_Error;
	}

	public void setStatistics_Error(Statistics_Error statistics_Error) {
		this.statistics_Error = statistics_Error;
	}

}