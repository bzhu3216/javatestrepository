package testpaper.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import testpaper.dao.PicDao;
import testpaper.entity.Pic;
@Repository
public class PicDaoimpl implements PicDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public int addPic(Pic ac) {
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
	public Pic getPicById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePicItem(Pic ac) {
		// TODO Auto-generated method stub
		return 0;
	}

}
