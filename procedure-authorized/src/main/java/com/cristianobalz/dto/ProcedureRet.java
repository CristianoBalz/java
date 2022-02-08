package com.cristianobalz.dto;

public class ProcedureRet {
	String age;
	String number;
	String sex;
	String permitted;
	String id;	
	
	
	public ProcedureRet(String age, String number, String sex, String permitted, String id) {
		this.age = age;
		this.number = number;
		this.sex = sex;
		this.permitted = permitted;
		this.id = id;
	}

	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getPermitted() {
		return permitted;
	}
	
	public void setPermitted(String permitted) {
		this.permitted = permitted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
