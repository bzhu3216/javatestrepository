package testpaper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import testpaper.service.*;
import testpaper.entity.Pic;
import testpaper.dao.*;
@Service

@Transactional
public class PicServiceimpl implements PicService {
	@Autowired
	PicDao PicDAO1;
	@Override
	public int addPic(Pic ac) {
		// TODO Auto-generated method stub
		return PicDAO1.addPic(ac);
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
