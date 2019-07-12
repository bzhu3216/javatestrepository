package testpaper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.*;



@Entity

@Table(name = "picdata", catalog = "testpaper")
public class Pic {

	private int id;	
  private Blob pic;
	private Integer type;
	private Integer questionid;
	
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
	@Column(name = "pic")	

	public Blob getPic() 
	{ 
	return pic; 
	} 

	public void setPic(Blob picFile) { 
	this.pic = picFile; 
	}
	
	
	////////////////////
	@Column(name = "type")	
	public Integer getType()
	{
		return type;
	}
	
	public void setType(Integer type)
	{
		this.type=type;
	}
	@Column(name = "questionid")	
	public Integer getQuestionid()
	{
		return questionid;
	}
	
	public void setQuestionid(Integer questionid)
	{
		this.questionid=questionid;
	}
	
	
	////////////////////
	/** 无参构造方法 */
	public Pic() {
	}

	
	@Override
	public String toString() {
		return "UserInfo [id=" + id ;
	}	
	
	
	
	
	
	
	
}
