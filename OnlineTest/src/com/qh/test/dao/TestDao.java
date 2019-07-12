package com.qh.test.dao;

import java.util.List;

import com.qh.test.entity.PageBean;
import com.qh.test.entity.Statistics_Error;
import com.qh.test.entity.Statistics_Grade;
import com.qh.test.entity.Test;

public interface TestDao {
	public boolean existUserByUserId(String userId) throws Exception;
	
	public boolean existPaperByPaperId(String paperId) throws Exception;

	public List<Test> getTests(Test test, PageBean pageBean) throws Exception;

	public int testCount(Test test) throws Exception;

	public void savaTest(Test test) throws Exception;

	public Test getDetailTestById(String testId) throws Exception;
	
	public void delTest(Test test) throws Exception;
	
	public List<Statistics_Grade> getAllGrades(PageBean pageBean) throws Exception;
	
	public int gradeCount() throws Exception;
	
	public void saveGrades() throws Exception;
	
	public Statistics_Error getStatisticsErrors() throws Exception;
	
	public void saveStatisticsError(Statistics_Error statistics_Error) throws Exception;
}
