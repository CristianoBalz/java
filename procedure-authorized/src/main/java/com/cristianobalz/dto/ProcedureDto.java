package com.cristianobalz.dto;

import com.cristianobalz.dto.exception.DtoException;
import com.cristianobalz.entity.Procedure;
import com.cristianobalz.web.implement.ValidationException;

public class ProcedureDto extends DtoObject<Procedure> {

	private Integer id;
	private Integer number;
	private Integer age;
	private Character sex;
	private Boolean permitted;

	public ProcedureDto(Integer id, Integer age, Character sex, Boolean permitted, Integer number) {
		this.id = id;
		this.age = age;
		this.sex = sex;
		this.permitted = permitted;
		this.number = number;
	}
	
	public ProcedureDto(Procedure procedure) {
		this.permitted = procedure.getPermitted();
		this.age = procedure.getAge();
		this.sex = procedure.getSex();
		this.id = procedure.getId();
		this.number = procedure.getNumber();
	}

	public Integer getId() {
		return id;
	}

	public Integer getAge() {
		return age;
	}

	public Character getSex() {
		return sex;
	}

	public Boolean getPermitted() {
		return permitted;
	}	

	public Integer getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "ProcedureDto [id=" + id + " number=" + number + ", age=" + age + ", sex=" + sex + ", permitted=" + permitted + "]";
	}

	@Override
	public Procedure convertToEntity() throws DtoException, ValidationException {
		return new Procedure(this);
	}

}
