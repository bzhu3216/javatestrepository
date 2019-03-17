package testpaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;



import testpaper.entity.Abilitycontent;
import testpaper.service.*;

@RequestMapping("/ab")
@Controller
public class AbilityInfiHandler {


	@Autowired
	AbilityInfoService abilityInfoService;
	@RequestMapping("/getAllabilityInfo")
	@ResponseBody	
	public List<Abilitycontent> getAbilitycontent() {
		List<Abilitycontent> acs = abilityInfoService.getAllAbilitycontent();
		
	
		return  acs;
	}
	
	/////////////////////////////////////////////////////
	
	@RequestMapping("/saveAbilityInfo")
	@ResponseBody
	public String addAbilitycontent(Abilitycontent ac){
	
			
		int dOK = abilityInfoService.addAbilitItem(ac);
		if(dOK > 0){
			return "{\"success\":\"true\",\"message\":\"success!!!\"}";
		}
		 return "{\"success\":\"false\",\"message\":\"not succeed!\"}";
	}

	/////////////////////////////////////////////////////////
	@RequestMapping("/deleteAbilityInfo")
	@ResponseBody
	public String deleteAbilitycontent(int id){
	
		System.out.println("ok........................"+id);	
		int dOK = abilityInfoService.deleteAbilitItem(id);
		if(dOK > 0){
			return "{\"success\":\"true\",\"message\":\"DEL success!!!\"}";
		}
		 return "{\"success\":\"false\",\"message\":\"not succeed!\"}";
	}
	
	
	
/////////////////////////////////////////////////////////
	
}
