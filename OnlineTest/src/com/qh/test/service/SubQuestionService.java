package com.qh.test.service;

import java.util.List;

import com.qh.test.entity.PageBean;
import com.qh.test.entity.SubQuestion;

public interface SubQuestionService {
	public List<SubQuestion> getAllSubQuestions(SubQuestion subQuestion, PageBean pageBean) throws Exception;

	public int subQuestionCount(SubQuestion subQuestion) throws Exception;

	public SubQuestion getSubQuestionById(String subquestionId) throws Exception;

	public void saveSubQuestion(SubQuestion subQuestion) throws Exception;

	public void deleteSubQuesion(SubQuestion subQuestion) throws Exception;

	public boolean existSubQuestionByPaperId(String paperId) throws Exception;
}
