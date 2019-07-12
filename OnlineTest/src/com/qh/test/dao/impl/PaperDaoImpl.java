package com.qh.test.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.qh.test.dao.PaperDao;
import com.qh.test.entity.Paper;
import com.qh.test.entity.Statistics_Grade;
import com.qh.test.util.HibernateUitl;

public class PaperDaoImpl implements PaperDao{
	
	SessionFactory sessionFactory=HibernateUitl.getSessionFactory();
	Statistics_Grade statistics_Grade=new Statistics_Grade();
	
	public List<Paper> getAllPapers() throws Exception {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from Paper");
		List<Paper> paperList=(List<Paper>)query.list();
		session.getTransaction().commit();
		return paperList;
	}

	public Paper getPaper(String paperId) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Paper paper=(Paper)session.get(Paper.class, Integer.parseInt(paperId));
		session.getTransaction().commit();
		return paper;
	}

	public void savePaper(Paper paper) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.merge(paper);
		session.getTransaction().commit();
	}

	public Paper getPaperByName(String paperName) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql="from Paper as a where a.paperName=:paperName";
		Query query=session.createQuery(hql);
		query.setString("paperName", paperName);
		Paper paper=(Paper)query.uniqueResult();
		session.getTransaction().commit();
		return paper;
	}

	public void deletePaper(Paper paper) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(paper);
		session.getTransaction().commit();
	}

	public void addPaper(Paper paper) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.merge(paper);
		Query query=session.createQuery("from Paper as p where p.paperName=:paperName");
		query.setString("paperName", paper.getPaperName());
		Paper paper1=(Paper) query.uniqueResult();
		//System.out.println(paper1.getId());
		statistics_Grade.setPaper(paper1);
		session.merge(statistics_Grade);
		session.getTransaction().commit();
	}
	
}
