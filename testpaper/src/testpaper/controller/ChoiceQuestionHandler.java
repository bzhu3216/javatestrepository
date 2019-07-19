package testpaper.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import testpaper.entity.Choice;
import testpaper.entity.Picdata;
import testpaper.service.ChoiceService;
import testpaper.service.PicService;;
@RequestMapping("/cq")
@Controller
public class ChoiceQuestionHandler {
	
	
	@Autowired
	ChoiceService choiceservice;
	@Autowired
	PicService picService;
	@RequestMapping("/getAllchoicequestion")
	@ResponseBody	
	public List<Choice> getAbilitycontent() {
		
		List<Choice> acs = choiceservice.getAllChoiceQuestion();
	
		return  acs;
	}
	
	/////////////////////////////////////////////////////
	@RequestMapping("/getAllPagechoicequestion")
	@ResponseBody	
	public Map<String, Object> getAllChoiceQuestionByPage(int page, int rows,
			Choice oi){
					
		//return choiceservice.getAllChoiceQuestionByPage(page,rows,oi);
		
		Map<String, Object> result = new HashMap<String, Object>(2);
	
		// 根据查询条件获取订单记录总数
		int totalCount = choiceservice.getTotalCount(oi);
		// 根据当前页码、每页显示记录数和查询条件获取指定页显示的订单列表
		List<Choice> oiList = choiceservice.getAllChoiceQuestionByPage(page,
				rows, oi);
		// 向对象result中放入键值对，键为“total”,值为totalCount,
		result.put("total", totalCount);
		// 向对象result中放入键值对，键为“rows”,值为oiList,
		result.put("rows", oiList);
		// 通过@ResponseBody注解自动将Map<String, Object>类型result转换为JSON格式,并向前端页面发送
		return result;
	
	}
	
	
	////////////////////////////////////////////////////

	@RequestMapping("/savechoiceInfo")
	@ResponseBody
	@Transactional(timeout=10)
	public String addchoicequestion(Choice cq,@RequestParam("pic") CommonsMultipartFile pic)
	//public String addchoicequestion(@RequestParam("pic") CommonsMultipartFile pic)
	{
	
	System.out.println(cq.getQuestion());
	System.out.println(cq.getId());
	int dOK = choiceservice.addChoiceQuestion(cq);
	////////////////////////////////////////////////////////
	
	//System.out.println(cq.getQuestion());
	//System.out.println(cq.getChapter());
	
	
	////////////////////////////////////////////////////////////
	
	if(pic!=null) {
	 Picdata pb=new Picdata();
	 int dOK2=0;
	 try
	 {
	
		 InputStream in =  pic.getInputStream(); 
		byte[] b = new byte[in.available()];
        //System.out.println("方法二的运行时间："+b.length);
        in.read(b);
        in.close();
        pb.setPic(b);
        pb.setType(1);
        pb.setQuestionid(cq.getId());
        dOK2 = picService.addPic(pb);
       
       
	 }
	 catch(Exception e)
	  
	 {   
		 e.printStackTrace();
		
	 }
	
		
	
	
	///////////////////////////////////////////////////////////
	
	if(dOK > 0 && dOK2>0)
		return "{\"success\":\"true\",\"message\":\"ok\"}";
	
	else
		return "{\"success\":\"false\",\"message\":\"fail\"}";
	
	}
	
	else 
	{
		if(dOK > 0 )
			return "{\"success\":\"true\",\"message\":\"ok\"}";
		
		else
			return "{\"success\":\"false\",\"message\":\"fail\"}";	
		
		
	}
	
	
	
	
	
	}

	
	
	
	//////////////////////////////////////////
	
	
	
	
	
	
	
	
	
}
