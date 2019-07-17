package testpaper.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import testpaper.entity.Chapter;
import testpaper.service.Chapterinfoservice;
@Service

@Transactional
public class Chapterinfoserviceimpl implements Chapterinfoservice {

	public Chapterinfoserviceimpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Chapter> getAllChaptercontent() {
		// TODO Auto-generated method stub
		return null;
	
	
	}

}
