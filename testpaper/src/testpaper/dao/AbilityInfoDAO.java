package testpaper.dao;

import java.util.List;



import testpaper.entity.Abilitycontent;
public interface AbilityInfoDAO {

	
	public List<Abilitycontent> getAllAbilitycontent();
	public int addAbilitItem(Abilitycontent ac);
	public int deleteAbilitItem(int id);
	public Abilitycontent getAbilityInfoById(int id);
	public int updateAbilitItem(Abilitycontent ac);
	
}
