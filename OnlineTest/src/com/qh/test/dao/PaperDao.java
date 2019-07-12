package com.qh.test.dao;

import java.util.List;

import com.qh.test.entity.Paper;

public interface PaperDao {
	public List<Paper> getAllPapers() throws Exception;
	public Paper getPaper(String paperId) throws Exception;
	public void savePaper(Paper paper) throws Exception;
	public void addPaper(Paper paper) throws Exception;
	public Paper getPaperByName(String paperName) throws Exception;
	public void deletePaper(Paper paper) throws Exception;
}
