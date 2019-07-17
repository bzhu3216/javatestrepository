package testpaper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import testpaper.entity.Choice;
import testpaper.service.ChoiceService;
import testpaper.dao.*;

@Service

@Transactional
public class Choiceserviceimpl implements ChoiceService {
	@Autowired
	ChoiceQuestionDAO choicequestiondao1;
	
	@Override
	public List<Choice> getAllChoiceQuestion() {
		// TODO Auto-generated method stub
		return choicequestiondao1.getAllChoiceQuestion();	
	
	}
	@Override	
	public List<Choice> getAllChoiceQuestionByPage(int pageIndex, int pageSize,
			Choice oi){
	
		return choicequestiondao1.getAllChoiceQuestionByPage(pageIndex, pageSize, oi);
		
		
	}
///////////////////////////////
	@Override	
	public int getTotalCount(Choice oi) {
		return choicequestiondao1.getTotalCount(oi);
		
		
	}
	////////////////////////
	@Override	
	public int addChoiceQuestion(Choice ac)
	
	
	{
		return choicequestiondao1.addChoiceQuestion(ac);
		
		
		
	
	}
////////////////////////	
	

}
