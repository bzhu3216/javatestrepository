package testpaper.service;

import java.util.List;
import testpaper.entity.Abilitycontent;
public interface AbilityInfoService {

	
	
       
	   public List<Abilitycontent> getAllAbilitycontent();
		public int addAbilitItem(Abilitycontent ac);
		public int deleteAbilitItem(int id);
		public int updateAbilitItem(Abilitycontent ac);
		public Abilitycontent getAbilityInfoById(int id);
		/*
	   public Abilitycontent getAbilityInfoById(int id);
		public int addAbility(Abilitycontent ac);
		public int deleteOrder(Abilitycontent ac);
		public int modifyOrder(Abilitycontent ac);
		public int deleteOrderDetail(Abilitycontent ac);
	*/


	
	
	
}
