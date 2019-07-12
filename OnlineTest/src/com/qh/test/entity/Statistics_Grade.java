package com.qh.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "statistics_grade")
public class Statistics_Grade {
	private int id;
	private int totalCount; // 总考试次数
	private int passCount; // 及格数
	private int excellentCount; // 优秀数
	private String passPercent; // 及格率
	private String excellentPercent; // 优秀率
	private Double averageScore; // 平均分
	private int maxScore; // 最高分
	private int minScore; // 最低分
	private Paper paper;

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "totalCount", length = 11)
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Column(name = "passCount", length = 11)
	public int getPassCount() {
		return passCount;
	}

	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}

	@Column(name = "excellentCount", length = 11)
	public int getExcellentCount() {
		return excellentCount;
	}

	public void setExcellentCount(int excellentCount) {
		this.excellentCount = excellentCount;
	}

	@Column(name = "passPercent", length = 40)
	public String getPassPercent() {
		return passPercent;
	}

	public void setPassPercent(String passPercent) {
		this.passPercent = passPercent;
	}

	@Column(name = "excellentPercent", length = 40)
	public String getExcellentPercent() {
		return excellentPercent;
	}

	public void setExcellentPercent(String excellentPercent) {
		this.excellentPercent = excellentPercent;
	}

	@Column(name = "averageScore", columnDefinition = "double(16,2)")
	public Double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(Double averageScore) {
		this.averageScore = averageScore;
	}

	@Column(name = "maxScore", length = 20)
	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	@Column(name = "minScore", length = 20)
	public int getMinScore() {
		return minScore;
	}

	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}

	@ManyToOne
	@Cascade(value = { CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "paperId")
	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	
}
