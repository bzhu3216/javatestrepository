package testpaper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "choice", catalog = "testpaper")
public class ChoiceQuestion {
	
	private int id;
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private int comeout;
	private int picid;
	private int chapter;
	private int key;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getID()
	{
		return id;
	}
	
	public void setID(int id)
	{
		this.id=id;
	}
	
	@Column(name = "question")
	public String getQuestion()
	{
		return question;
	}
	
	public void setQuestion(String question)
	{
		this.question=question;
	}	
	
	@Column(name = "answer1")
	public String getAnswer1()
	{
		return answer1;
	}
	
	public void setAnswer1(String answer1)
	{
		this.answer1=answer1;
	}	

	@Column(name = "answer2")
	public String getAnswer2()
	{
		return answer2;
	}
	
	public void setAnswer2(String answer2)
	{
		this.answer2=answer2;
	}	
	
	@Column(name = "answer3")
	public String getAnswer3()
	{
		return answer3;
	}
	
	public void setAnswer3(String answer3)
	{
		this.answer3=answer3;
	}	
	@Column(name = "answer4")
	public String getAnswer4()
	{
		return answer4;
	}
	
	public void setAnswer4(String answer4)
	{
		this.answer4=answer4;
	}	
	
	
	
	@Column(name = "comeout")
	public int getComeout()
	{
		return comeout;
	}
	
	public void setComeout(int comeout)
	{
		this.comeout=comeout;
	}	
	
	
	@Column(name = "picid")
	public int getPicid()
	{
		return picid;
	}
	
	public void setPicid(int picid)
	{
		this.picid=picid;
	}	
	
	@Column(name = "chapter")
	public int getChapter()
	{
		return chapter;
	}
	
	public void setChapter(int chapter)
	{
		this.chapter=chapter;
	}	
	
	@Column(name = "key")
	public int getKey()
	{
		return key;
	}
	
	public void setKey(int key)
	{
		this.key=key;
	}	
	/** 无参构造方法 */
	public ChoiceQuestion() {
	}
	
	
	

}
