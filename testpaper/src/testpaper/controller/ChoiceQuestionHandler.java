package testpaper.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	@RequestMapping("/getAllPagechoicequestion")
	@ResponseBody	
	public Map<String, Object> getAllChoiceQuestionByPage(int page, int rows,
			ChoiceQuestion oi){
					
		//return choiceservice.getAllChoiceQuestionByPage(page,rows,oi);
		
		Map<String, Object> result = new HashMap<String, Object>(2);
	
		// 根据查询条件获取订单记录总数
		int totalCount = choiceservice.getTotalCount(oi);
		// 根据当前页码、每页显示记录数和查询条件获取指定页显示的订单列表
		List<ChoiceQuestion> oiList = choiceservice.getAllChoiceQuestionByPage(page,
				rows, oi);
		// 向对象result中放入键值对，键为“total”,值为totalCount,
		result.put("total", totalCount);
		// 向对象result中放入键值对，键为“rows”,值为oiList,
		result.put("rows", oiList);
		// 通过@ResponseBody注解自动将Map<String, Object>类型result转换为JSON格式,并向前端页面发送
		return result;
	
	
	
	
	
	}
	
	
	////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
}
