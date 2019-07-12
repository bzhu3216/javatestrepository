package com.qh.test.dao.impl;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.qh.test.dao.TestDao;
import com.qh.test.entity.PageBean;
import com.qh.test.entity.Paper;
import com.qh.test.entity.Statistics_Error;
import com.qh.test.entity.Statistics_Grade;
import com.qh.test.entity.Test;
import com.qh.test.entity.User;
import com.qh.test.util.HibernateUitl;
import com.qh.test.util.StringUtil;

import sun.swing.StringUIClientPropertyKey;

public class TestDaoImpl implements TestDao {

	SessionFactory sessionFactory = HibernateUitl.getSessionFactory();

	public List<Test> getTests(Test test, PageBean pageBean) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from Test as test";
		if (test.getUser() != null && StringUtil.isNotEmpty(test.getUser().getId())) {
			hql += " and test.user.id like '%" + test.getUser().getId() + "%'";
		}
		if (test.getUser() != null && StringUtil.isNotEmpty(test.getUser().getName())) {
			hql += " and test.user.name like '%" + test.getUser().getName() + "%'";
		}
		Query query = session.createQuery(hql.toString().replaceFirst("and", "where"));
		if (pageBean != null) {
			query.setFirstResult(pageBean.getStart());
			query.setMaxResults(pageBean.getPageSize());
		}
		@SuppressWarnings("unchecked")
		List<Test> testList = (List<Test>) query.list();
		session.getTransaction().commit();
		return testList;
	}

	public int testCount(Test test) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sql = "select count(*) from t_exam t1,t_student t2 where t1.studentId=t2.id";
		if (test.getUser() != null && StringUtil.isNotEmpty(test.getUser().getId())) {
			sql += " and t2.id like '%" + test.getUser().getId() + "%'";
		}
		if (test.getUser() != null && StringUtil.isNotEmpty(test.getUser().getName())) {
			sql += " and t2.name like '%" + test.getUser().getName() + "%'";
		}
		Query query = session.createSQLQuery(sql.toString());
		int count = ((BigInteger) query.uniqueResult()).intValue();
		session.getTransaction().commit();
		return count;
	}

	public void savaTest(Test test) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.merge(test);
		session.getTransaction().commit();
	}

	public Test getDetailTestById(String testId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Test test = (Test) session.get(Test.class, Integer.parseInt(testId));
		session.getTransaction().commit();
		return test;
	}

	public boolean existUserByUserId(String userId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Test as t where t.user.id=:userId");
		query.setString("userId", userId);
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) query.list();
		System.out.println(userList.size());
		session.getTransaction().commit();
		if (userList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void delTest(Test test) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(test);
		session.getTransaction().commit();
	}

	public List<Statistics_Grade> getAllGrades(PageBean pageBean) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from Statistics_Grade";
		Query query = session.createQuery(hql);
		if (pageBean != null) {
			query.setFirstResult(pageBean.getStart());
			query.setMaxResults(pageBean.getPageSize());
		}
		@SuppressWarnings("unchecked")
		List<Statistics_Grade> grades = (List<Statistics_Grade>) query.list();
		session.getTransaction().commit();
		return grades;
	}

	public int gradeCount() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sql = "select count(*) from statistics_grade";
		Query query = session.createSQLQuery(sql.toString());
		int count = ((BigInteger) query.uniqueResult()).intValue();
		System.out.println(count);
		session.getTransaction().commit();
		return count;
	}

	public void saveGrades() throws Exception {
		Session session = HibernateUitl.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "from Statistics_Grade";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Statistics_Grade> grades = query.list();
		Iterator<Statistics_Grade> it1 = grades.iterator();
		while (it1.hasNext()) {
			int totalCount = 0, passCount = 0, excellentCount = 0, maxScore = 0, minScore = 0, totalScore = 0,
					score = 0;
			double averageScore = 0, passPercent = 0, excellentPercent = 0;
			Statistics_Grade grade = it1.next();
			String hql1 = "from Test as t where t.paper.id=:paperId";
			Query query1 = session.createQuery(hql1);
			query1.setString("paperId", grade.getPaper().getId() + "");
			List tests = query1.list();
			totalCount = tests.size();
			totalScore = grade.getPaper().getFullScore();
			Iterator<Test> it2 = tests.iterator();
			while (it2.hasNext()) {
				Test test = it2.next();
				if (maxScore < test.getTotalScore())
					maxScore = test.getTotalScore();
				if (minScore > test.getTotalScore())
					minScore = test.getTotalScore();
				score += test.getTotalScore();
				if (test.getTotalScore() >= totalScore * 0.6)
					passCount += 1;
				if (test.getTotalScore() >= totalScore * 0.8)
					excellentCount += 1;
			}
			if (totalCount != 0) {
				excellentPercent = excellentCount * 1.00 / totalCount;
				passPercent = passCount * 1.00 / totalCount;
				averageScore = score * 1.00 / totalCount;
			}
			grade.setTotalCount(totalCount);
			grade.setPassCount(passCount);
			grade.setExcellentCount(excellentCount);
			grade.setPassPercent(StringUtil.getPercentFormat(passPercent));
			grade.setExcellentPercent(StringUtil.getPercentFormat(excellentPercent));
			grade.setAverageScore(averageScore);
			grade.setMaxScore(maxScore);
			grade.setMinScore(minScore);
			session.merge(grade);
		}
		session.getTransaction().commit();
	}

	public boolean existPaperByPaperId(String paperId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Test as t where t.paper.id=:paperId");
		query.setString("paperId", paperId);
		@SuppressWarnings("unchecked")
		List<Paper> paperList = (List<Paper>) query.list();
		System.out.println(paperList.size());
		session.getTransaction().commit();
		if (paperList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Statistics_Error getStatisticsErrors() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from Statistics_Error as a where a.id=:id";
		Query query = session.createQuery(hql);
		query.setString("id", "23");
		Statistics_Error statistics_Error = (Statistics_Error) query.uniqueResult();
		session.getTransaction().commit();
		return statistics_Error;
	}

	public void saveStatisticsError(Statistics_Error statistics_Error) throws Exception {
		Session session = HibernateUitl.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.merge(statistics_Error);
		session.getTransaction().commit();
	}

}
