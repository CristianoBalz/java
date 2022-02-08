package com.cristianobalz.business;

import java.io.Serializable;
import java.util.List;

import com.cristianobalz.business.exception.BusinessException;
import com.cristianobalz.dao.exception.EntityDaoException;
import com.cristianobalz.dto.DtoObject;
import com.cristianobalz.dto.exception.DtoException;
import com.cristianobalz.web.implement.ValidationException;

public interface BusinessInterface<D extends DtoObject<?>> {

	void save(D dto) throws BusinessException, DtoException, EntityDaoException, ValidationException;
	
	void removeById(Serializable id) throws BusinessException, EntityDaoException;
	
	D findById(Serializable id) throws BusinessException, EntityDaoException;
	
	List<D> listAll() throws BusinessException, EntityDaoException;
}
