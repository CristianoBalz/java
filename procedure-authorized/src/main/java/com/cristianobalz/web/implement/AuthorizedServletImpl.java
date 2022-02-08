package com.cristianobalz.web.implement;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cristianobalz.business.AuthorizedBusiness;
import com.cristianobalz.business.BusinessObject;
import com.cristianobalz.business.exception.BusinessException;
import com.cristianobalz.dao.exception.EntityDaoException;
import com.cristianobalz.dto.AuthorizedDto;
import com.cristianobalz.dto.AuthorizedRet;
import com.cristianobalz.dto.exception.DtoException;
import com.cristianobalz.web.ServletInterface;

public class AuthorizedServletImpl implements ServletInterface<BusinessObject> {

	@Override
	public AuthorizedBusiness getBusiness() {
		return new AuthorizedBusiness();
	}

	@Override
	public void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ValidationException {
		AuthorizedDto dto = createAuthorizedDto(request);
		try {
			getBusiness().save(dto);
		} catch (BusinessException | DtoException | EntityDaoException e) {
			throw new ServletException(e.getMessage(), e);
		}
		response.sendRedirect("authorized");
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = getId(request, request.getParameterMap());
		try {
			getBusiness().removeById(id);
		} catch (BusinessException | EntityDaoException e) {
			throw new ServletException(e.getMessage(), e);
		}
		response.sendRedirect("authorized");
	}

	@Override
	public void newReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.showNewForm(request, response);
		request.setAttribute("authorized", null);
	}

	@Override
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = getId(request, request.getParameterMap());
		try {
			AuthorizedDto authorized = getBusiness().findById(id);
			request.setAttribute("authorized", authorized);
			showNewForm(request, response);
		} catch (BusinessException | EntityDaoException e) {
			throw new ServletException(e.getMessage(), e);
		}

	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ValidationException {
		AuthorizedDto authorizedDto = createAuthorizedDto(request);
		try {
			getBusiness().save(authorizedDto);
		} catch (BusinessException | DtoException | EntityDaoException e) {
			throw new ServletException(e.getMessage(), e);
		}
		response.sendRedirect("authorized");
	}

	@Override
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<AuthorizedDto> listAll = getBusiness().listAll();
			request.setAttribute("listAuthorized", listAll);
			RequestDispatcher dispacher = request.getRequestDispatcher("/authorized-list.jsp");
			dispacher.forward(request, response);
		} catch (BusinessException | EntityDaoException e) {
			throw new ServletException(e.getMessage(), e);
		}
	}

	@Override
	public void validateForm(HttpServletRequest request) throws ValidationException {
		AuthorizedDto authorized = createAuthorizedDto(request);
		StringBuilder sbMessage = new StringBuilder();
		if (authorized.getNumber() == null) {
			sbMessage.append("Field \"Number\" is not valid!");
		}

		if (authorized.getAge() == null) {
			sbMessage.append("Field \"Age\" is not valid!");
		}

		if (authorized.getSex() == null) {
			sbMessage.append("Field \"Sex\" is not valid! Valid values: M (Male), F (Women)");
		}

		if (authorized.getAuthorizedName() == null) {
			sbMessage.append("Field \"Authorized Name\" is not valid!");
		}

		if (!sbMessage.toString().isEmpty()) {
			throw new ValidationException(sbMessage.toString());
		}
	}

	@Override
	public void printMessageValidateForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("authorized",
				new AuthorizedRet(request.getParameter("number"), request.getParameter("age"),
						request.getParameter("sex"), request.getParameter("authorizedName"),
						request.getParameter("id")));
		showNewForm(request, response);
	}

	private AuthorizedDto createAuthorizedDto(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Integer id = getId(request, parameterMap);
		Integer age = ServletUtils.hasParameter(parameterMap, "age")
				? request.getParameter("age").replaceAll("[0-9]", "").isEmpty()
						? Integer.parseInt(request.getParameter("age"))
						: null
				: null;

		Integer number = ServletUtils.hasParameter(parameterMap, "number")
				? request.getParameter("number").replaceAll("[0-9]", "").isEmpty()
						? Integer.parseInt(request.getParameter("number"))
						: null
				: null;

		Character sex = ServletUtils.hasParameter(parameterMap, "sex")
				? request.getParameter("sex").trim().equals("M") || request.getParameter("sex").trim().equals("F")
						? Character.valueOf(request.getParameter("sex").charAt(0))
						: null
				: null;

		String authorizedName = ServletUtils.hasParameter(parameterMap, "authorizedName")
				? request.getParameter("authorizedName")
				: null;

		return new AuthorizedDto(number, new Date(), age, sex, authorizedName, id);
	}

	private Integer getId(HttpServletRequest request, Map<String, String[]> parameterMap) {
		Integer id = ServletUtils.hasParameter(parameterMap, "id") ? Integer.parseInt(request.getParameter("id"))
				: null;
		return id;
	}

	@Override
	public void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispacher = request.getRequestDispatcher("/authorized-form.jsp");
		dispacher.forward(request, response);
		
	}

}
