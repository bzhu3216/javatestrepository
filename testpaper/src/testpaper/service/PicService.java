package testpaper.service;

import testpaper.entity.Pic;

public interface PicService {
	
	public int addPic(Pic ac);
	public int deletePic(int id);
	public Pic getPicById(int id);
	public int updatePicItem(Pic ac);
	
	
	

}
