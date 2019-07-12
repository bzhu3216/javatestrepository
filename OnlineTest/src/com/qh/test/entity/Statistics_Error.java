package com.qh.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "statistics_error")
public class Statistics_Error {
	private int id;
	private int totalCount; // 总做题数
	private int difficultCount;
	private int middleCount;
	private int easyCount;
	private int diffiErrorCount;
	private int middErroCount;
	private int easyErroCount;
	private String difficultPercent;
	private String middlePercent;
	private String easyPercent;

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

	@Column(name = "difficultCount", length = 11)
	public int getDifficultCount() {
		return difficultCount;
	}

	public void setDifficultCount(int difficultCount) {
		this.difficultCount = difficultCount;
	}

	@Column(name = "middleCount", length = 11)
	public int getMiddleCount() {
		return middleCount;
	}

	public void setMiddleCount(int middleCount) {
		this.middleCount = middleCount;
	}

	@Column(name = "easyCount", length = 11)
	public int getEasyCount() {
		return easyCount;
	}

	public void setEasyCount(int easyCount) {
		this.easyCount = easyCount;
	}

	@Column(name = "difficultPercent", length = 40)
	public String getDifficultPercent() {
		return difficultPercent;
	}

	public void setDifficultPercent(String difficultPercent) {
		this.difficultPercent = difficultPercent;
	}

	@Column(name = "middlePercent", length = 40)
	public String getMiddlePercent() {
		return middlePercent;
	}

	public void setMiddlePercent(String middlePercent) {
		this.middlePercent = middlePercent;
	}

	@Column(name = "easyPercent", length = 40)
	public String getEasyPercent() {
		return easyPercent;
	}

	public void setEasyPercent(String easyPercent) {
		this.easyPercent = easyPercent;
	}

	@Column(name = "diffErrorCount", length = 11)
	public int getDiffiErrorCount() {
		return diffiErrorCount;
	}

	public void setDiffiErrorCount(int diffiErrorCount) {
		this.diffiErrorCount = diffiErrorCount;
	}

	@Column(name = "midErrorCount", length = 11)
	public int getMiddErroCount() {
		return middErroCount;
	}

	public void setMiddErroCount(int middErroCount) {
		this.middErroCount = middErroCount;
	}

	@Column(name = "easyErrorCount", length = 11)
	public int getEasyErroCount() {
		return easyErroCount;
	}

	public void setEasyErroCount(int easyErroCount) {
		this.easyErroCount = easyErroCount;
	}

}
