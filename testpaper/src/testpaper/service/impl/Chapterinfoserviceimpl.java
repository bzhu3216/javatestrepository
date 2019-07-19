package testpaper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import testpaper.entity.Chapter;
import testpaper.service.Chapterinfoservice;
import testpaper.dao.*;
@Service

@Transactional
public class Chapterinfoserviceimpl implements Chapterinfoservice {
	@Autowired
	ChaperDAO chaperdao1;
	
	public Chapterinfoserviceimpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Chapter> getAllChaptercontent() {
		// TODO Auto-generated method stub
	return chaperdao1.getAllChaptercontent();
	
	
	}

}
