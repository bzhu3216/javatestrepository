package com.qh.test.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "t_paper")
public class Paper {

	private List<Question> questions = new ArrayList<Question>();
	private List<SubQuestion> subquestions = new ArrayList<SubQuestion>();
	private List<Statistics_Grade> statistics_Grades = new ArrayList<Statistics_Grade>();
	private int id;
	private String paperName;
	private Date joinDate;
	private int sScore;
	private int mScore;
	private int fScore;
	private int tScore;
	private int fullScore;

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "paperName", length = 255)
	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	@Column(name = "joinDate")
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@Column(name = "fullScore", length = 11)
	public int getFullScore() {
		return fullScore;
	}

	public void setFullScore(int fullScore) {
		this.fullScore = fullScore;
	}

	@OneToMany(mappedBy = "paper", fetch = FetchType.EAGER)
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@OneToMany(mappedBy = "paper", fetch = FetchType.EAGER)
	public List<SubQuestion> getSubquestions() {
		return subquestions;
	}

	public void setSubquestions(List<SubQuestion> subquestions) {
		this.subquestions = subquestions;
	}

	@OneToMany(mappedBy = "paper", fetch = FetchType.EAGER)
	public List<Statistics_Grade> getStatistics_Grades() {
		return statistics_Grades;
	}

	public void setStatistics_Grades(List<Statistics_Grade> statistics_Grades) {
		this.statistics_Grades = statistics_Grades;
	}

	@Transient
	public int getsScore() {
		return sScore;
	}

	public void setsScore(int sScore) {
		this.sScore = sScore;
	}

	@Transient
	public int getmScore() {
		return mScore;
	}

	public void setmScore(int mScore) {
		this.mScore = mScore;
	}

	@Transient
	public int getfScore() {
		return fScore;
	}

	public void setfScore(int fScore) {
		this.fScore = fScore;
	}

	@Transient
	public int gettScore() {
		return tScore;
	}

	public void settScore(int tScore) {
		this.tScore = tScore;
	}
}
