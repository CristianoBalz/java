package com.cristianobalz.web.implement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cristianobalz.business.BusinessObject;
import com.cristianobalz.business.ProcedureBusiness;
import com.cristianobalz.business.exception.BusinessException;
import com.cristianobalz.dao.exception.EntityDaoException;
import com.cristianobalz.dto.ProcedureDto;
import com.cristianobalz.dto.ProcedureRet;
import com.cristianobalz.dto.exception.DtoException;
import com.cristianobalz.web.ServletInterface;

public class ProcedureServletImpl implements ServletInterface<BusinessObject> {

	@Override
	public ProcedureBusiness getBusiness() {
		return new ProcedureBusiness();
	}

	@Override
	public void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ValidationException {
		ProcedureDto dto = createProcedureDto(request);
		validateForm(request);
		try {
			getBusiness().save(dto);
		} catch (BusinessException | DtoException | EntityDaoException e) {
			throw new ServletException(e.getMessage(), e);
		}
		response.sendRedirect("procedure");
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = getId(request, request.getParameterMap());
		try {
			getBusiness().removeById(id);
		} catch (BusinessException | EntityDaoException e) {
			throw new ServletException(e.getMessage(), e);
		}
		response.sendRedirect("procedure");
	}

	@Override
	public void newReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.showNewForm(request, response);
		request.setAttribute("procedure", null);
	}

	@Override
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = getId(request, request.getParameterMap());
		try {
			ProcedureDto procedure = getBusiness().findById(id);
			request.setAttribute("procedure", procedure);
			showNewForm(request, response);
		} catch (BusinessException | EntityDaoException e) {
			throw new ServletException(e.getMessage(), e);
		}

	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ValidationException {
		ProcedureDto procedureDto = createProcedureDto(request);
		validateForm(request);
		try {
			getBusiness().save(procedureDto);
		} catch (BusinessException | DtoException | EntityDaoException e) {
			throw new ServletException(e.getMessage(), e);
		}
		response.sendRedirect("procedure");
	}

	@Override
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<ProcedureDto> listAll = getBusiness().listAll();
			request.setAttribute("listProcedure", listAll);
			RequestDispatcher dispacher = request.getRequestDispatcher("/procedure-list.jsp");
			dispacher.forward(request, response);
		} catch (BusinessException | EntityDaoException e) {
			throw new ServletException(e.getMessage(), e);
		}
	}

	@Override
	public void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispacher = request.getRequestDispatcher("/procedure-form.jsp");
		dispacher.forward(request, response);
	}

	@Override
	public void validateForm(HttpServletRequest request) throws ValidationException {
		ProcedureDto procedure = createProcedureDto(request);
		StringBuilder sbMessage = new StringBuilder();
		if (procedure.getNumber() == null) {
			sbMessage.append("Field \"Number\" is not valid!\n");
		}

		if (procedure.getAge() == null) {
			sbMessage.append("Field \"Age\" is not valid!\n");
		}

		if (procedure.getSex() == null) {
			sbMessage.append(
					"\nField \"Sex\" is not valid! Valid values: M (Male), F (Women)");
		}

		if (procedure.getPermitted() == null) {
			sbMessage.append(
					"\nField \"Permitted\" is not valid! Valid values: true, false");
		}

		request.setAttribute("procedure", procedure);
		if (!sbMessage.toString().isEmpty()) {
			throw new ValidationException(sbMessage.toString());
		}
	}

	@Override
	public void printMessageValidateForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		request.setAttribute("procedure", new ProcedureRet(request.getParameter("age"), 
				request.getParameter("number"), request.getParameter("sex"), 
				request.getParameter("permitted"), 
				request.getParameter("id")));
		showNewForm(request, response);
	}

	private ProcedureDto createProcedureDto(HttpServletRequest request) {
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
		Boolean permitted = ServletUtils.hasParameter(parameterMap, "permitted")
				? !request.getParameter("permitted").isEmpty()
						&& (request.getParameter("permitted").equalsIgnoreCase("true")
								|| request.getParameter("permitted").equalsIgnoreCase("false"))
										? Boolean.parseBoolean(request.getParameter("permitted"))
										: null
				: null;
		return new ProcedureDto(id, age, sex, permitted, number);
	}

	private Integer getId(HttpServletRequest request, Map<String, String[]> parameterMap) {
		Integer id = ServletUtils.hasParameter(parameterMap, "id") ? Integer.parseInt(request.getParameter("id"))
				: null;
		return id;
	}
	
	

}
