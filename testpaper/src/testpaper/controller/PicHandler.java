package testpaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import testpaper.entity.Pic;
import testpaper.service.PicService;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import java.io.*;

@RequestMapping("/pc")
@Controller
public class PicHandler {
	PicService picService;
	//@Autowired	
	@RequestMapping("/savePic")
	
	@ResponseBody
	
	public String Pic(@RequestParam("pic") CommonsMultipartFile pic,HttpServletRequest req){
				
		//int dOK = picService.addPic(ac);	
		
		 long  startTime=System.currentTimeMillis();
	        
		 try
		 {
		 System.out.println("fileName："+pic.getOriginalFilename());
		    //上传文件uploadtemp
		   String path=req.getServletContext().getContextPath();
	       String realPath=req.getServletContext().getRealPath("/uploadtemp");	    
	       // String path="E:/"+new Date().getTime()+pic.getOriginalFilename();	         
	        File newFile=new File(realPath+"/"+pic.getOriginalFilename());
	        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
	        pic.transferTo(newFile);
	        System.out.println(realPath);  	
	        long  endTime=System.currentTimeMillis();
	        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
	        //upload to database
	        
	        
	        
	        
	        
	       
		 }
		 catch(Exception e)
		  
		 {
			 e.printStackTrace();
		 }
		 
		 return "/success"; 
		/*
		int dOK=0;
		System.out.println(dOK);
		if(dOK > 0){
			return "s";
		}
		 return "f";
		 
		*/ 
		 
		 
	}

	
	
	
}
