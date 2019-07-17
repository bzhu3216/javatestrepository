package testpaper.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import testpaper.entity.Choice;
import testpaper.dao.*;

@Repository
public class ChoiceQuestDAOimpl implements ChoiceQuestionDAO{
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public List<Choice> getAllChoiceQuestion(){
	
			String hql = "from ChoiceQuestion";
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			return query.list();
		
	}			
	//////////////////////////////////
	@Override
	public List<Choice> getAllChoiceQuestionByPage(int pageIndex, int pageSize,
			Choice oi){
		
		
		
		String hql = "from ChoiceQuestion oi where 1=1";
		
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		return query.setFirstResult((pageIndex - 1) * pageSize)
				.setMaxResults(pageSize).list();
		
	}
	
	////////////////////////////////////
	@Override
	public int getTotalCount(Choice oi){
		
		int count = 0;
		String hql = "select count(oi) from ChoiceQuestion oi where 1=1";		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		count = ((Long) query.uniqueResult()).intValue();
		return count;
		
		
		
	}	
	
	
////////////////////////////////////
	@Override
	 public int addChoiceQuestion(Choice ac)
	
	 {
		
		
		Session session = sessionFactory.getCurrentSession();
		
		int temp= (Integer)session.save(ac);
		//System.out.println(ac.getId());
		
		
		
		
		
		return temp;
		
		
	 }
	
	
	
	
////////////////////////////////////
	
	

}
