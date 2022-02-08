package com.cristianobalz.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cristianobalz.connection.ConnectionFactory;
import com.cristianobalz.dao.exception.EntityDaoException;
import com.cristianobalz.entity.object.EntityObject;

public abstract class EntityDao<E extends EntityObject> {

	protected abstract Class<E> getEntityClass();
	
	public abstract List<E> listAll() throws EntityDaoException;

	public Optional<E> find(final Serializable id) throws EntityDaoException {
		EntityManager entityManager = getEntityManager();
		try {
			return Optional.ofNullable(entityManager.find(getEntityClass(), id));
		} catch (Exception e) {
			throw new EntityDaoException("Fail to find by id " + id + " in entity " + getEntityClass().getSimpleName(), e);
		} finally {
			entityManager.close();
		}
	}

	public void insert(final E entity) throws EntityDaoException {
		EntityManager entityManager = getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();		
		try {
			transaction.begin();
			entityManager.persist(entity);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new EntityDaoException("Fail to insert entity " + getEntityClass().getSimpleName(), e);
		} finally {
			entityManager.close();
		}
	}

	public Optional<E> update(final E entity) throws EntityDaoException {
		EntityManager entityManager = getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();	
		try {
			transaction.begin();
			E entityResult = entityManager.merge(entity);
			transaction.commit();
			return Optional.of(entityResult);
		} catch (Exception e) {
			transaction.rollback();
			throw new EntityDaoException("Fail to update entity " + getEntityClass().getSimpleName(), e);
		} finally {
			entityManager.close();
		}
	}

	public void delete(final E entity) throws EntityDaoException {
		EntityManager entityManager = getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();	
		try {
			transaction.begin();
			entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new EntityDaoException("Fail to remove entity " + getEntityClass().getSimpleName(), e);
		} finally {
			entityManager.close();
		}
	}	

	protected EntityManager getEntityManager() {
		return new ConnectionFactory().getEntityManager();
	}

}
