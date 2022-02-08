package com.cristianobalz.business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cristianobalz.business.exception.BusinessException;
import com.cristianobalz.business.exception.ProcedureAuthorizationNotFoundException;
import com.cristianobalz.dao.AuthorizedDao;
import com.cristianobalz.dao.ProcedureDao;
import com.cristianobalz.dao.exception.EntityDaoException;
import com.cristianobalz.dto.AuthorizedDto;
import com.cristianobalz.dto.exception.DtoException;
import com.cristianobalz.entity.Authorized;
import com.cristianobalz.web.implement.ValidationException;

public class AuthorizedBusiness extends BusinessObject implements BusinessInterface<AuthorizedDto> {

	private AuthorizedDao authorizedDao;
	private ProcedureDao procedureDao;

	public AuthorizedBusiness() {
		this.authorizedDao = new AuthorizedDao();
		this.procedureDao = new ProcedureDao();
	}

	@Override
	public void save(AuthorizedDto dto) throws BusinessException, DtoException, EntityDaoException, ValidationException {
		Authorized authorized = dto.convertToEntity();
		authorized.setInsertDate(new Date());
		if (authorized.getId() != null) {
			getAuthorizedDao().update(authorized);
		} else {
			if (!getProcedureDao().findByNumberSexAge(dto.getNumber(), dto.getAge(), dto.getSex()).isPresent()) {
				throw new ValidationException("Procedure not exist with number = " + dto.getNumber() + ", age = " + dto.getAge()
						+ ", sex = " + dto.getSex() + ".");
			}
		
			getAuthorizedDao().insert(authorized);			
		}
	}

	@Override
	public void removeById(Serializable id) throws BusinessException, EntityDaoException {
		Optional<Authorized> find = getAuthorizedDao().find(id);
		if (find.isPresent()) {
			getAuthorizedDao().delete(find.get());
		} else {
			throw new ProcedureAuthorizationNotFoundException("Not found authorized by id " + id);
		}
	}

	@Override
	public AuthorizedDto findById(Serializable id) throws BusinessException, EntityDaoException {
		Optional<Authorized> find = getAuthorizedDao().find(id);
		Authorized authorized = find.orElseThrow(() -> new ProcedureAuthorizationNotFoundException("Not found authorized by id " + id));	
		return new AuthorizedDto(authorized);
	}

	@Override
	public List<AuthorizedDto> listAll() throws BusinessException, EntityDaoException {
		List<Authorized> listAll = getAuthorizedDao().listAll();
		return listAll.stream().map(AuthorizedDto::new).collect(Collectors.toList());
	}

	private AuthorizedDao getAuthorizedDao() {
		return authorizedDao;
	}

	public ProcedureDao getProcedureDao() {
		return procedureDao;
	}	
	

}
