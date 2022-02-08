package com.cristianobalz.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cristianobalz.dto.ProcedureDto;
import com.cristianobalz.entity.object.EntityObject;

@Entity
@Table(name = "procedure")
public class Procedure implements EntityObject {

	public Procedure() {
	}

	public Procedure(ProcedureDto procedureDto) {
		if (procedureDto != null) {
			this.id = procedureDto.getId();
			this.number = procedureDto.getNumber();
			this.sex = procedureDto.getSex();
			this.age = procedureDto.getAge();
			this.permitted = procedureDto.getPermitted();
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer number;	
	
	private Integer age;
	
	private Character sex;

	private Boolean permitted;	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public Boolean getPermitted() {
		return permitted;
	}

	public void setPermitted(Boolean permitted) {
		this.permitted = permitted;
	}	

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Procedure [id=" + id + ", number=" + number + ", age=" + age + ", sex=" + sex + ", permitted=" + permitted + "]";
	}

}
