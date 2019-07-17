package testpaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import testpaper.entity.Picdata;
import testpaper.service.PicService;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

@RequestMapping("/pc")
@Controller
public class PicHandler {
	
	@Autowired	
	PicService picService;
	@RequestMapping("/savePic")
	
	@ResponseBody
	
	public String Pic(@RequestParam("pic") CommonsMultipartFile pic,HttpServletRequest req){
					
		// long  startTime=System.currentTimeMillis();
		 Picdata pb=new Picdata();
		 	        
		 try
		 {
		// System.out.println("fileName："+pic.getOriginalFilename());
		    //上传文件uploadtemp
		  // String path=req.getServletContext().getContextPath();
	     //  String realPath=req.getServletContext().getRealPath("/uploadtemp");	    
	       // String path="E:/"+new Date().getTime()+pic.getOriginalFilename();	         
	       // File newFile=new File(realPath+"/"+pic.getOriginalFilename());
	        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
	      //  pic.transferTo(newFile);	       
	       // System.out.println(realPath);  	
	       // long  endTime=System.currentTimeMillis();
	        //System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
	        //upload to database
	         
          //  in = new FileInputStream(realPath+"/"+pic.getOriginalFilename());
           
			 InputStream in =  pic.getInputStream(); 
			byte[] b = new byte[in.available()];
            //System.out.println("方法二的运行时间："+b.length);
            in.read(b);
            in.close();
            pb.setPic(b);
           // pb.setId(1);
            int dOK=0;
            dOK = picService.addPic(pb);
            
            
            
            
            
            
         	if(dOK > 0){
    			return "s";
    		}
    		 return "f"; 
	        
	       
		 }
		 catch(Exception e)
		  
		 {   
			 e.printStackTrace();return "f";
			
		 }
		
		
		 
	}

	///////////////////////////////
	@RequestMapping("/getPic")
	@ResponseBody
	public  String getPic(HttpServletResponse response)
	{
		
		 Picdata pd=picService.getPicById(1);
		 
		 byte[] data=pd.getPic();
	    	response.setContentType("img/jpeg");
	    	response.setCharacterEncoding("utf-8");
	    	try {
	    		
				OutputStream outputStream=response.getOutputStream();
				InputStream in=new ByteArrayInputStream(data);				
				int len=0;
				byte[]buf=new byte[1024];
				while((len=in.read(buf,0,1024))!=-1){
					outputStream.write(buf, 0, len);
				}
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	
	return null;
	
	}
	/////////////////////////////////
	
	
	
}
