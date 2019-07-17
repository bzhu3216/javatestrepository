package testpaper.dao;


import java.util.List;



import testpaper.entity.Choice;

public interface ChoiceQuestionDAO {

	
	public List<Choice> getAllChoiceQuestion();
	public List<Choice> getAllChoiceQuestionByPage(int pageIndex, int pageSize,
			Choice oi);
	public int getTotalCount(Choice oi);
	
 public int addChoiceQuestion(Choice ac);
	//public int deleteChoiceQuestion(int id);
	//public int updateChoiceQuestion(ChoiceQuestion ac);
	//public ChoiceQuestion getChoiceQuestionById(int id);
	
	
}
