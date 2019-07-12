package com.qh.test.service.impl;

import java.util.List;

import com.qh.test.dao.impl.QuestionDaoImpl;
import com.qh.test.entity.PageBean;
import com.qh.test.entity.Question;
import com.qh.test.service.QuestionService;

public class QuestionServiceImpl implements QuestionService{

	QuestionDaoImpl questionDao=new QuestionDaoImpl();
	
	public List<Question> getAllQuestions(Question question, PageBean pagebean) throws Exception {
		return questionDao.getAllQuestions(question,pagebean);
	}

	public int questionCount(Question question) throws Exception {
		return questionDao.questionCount(question);
	}

	public Question getQuestionById(String questionId) throws Exception {
		return questionDao.getQuestionById(questionId);
	}

	public void saveQuestion(Question question) throws Exception {
		questionDao.saveQuestion(question);
	}

	public void deleteQuestion(Question question) throws Exception {
		questionDao.deleteQuestion(question);
	}

	public boolean existQuestionByPaperId(String paperId) throws Exception {
		return questionDao.existQuestionByPaperId(paperId);
	}

}
