package testpaper.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import testpaper.dao.ChaperDAO;
import testpaper.entity.Chapter;
@Repository
public class ChaperDAOimpl implements ChaperDAO {
	@Autowired
	SessionFactory sessionFactory;

	public ChaperDAOimpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Chapter> getAllChaptercontent() {
		// TODO Auto-generated method stub
		String hql = "from Chapter";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}

}
