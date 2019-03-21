package testpaper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import testpaper.entity.ChoiceQuestion;
import testpaper.service.ChoiceService;;
@RequestMapping("/cq")
@Controller
public class ChoiceQuestionHandler {
	
	
	@Autowired
	ChoiceService choiceservice;
	@RequestMapping("/getAllchoicequestion")
	@ResponseBody	
	public List<ChoiceQuestion> getAbilitycontent() {
		List<ChoiceQuestion> acs = choiceservice.getAllChoiceQuestion();
	
		return  acs;
	}
	
	/////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
}
