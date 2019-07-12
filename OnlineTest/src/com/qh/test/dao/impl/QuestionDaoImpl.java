package com.qh.test.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.qh.test.dao.QuestionDao;
import com.qh.test.entity.PageBean;
import com.qh.test.entity.Question;
import com.qh.test.entity.User;
import com.qh.test.util.HibernateUitl;
import com.qh.test.util.StringUtil;

public class QuestionDaoImpl implements QuestionDao {

	SessionFactory sessionFactory = HibernateUitl.getSessionFactory();

	public List<Question> getAllQuestions(Question question, PageBean pagebean) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from Question as q";
		if (StringUtil.isNotEmpty(question.getSubject())) {
			hql += " and q.subject like '%" + question.getSubject() + "%'";
		}
		Query query = session.createQuery(hql.toString().replaceFirst("and", "where"));
		if (pagebean != null) {
			query.setFirstResult(pagebean.getStart());
			query.setMaxResults(pagebean.getPageSize());
		}
		List<Question> questionList = (List<Question>) query.list();
		session.getTransaction().commit();
		return questionList;
	}

	public int questionCount(Question question) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		StringBuffer sql = new StringBuffer("select count(*) from t_question");
		if (StringUtil.isNotEmpty(question.getSubject())) {
			sql.append(" and subject like '%" + question.getSubject() + "%'");
		}
		Query query = session.createSQLQuery(sql.toString().replaceFirst("and", "where"));
		int count = ((BigInteger) query.uniqueResult()).intValue();
		session.getTransaction().commit();
		return count;
	}

	public Question getQuestionById(String questionId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Question question = (Question) session.get(Question.class, Integer.parseInt(questionId));
		session.getTransaction().commit();
		return question;
	}

	public void saveQuestion(Question question) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.merge(question);
		session.getTransaction().commit();
	}

	public void deleteQuestion(Question question) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(question);
		session.getTransaction().commit();
	}

	public boolean existQuestionByPaperId(String paperId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Question as q where q.paper.id=:paperId");
		query.setString("paperId", paperId);
		@SuppressWarnings("unchecked")
		List<Question> userList = (List<Question>) query.list();
		session.getTransaction().commit();
		if (userList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

}
