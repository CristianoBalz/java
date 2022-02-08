package com.cristianobalz.business;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cristianobalz.business.exception.BusinessException;
import com.cristianobalz.business.exception.ProcedureAuthorizationNotFoundException;
import com.cristianobalz.dao.ProcedureDao;
import com.cristianobalz.dao.exception.EntityDaoException;
import com.cristianobalz.dto.ProcedureDto;
import com.cristianobalz.dto.exception.DtoException;
import com.cristianobalz.entity.Procedure;
import com.cristianobalz.web.implement.ValidationException;

public class ProcedureBusiness extends BusinessObject implements BusinessInterface<ProcedureDto> {

	private ProcedureDao procedureDao;

	public ProcedureBusiness() {
		this.procedureDao = new ProcedureDao();
	}

	@Override
	public void save(ProcedureDto dto) throws BusinessException, DtoException, EntityDaoException, ValidationException {
		Procedure procedure = dto.convertToEntity();

		Optional<Procedure> findByNumberSexAge = getProcedureDao().findByNumberSexAge(dto);
		if (findByNumberSexAge.isPresent()) {
			if (procedure.getId() == null || (procedure.getId() != null && findByNumberSexAge.get().getId() != procedure.getId())) {
				throw new ValidationException("A record already exists with number = " + dto.getNumber() + ", age = "
						+ dto.getAge() + ", sex = " + dto.getSex() + ".");
			}

		}

		if (procedure.getId() != null && getProcedureDao().find(procedure.getId()).isPresent()) {
			getProcedureDao().update(procedure);
		} else {
			getProcedureDao().insert(procedure);
		}
	}

	@Override
	public void removeById(Serializable id) throws BusinessException, EntityDaoException {
		Optional<Procedure> find = getProcedureDao().find(id);
		if (find.isPresent()) {
			getProcedureDao().delete(find.get());
		} else {
			throw new ProcedureAuthorizationNotFoundException("Not found procedure by id " + id);
		}
	}

	@Override
	public ProcedureDto findById(Serializable id) throws BusinessException, EntityDaoException {
		Optional<Procedure> find = getProcedureDao().find(id);
		Procedure procedure = find
				.orElseThrow(() -> new ProcedureAuthorizationNotFoundException("Not found procedure by id " + id));
		return new ProcedureDto(procedure);
	}

	@Override
	public List<ProcedureDto> listAll() throws BusinessException, EntityDaoException {
		List<Procedure> listAll = getProcedureDao().listAll();
		return listAll.stream().map(ProcedureDto::new).collect(Collectors.toList());
	}

	private ProcedureDao getProcedureDao() {
		return procedureDao;
	}

}
