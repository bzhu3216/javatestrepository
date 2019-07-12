package com.qh.test.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.qh.test.entity.PageBean;
import com.qh.test.entity.Paper;
import com.qh.test.entity.Question;
import com.qh.test.entity.Statistics_Error;
import com.qh.test.entity.Statistics_Grade;
import com.qh.test.entity.SubQuestion;
import com.qh.test.entity.Test;
import com.qh.test.service.impl.PaperServiceImpl;
import com.qh.test.service.impl.TestServiceImpl;
import com.qh.test.util.HibernateUitl;
import com.qh.test.util.JsonUtil;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.processors.JsonBeanProcessor;

public class Test1 {

	SessionFactory sessionFactory = HibernateUitl.getSessionFactory();
	Map<String, String> map1 = new HashMap<String, String>();
	Map<String, String> map2 = new HashMap<String, String>();

	@org.junit.Test
	public void test1() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from Test as a where a.id=36";
		Query query = session.createQuery(hql);
		List<Test> list = (List<Test>) query.list();
		String jsonString = list.get(0).getKeyMap().substring(1, list.get(0).getKeyMap().length() - 1);
		JSONObject jsonObject = new JSONObject().fromObject(jsonString);
		System.out.println("jsonObject " + jsonObject);
		Map<String, String[]> keyMap = new HashMap<String, String[]>();
		// keyMap = JsonUtil.toHashMap(jsonObject);
		System.out.println(jsonObject.entrySet());
		System.out.println(jsonObject.getString("test.user.id"));
		Iterator it = jsonObject.keys();
		while (it.hasNext()) {
			// System.out.println("key:" + it.next());
			String keyStr = (String) it.next();
			String key = "";
			String value = jsonObject.getString(keyStr).substring(1, jsonObject.getString(keyStr).length() - 1)
					.replaceAll("\"", "");
			if (keyStr.equals("test.user.id") || keyStr.equals("test.paper.id")) {
				continue;
			}
			if (keyStr.split("-")[1].equals("r")) { // 单选
				key = keyStr.split("-")[2];
				System.out.println("单选  " + "key:" + key + " value:" + value);
				map1.put(key, value);
			} else if (keyStr.split("-")[1].equals("c")) { // 多选
				key = keyStr.split("-")[2];
				System.out.println("多选  " + "key:" + key + " value:" + value);
				map1.put(key, value);
			} else if (keyStr.split("-")[1].equals("f")) {
				key = keyStr.split("-")[2];
				System.out.println("填空  " + "key:" + key + " value:" + value);
				map2.put(key, value);
			} else {
				key = keyStr.split("-")[2];
				System.out.println("简答  " + "key:" + key + " value:" + value);
				map2.put(key, value);
			}
		}
		System.out.println(map1);
		System.out.println(map2);
		System.out.println("map1Get:" + map1.get(""));
		System.out.println("map1Get2:" + map1.get("38"));
		/*
		 * Map<String, String[]> keyMap = new HashMap<String, String[]>(); String
		 * jsonString = list.get(0).getKeyMap(); keyMap =
		 * JsonUtil.toHashMap(JSONObject.fromObject(jsonString));
		 */

		session.getTransaction().commit();
	}

	@org.junit.Test
	public void test2() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql = "from Statistics_Grade";
		Query query = session.createQuery(hql);
		System.out.println(query.list());
		session.getTransaction().commit();
	}

	@org.junit.Test
	public void test3() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Statistics_Grade statistics_Grade = new Statistics_Grade();
		statistics_Grade.setAverageScore(11.2);
		Paper paper = new Paper();
		paper.setId(24);
		statistics_Grade.setPaper(paper);
		session.merge(statistics_Grade);
		session.getTransaction().commit();
	}

	@org.junit.Test
	public void test4() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Statistics_Grade statistics_Grade = new Statistics_Grade();
		String hql = "from Statistics_Grade as a where a.id=:id";
		Query query = session.createQuery(hql);
		query.setString("id", "21");
		statistics_Grade = (Statistics_Grade) query.uniqueResult();
		System.out.println(statistics_Grade.getId());
		session.delete(statistics_Grade);
		session.getTransaction().commit();
	}

	@org.junit.Test
	public void test5() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Statistics_Grade statistics_Grade = new Statistics_Grade();
		String hql = "from Statistics_Grade as a where a.id=:id";
		Query query = session.createQuery(hql);
		query.setString("id", "24");
		statistics_Grade = (Statistics_Grade) query.uniqueResult();
		System.out.println(statistics_Grade.getId());
		System.out.println(statistics_Grade.getPaper().getPaperName());
		session.getTransaction().commit();
	}

	@org.junit.Test
	public void test6() throws Exception {
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
				excellentPercent = excellentCount / totalCount;
				passPercent = passCount / totalCount;
				averageScore = score / totalCount;
			}
			System.out.println("试卷名称" + grade.getPaper().getPaperName());
			System.out.println("满分" + totalScore);
			System.out.println("考试次数" + totalCount);
			System.out.println("考试得分" + score);
			System.out.println("及格数" + passCount);
			System.out.println("优秀数" + excellentCount);
			System.out.println("及格率" + passPercent);
			System.out.println("优秀率" + excellentPercent);
			System.out.println("平均分" + averageScore);
			System.out.println("最高分" + maxScore);
			System.out.println("最低分" + minScore);
			grade.setTotalCount(totalCount);
			grade.setPassCount(passCount);
			grade.setExcellentCount(excellentCount);
			grade.setPassPercent(passPercent + "");
			grade.setExcellentPercent(excellentPercent + "");
			grade.setAverageScore(averageScore);
			grade.setMaxScore(maxScore);
			grade.setMinScore(minScore);
			session.merge(grade);
		}
		session.getTransaction().commit();
	}

	@org.junit.Test
	public void test7() throws Exception {
		Session session = HibernateUitl.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String sql = "select * from t_exam where paperId=" + 19;
		Query query = session.createSQLQuery(sql);
		int size = query.list().size();
		System.out.println(size);
	}
	
	@org.junit.Test
	public void test8() throws Exception{
		Session session = HibernateUitl.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Statistics_Error statistics_Error=new Statistics_Error();
		session.merge(statistics_Error);
		session.getTransaction().commit();
	}
	
	@org.junit.Test
	public void test9() throws Exception{
		TestServiceImpl testServiceImpl=new TestServiceImpl();
		Statistics_Error statistics_Error=testServiceImpl.getStatisticsErrors();
		System.out.println(statistics_Error.getId());
		System.out.println(statistics_Error.getTotalCount());
		System.out.println(statistics_Error.getDifficultCount());
		System.out.println(statistics_Error.getEasyCount());
		System.out.println(statistics_Error.getMiddleCount());
	}
}
