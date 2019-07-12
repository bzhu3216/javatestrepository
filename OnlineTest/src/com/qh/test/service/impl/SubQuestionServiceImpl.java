package com.qh.test.service.impl;

import java.util.List;

import com.qh.test.dao.SubQuestionDao;
import com.qh.test.dao.impl.SubQuestionDaoImpl;
import com.qh.test.entity.PageBean;
import com.qh.test.entity.SubQuestion;
import com.qh.test.service.SubQuestionService;

public class SubQuestionServiceImpl implements SubQuestionService {

	SubQuestionDaoImpl subQuestionDaoImpl = new SubQuestionDaoImpl();

	public List<SubQuestion> getAllSubQuestions(SubQuestion subQuestion, PageBean pageBean) throws Exception {
		return subQuestionDaoImpl.getAllSubQuestions(subQuestion, pageBean);
	}

	public int subQuestionCount(SubQuestion subQuestion) throws Exception {
		return subQuestionDaoImpl.subQuestionCount(subQuestion);
	}

	public SubQuestion getSubQuestionById(String subquestionId) throws Exception {
		return subQuestionDaoImpl.getSubQuestionById(subquestionId);
	}

	public void saveSubQuestion(SubQuestion subQuestion) throws Exception {
		subQuestionDaoImpl.saveSubQuestion(subQuestion);
	}

	public void deleteSubQuesion(SubQuestion subQuestion) throws Exception {
		subQuestionDaoImpl.deleteSubQuesion(subQuestion);
	}

	public boolean existSubQuestionByPaperId(String paperId) throws Exception {
		return subQuestionDaoImpl.existSubQuestionByPaperId(paperId);
	}

}
