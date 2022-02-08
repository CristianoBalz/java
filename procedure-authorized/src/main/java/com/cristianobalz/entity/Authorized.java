package com.cristianobalz.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cristianobalz.dao.ProcedureDao;
import com.cristianobalz.dao.exception.EntityDaoException;
import com.cristianobalz.dto.AuthorizedDto;
import com.cristianobalz.entity.object.EntityObject;

@Entity
@Table(name = "authorized")
public class Authorized implements EntityObject {

	public Authorized() {
	}

	public Authorized(AuthorizedDto authorizedDto) throws EntityDaoException {
		if (authorizedDto != null) {
			this.age = authorizedDto.getAge();
			this.sex = authorizedDto.getSex();
			this.authorizedName = authorizedDto.getAuthorizedName();
			this.insertDate = authorizedDto.getInsertDate();
			this.id = authorizedDto.getId();
			this.procedure = new ProcedureDao()
					.findByNumberSexAge(authorizedDto.getNumber(), authorizedDto.getAge(), authorizedDto.getSex())
					.get();
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "procedure_id")
	private Procedure procedure;

	@Column(name = "insert_date")
	private Date insertDate;

	@Column(name = "authorized_name")
	private String authorizedName;

	private Integer age;

	private Character sex;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getAuthorizedName() {
		return authorizedName;
	}

	public void setAuthorizedName(String authorizedName) {
		this.authorizedName = authorizedName;
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
		return "Authorized [id=" + id + ", procedure=" + procedure + ", insertDate=" + insertDate + ", authorizedName="
				+ authorizedName + ", age=" + age + ", sex=" + sex + "]";
	}

}
