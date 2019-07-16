package testpaper.dao;



import testpaper.entity.Pic;
import testpaper.entity.Picdata;

public interface PicDao {
	
	
	public int addPic(Picdata ac);
	public int deletePic(int id);
	public Picdata getPicById(int id);
	public int updatePicItem(Pic ac);
	
	
	

}
