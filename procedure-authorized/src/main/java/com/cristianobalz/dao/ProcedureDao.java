package com.cristianobalz.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import com.cristianobalz.dto.ProcedureDto;
import com.cristianobalz.entity.Procedure;

public class ProcedureDao extends EntityDao<Procedure> {

	@Override
	protected Class<Procedure> getEntityClass() {
		return Procedure.class;
	}
		
	@Override
	public List<Procedure> listAll() {
		List<Procedure> resultList = getEntityManager().createQuery("from Procedure", getEntityClass()).getResultList();
		return resultList;
	}	
	
	public Optional<Procedure> findByNumberSexAge(Integer number, Integer age, Character sex) {
		Query query = getEntityManager().createQuery("select p from Procedure p where p.sex = :sex and p.number = :number and p.age = :age", getEntityClass());
		query.setParameter("sex", sex);
		query.setParameter("age", age);
		query.setParameter("number", number);
		List<Procedure> list = query.getResultList();		
		return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
	}
	
	public Optional<Procedure> findByNumberSexAge(ProcedureDto dto) {
		return this.findByNumberSexAge(dto.getNumber(), dto.getAge(), dto.getSex());
	}

}
