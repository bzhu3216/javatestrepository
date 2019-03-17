package testpaper.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import testpaper.entity.Abilitycontent;
import testpaper.service.AbilityInfoService;
import testpaper.dao.*;
@Service

@Transactional
public class AbilityInfiServiceimpl implements AbilityInfoService {
	@Autowired
	AbilityInfoDAO abilityInfoDAO1;
	@Override
	public List<Abilitycontent> getAllAbilitycontent() 
	{		
	return	abilityInfoDAO1.getAllAbilitycontent();
	}

	
///////////////////////////////////////////////////
	
	@Override
	public int addAbilitItem(Abilitycontent ac) {
		return abilityInfoDAO1.addAbilitItem(ac);
	}
	
	
	
	//////////////////////////////////////////////////

}
