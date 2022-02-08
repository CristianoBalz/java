package com.cristianobalz.dto;

import com.cristianobalz.dto.exception.DtoException;
import com.cristianobalz.entity.object.EntityObject;
import com.cristianobalz.web.implement.ValidationException;

public abstract class DtoObject<E extends EntityObject> {

	abstract E convertToEntity() throws DtoException, ValidationException;

}
