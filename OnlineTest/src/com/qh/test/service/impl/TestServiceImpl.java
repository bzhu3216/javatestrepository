package com.qh.test.service.impl;

import java.util.List;

import com.qh.test.dao.impl.TestDaoImpl;
import com.qh.test.entity.PageBean;
import com.qh.test.entity.Statistics_Error;
import com.qh.test.entity.Statistics_Grade;
import com.qh.test.entity.Test;
import com.qh.test.service.TestService;

public class TestServiceImpl implements TestService {

	TestDaoImpl testDaoImpl = new TestDaoImpl();

	public List<Test> getTests(Test test, PageBean pageBean) throws Exception {
		return testDaoImpl.getTests(test, pageBean);
	}

	public int testCount(Test test) throws Exception {
		return testDaoImpl.testCount(test);
	}

	public void savaTest(Test test) throws Exception {
		testDaoImpl.savaTest(test);
	}

	public Test getDetailTestById(String testId) throws Exception {
		return testDaoImpl.getDetailTestById(testId);
	}

	public boolean existUserByUserId(String userId) throws Exception {
		return testDaoImpl.existUserByUserId(userId);
	}

	public void delTest(Test test) throws Exception {
		testDaoImpl.delTest(test);
	}

	public List<Statistics_Grade> getAllGrades(PageBean pageBean) throws Exception {
		return testDaoImpl.getAllGrades(pageBean);
	}

	public int gradeCount() throws Exception {
		return testDaoImpl.gradeCount();
	}

	public void saveGrades() throws Exception {
		testDaoImpl.saveGrades();
	}

	public boolean existPaperByPaperId(String paperId) throws Exception {
		return testDaoImpl.existPaperByPaperId(paperId);
	}

	public Statistics_Error getStatisticsErrors() throws Exception {
		return testDaoImpl.getStatisticsErrors();
	}

	public void saveStatisticsError(Statistics_Error statistics_Error) throws Exception {
		testDaoImpl.saveStatisticsError(statistics_Error);
	}

}
