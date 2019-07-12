package com.qh.test.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.jsp.tagext.IterationTag;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.qh.test.dao.SubQuestionDao;
import com.qh.test.entity.PageBean;
import com.qh.test.entity.Question;
import com.qh.test.entity.SubQuestion;
import com.qh.test.util.HibernateUitl;
import com.qh.test.util.StringUtil;

public class SubQuestionDaoImpl implements SubQuestionDao {

	SessionFactory sessionFactory = HibernateUitl.getSessionFactory();

	public List<SubQuestion> getAllSubQuestions(SubQuestion subQuestion, PageBean pageBean) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from SubQuestion";
		if (StringUtil.isNotEmpty(subQuestion.getSubject())) {
			hql += " and subject like '%" + subQuestion.getSubject() + "%'";
		}
		Query query = session.createQuery(hql.toString().replaceFirst("and", "where"));
		if (pageBean != null) {
			query.setFirstResult(pageBean.getStart());
			query.setMaxResults(pageBean.getPageSize());
		}
		List<SubQuestion> subquestionList = (List<SubQuestion>) query.list();
		session.getTransaction().commit();
		return subquestionList;
	}

	public int subQuestionCount(SubQuestion subQuestion) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		StringBuffer sql = new StringBuffer("select count(*) from t_subquestion");

		if (StringUtil.isNotEmpty(subQuestion.getSubject())) {
			sql.append(" and subject like '%" + subQuestion.getSubject() + "%'");
		}

		Query query = session.createSQLQuery(sql.toString().replaceFirst("and", "where"));
		int count = ((BigInteger) query.uniqueResult()).intValue();
		session.getTransaction().commit();
		return count;
	}

	public SubQuestion getSubQuestionById(String subquestionId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		SubQuestion subquestion = (SubQuestion) session.get(SubQuestion.class, Integer.parseInt(subquestionId));
		session.getTransaction().commit();
		return subquestion;
	}

	public void saveSubQuestion(SubQuestion subQuestion) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.merge(subQuestion);
		session.getTransaction().commit();
	}

	public void deleteSubQuesion(SubQuestion subQuestion) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(subQuestion);
		session.getTransaction().commit();
	}

	public boolean existSubQuestionByPaperId(String paperId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from SubQuestion as q where q.paper.id=:paperId");
		query.setString("paperId", paperId);
		@SuppressWarnings("unchecked")
		List<SubQuestion> userList = (List<SubQuestion>) query.list();
		session.getTransaction().commit();
		if (userList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

}
