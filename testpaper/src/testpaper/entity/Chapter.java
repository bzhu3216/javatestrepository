package testpaper.entity;
// Generated 2019-7-17 15:23:24 by Hibernate Tools 3.2.2.GA

/**
 * Chapter generated by hbm2java
 */
public class Chapter implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5745571695778417423L;
	private Integer id;
	private String chapterstr;

	public Chapter() {
	}

	public Chapter(String chapterstr) {
		this.chapterstr = chapterstr;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChapterstr() {
		return this.chapterstr;
	}

	public void setChapterstr(String chapterstr) {
		this.chapterstr = chapterstr;
	}

}