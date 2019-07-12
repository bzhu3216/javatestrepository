package com.qh.test.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_exam")
public class Test {

	int id;
	int singleScore; // 单选题得分

	int moreScore; // 多选题得分
	int score; // 客观题得分

	int fScore; // 填空题得分
	int sScore; // 简答题得分

	int tScore; // 主观题总分
	int totalScore; // 总分

	String keyMap;

	User user;
	Paper paper;

	Date testDate;

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "singleScore", length = 11)
	public int getSingleScore() {
		return singleScore;
	}

	public void setSingleScore(int singleScore) {
		this.singleScore = singleScore;
	}

	@Column(name = "moreScore", length = 11)
	public int getMoreScore() {
		return moreScore;
	}

	public void setMoreScore(int moreScore) {
		this.moreScore = moreScore;
	}

	@Column(name = "score", length = 11)
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@ManyToOne
	@JoinColumn(name = "studentId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "paperId")
	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	@Column(name = "examDate")
	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	@Column(name = "keyMap", length = 255)
	public String getKeyMap() {
		return keyMap;
	}

	public void setKeyMap(String keyMap) {
		this.keyMap = keyMap;
	}

	@Column(name = "fScore", length = 11)
	public int getfScore() {
		return fScore;
	}

	public void setfScore(int fScore) {
		this.fScore = fScore;
	}

	@Column(name = "SScore", length = 11)
	public int getsScore() {
		return sScore;
	}

	public void setsScore(int sScore) {
		this.sScore = sScore;
	}

	@Column(name = "TScore", length = 11)
	public int gettScore() {
		return tScore;
	}

	public void settScore(int tScore) {
		this.tScore = tScore;
	}

	@Column(name = "totalScore", length = 11)
	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

}
