package testpaper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import testpaper.entity.ChoiceQuestion;
import testpaper.service.ChoiceService;
import testpaper.dao.*;

@Service

@Transactional
public class Choiceserviceimpl implements ChoiceService {
	@Autowired
	ChoiceQuestionDAO choicequestiondao1;
	
	@Override
	public List<ChoiceQuestion> getAllChoiceQuestion() {
		// TODO Auto-generated method stub
		return choicequestiondao1.getAllChoiceQuestion();	
	
	}
	@Override	
	public List<ChoiceQuestion> getAllChoiceQuestionByPage(int pageIndex, int pageSize,
			ChoiceQuestion oi){
	
		return choicequestiondao1.getAllChoiceQuestionByPage(pageIndex, pageSize, oi);
		
		
	}
///////////////////////////////
	public int getTotalCount(ChoiceQuestion oi) {
		return choicequestiondao1.getTotalCount(oi);
		
		
	}
	
	

}
