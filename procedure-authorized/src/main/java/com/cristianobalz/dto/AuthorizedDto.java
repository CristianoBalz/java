package com.cristianobalz.dto;

import java.util.Date;

import com.cristianobalz.dao.ProcedureDao;
import com.cristianobalz.dao.exception.EntityDaoException;
import com.cristianobalz.dto.exception.DtoException;
import com.cristianobalz.entity.Authorized;
import com.cristianobalz.web.implement.ValidationException;

public class AuthorizedDto extends DtoObject<Authorized> {

	private Integer number;
	private Date insertDate;
	private Integer age;
	private Character sex;
	private String authorizedName;
	private Integer id;

	public AuthorizedDto(Integer number, Date insertDate, Integer age, Character sex, String authorizedName,
			Integer id) {
		this.number = number;
		this.insertDate = insertDate;
		this.age = age;
		this.sex = sex;
		this.authorizedName = authorizedName;
		this.id = id;
	}

	public AuthorizedDto(Authorized authorized) {
		this.number = authorized.getProcedure().getNumber();
		this.insertDate = authorized.getInsertDate();
		this.age = authorized.getAge();
		this.sex = authorized.getSex();
		this.authorizedName = authorized.getAuthorizedName();
		this.id = authorized.getId();
	}

	public Integer getNumber() {
		return number;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public Integer getAge() {
		return age;
	}

	public Character getSex() {
		return sex;
	}

	public String getAuthorizedName() {
		return authorizedName;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public Authorized convertToEntity() throws DtoException, ValidationException {
		new ProcedureDao().findByNumberSexAge(this.getNumber(), this.getAge(), this.getSex())
				.orElseThrow(() -> new ValidationException("Not found procedure with number = " + this.number+", age = "+this.age+", sex = "+this.sex));
		try {			
			return new Authorized(this);
		} catch(EntityDaoException e) {
			throw new DtoException(e.getMessage(), e);
		}
	}

	@Override
	public String toString() {
		return "AuthorizedDto [number=" + number + ", insertDate=" + insertDate + ", age=" + age + ", sex="
				+ sex + ", authorizedName=" + authorizedName + ", id=" + id + "]";
	}
}
