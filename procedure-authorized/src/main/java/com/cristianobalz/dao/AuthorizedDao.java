package com.cristianobalz.dao;

import java.util.List;

import com.cristianobalz.dao.exception.EntityDaoException;
import com.cristianobalz.entity.Authorized;

public class AuthorizedDao extends EntityDao<Authorized> {

	@Override
	public Class<Authorized> getEntityClass() {
		return Authorized.class;
	}

	@Override
	public List<Authorized> listAll() throws EntityDaoException {
		List<Authorized> resultList = getEntityManager().createQuery("from Authorized", getEntityClass()).getResultList();
		return resultList;
	}

}
