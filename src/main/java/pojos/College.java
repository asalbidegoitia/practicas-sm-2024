package pojos;

import java.util.Date;

public class College {
	private Integer uid;
	private String name;
	private String webpage;
	private String country;
	private String state;
	private Date date;

	public College(Integer uid, String name, String webpage, String country, String state, Date date) {
		super();
		this.uid = uid;
		this.name = name;
		this.webpage = webpage;
		this.country = country;
		this.state = state;
		this.date = date;
	}

	public College(String name, String webpage, String country, String state) {
		super();
		this.name = name;
		this.webpage = webpage;
		this.country = country;
		this.state = state;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebpage() {
		return webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "College [uid=" + uid + ", name=" + name + ", webpage=" + webpage + ", country=" + country + ", state="
				+ state + ", date=" + date + "]";
	}

}
