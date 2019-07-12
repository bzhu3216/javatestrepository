package com.qh.test.dao;

import java.util.List;

import com.qh.test.entity.PageBean;
import com.qh.test.entity.Question;

public interface QuestionDao {
	public List<Question> getAllQuestions(Question question, PageBean pagebean) throws Exception;

	public int questionCount(Question question) throws Exception;

	public Question getQuestionById(String questionID) throws Exception;

	public void saveQuestion(Question question) throws Exception;

	public void deleteQuestion(Question question) throws Exception;

	public boolean existQuestionByPaperId(String paperId) throws Exception;
}
