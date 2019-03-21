package testpaper.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import testpaper.entity.ChoiceQuestion;
import testpaper.dao.*;

@Repository
public class ChoiceQuestDAOimpl implements ChoiceQuestionDAO{
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public List<ChoiceQuestion> getAllChoiceQuestion(){
	
			String hql = "from ChoiceQuestion";
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			return query.list();
		
	}			
	//////////////////////////////////
	
	
	
	
	
	////////////////////////////////////
	
	
////////////////////////////////////
	
////////////////////////////////////
	
	

}
