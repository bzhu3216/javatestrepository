package testpaper.dao.impl;
import testpaper.entity.Picdata;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import testpaper.dao.PicDao;
import testpaper.entity.Abilitycontent;
import testpaper.entity.Pic;
@Repository
public class PicDaoimpl implements PicDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public int addPic(Picdata ac) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(ac);
	}

	@Override
	public int deletePic(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Picdata getPicById(int id) {
		// TODO Auto-generated method stub
		//String hql = "from Picdata ac where ac.id=" + id;
		String hql = "from Picdata ac where ac.id=2";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		return (Picdata) query.uniqueResult();
		//return (Picdata) query.getFirstResult();
	}
	

	@Override
	public int updatePicItem(Pic ac) {
		// TODO Auto-generated method stub
		return 0;
	}

}
