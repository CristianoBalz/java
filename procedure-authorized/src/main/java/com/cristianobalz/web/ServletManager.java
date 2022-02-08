package com.cristianobalz.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cristianobalz.business.BusinessObject;
import com.cristianobalz.web.implement.ValidationException;

public class ServletManager {
	
	private ServletInterface<BusinessObject> servletInterfaceObj;
	public ServletManager(ServletInterface<BusinessObject> servletInterfaceObj) {
		this.servletInterfaceObj = servletInterfaceObj;
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		ServletPathEnum servletPathEnum = findEnumByValue(request.getParameter("action"));
		
		try
		{
			switch (servletPathEnum) {
			case NEW:
				getServlet().newReg(request, response);
				break;
			case INSERT:
				getServlet().insert(request, response);
				break;
			case DELETE:
				getServlet().delete(request, response);
				break;
			case EDIT:
				getServlet().edit(request, response);
				break;
			case UPDATE:
				getServlet().update(request, response);
				break;
			default:
				getServlet().list(request, response);
				break;
			}
		} catch(ValidationException e) {
			request.setAttribute("message", e.getMessage());
			servletInterfaceObj.printMessageValidateForm(request, response);
		}
	}	

	private ServletInterface<BusinessObject> getServlet() {		
		return this.servletInterfaceObj;
	}
	
	private ServletPathEnum findEnumByValue(String path) {
		for(ServletPathEnum pathEnum : ServletPathEnum.values()) {
			if(path != null && path.equals(pathEnum.getPath())) {
				return pathEnum;
			}
		}
		return ServletPathEnum.LIST;
	}

	protected enum ServletPathEnum {

		NEW("new"), INSERT("insert"), DELETE("delete"), EDIT("edit"), UPDATE("update"), LIST("list");

		String path;

		ServletPathEnum(String path) {
			this.path = path;
		}
		
		public String getPath() {
			return path;
		}

	}

}
