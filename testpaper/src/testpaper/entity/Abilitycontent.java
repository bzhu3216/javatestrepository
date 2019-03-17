package testpaper.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity

@Table(name = "abilitycontent", catalog = "testpaper")
public class Abilitycontent {
	
	private int id;
	private String abilitystr;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idability", unique = true, nullable = false)
	public int getID()
	{
		return id;
	}
	
	public void setID(int id)
	{
		this.id=id;
	}
	@Column(name = "abilitystr", length = 1000)
	public String getAbilitystr()
	{
		return abilitystr;
	}
	
	public void setAbilitystr(String abilitystr)
	{
		this.abilitystr=abilitystr;
	}
	
	/** 无参构造方法 */
	public Abilitycontent() {
	}

	
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", abilitystr=" + abilitystr;
	}	
	
	
	
	

}
