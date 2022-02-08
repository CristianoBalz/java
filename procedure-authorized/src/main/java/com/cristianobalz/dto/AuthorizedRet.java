package com.cristianobalz.dto;

public class AuthorizedRet {
	private String number;
	private String age;
	private String sex;
	private String authorizedName;
	private String id;
	
	

	public AuthorizedRet(String number, String age, String sex, String authorizedName, String id) {
		this.number = number;
		this.age = age;
		this.sex = sex;
		this.authorizedName = authorizedName;
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAuthorizedName() {
		return authorizedName;
	}

	public void setAuthorizedName(String authorizedName) {
		this.authorizedName = authorizedName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
