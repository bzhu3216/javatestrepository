package com.qh.test.service.impl;

import java.util.List;

import com.qh.test.dao.impl.PaperDaoImpl;
import com.qh.test.entity.Paper;
import com.qh.test.service.PaperService;

public class PaperServiceImpl implements PaperService {

	PaperDaoImpl paperDao = new PaperDaoImpl();

	public List<Paper> getAllPapers() throws Exception {
		return paperDao.getAllPapers();
	}

	public Paper getPaper(String paperId) throws Exception {
		return paperDao.getPaper(paperId);
	}

	public void savePaper(Paper paper) throws Exception {
		paperDao.savePaper(paper);
	}

	public Paper getPaperByName(String paperName) throws Exception {
		return paperDao.getPaperByName(paperName);
	}

	public void deletePaper(Paper paper) throws Exception {
		paperDao.deletePaper(paper);
	}

	public void addPaper(Paper paper) throws Exception {
		paperDao.addPaper(paper);
	}

}
