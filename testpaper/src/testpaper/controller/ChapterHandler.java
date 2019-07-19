package testpaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import testpaper.entity.Chapter;
import testpaper.service.Chapterinfoservice;

import java.util.List;
@RequestMapping("/ch")
@Controller
public class ChapterHandler {

	public ChapterHandler() {
		// TODO Auto-generated constructor stub
	}
///////////////////////////////////////
	@Autowired
	Chapterinfoservice Chapterinfoservice1;
	@RequestMapping("/getAllchapter")
	@ResponseBody	
	public List<Chapter> getAllchapter() {
		List<Chapter> acs = Chapterinfoservice1.getAllChaptercontent();
		
	
		return  acs;
	}
	
	
	
	
//////////////////////////////////////	
	
	
	
}
