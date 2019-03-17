package testpaper.dao;

import java.util.List;



import testpaper.entity.Abilitycontent;
public interface AbilityInfoDAO {

	
	public List<Abilitycontent> getAllAbilitycontent();
	public int addAbilitItem(Abilitycontent ac);	
	
}
