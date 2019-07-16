package testpaper.service;

import testpaper.entity.Pic;
import testpaper.entity.Picdata;

public interface PicService {
	
	public int addPic(Picdata ac);
	public int deletePic(int id);
	public Picdata getPicById(int id);
	public int updatePicItem(Pic ac);
	
	
	

}
