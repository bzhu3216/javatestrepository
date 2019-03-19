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
			return "s";
		}
		 return "f";
	}

	/////////////////////////////////////////////////////////
	@RequestMapping("/deleteAbilityInfo")
	@ResponseBody
	public String deleteAbilitycontent(int id){
	
		//System.out.println("ok........................"+id);	
		int dOK = abilityInfoService.deleteAbilitItem(id);
		if(dOK > 0){
			return "s";
		}
		 return "f";
	}
	

	
/////////////////////////////////////////////////////////
	@RequestMapping("/updateAbilityInfo")
	@ResponseBody
	public String updateAbilitycontent(int id ,String abilitystr){
	
		Abilitycontent ac=abilityInfoService.getAbilityInfoById(id);
		ac.setAbilitystr(abilitystr);		
	   int dOK = abilityInfoService.updateAbilitItem(ac);
		if(dOK > 0){
			return "s";
		}
		 return "f";
	
	
		
	
	}	
	
	
	
	
	//////////////////////////////////////////////////////
	
}
