package testpaper.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testpaper.dao.AbilityInfoDAO;
import testpaper.entity.Abilitycontent;
@Repository
public class AbilityInfoDAOimpl implements AbilityInfoDAO {
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public List<Abilitycontent> getAllAbilitycontent() {
	
			String hql = "from Abilitycontent";
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			return query.list();
		
		
	}		
////////////////////////////////////////////////////////
	@Override
	public int addAbilitItem(Abilitycontent ac) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(ac);
	}
//////////////////////////////////////////////////
	@Override
	public Abilitycontent getAbilityInfoById(int id) {
		String hql = "from Abilitycontent ac where ac.id=" + id;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		return (Abilitycontent) query.uniqueResult();
	}
///////////////////////////////////////////////////

	public int deleteAbilitItem(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		try {
			
			Abilitycontent ac=getAbilityInfoById(id);
			
			session.delete(ac);
		} catch (Exception e) {
			return 0;
		}
		  return 1;
		
	}
	
	
	
	///////////////////////////////////////////////////
	

}
