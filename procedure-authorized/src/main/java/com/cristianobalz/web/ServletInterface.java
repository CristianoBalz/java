package com.cristianobalz.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cristianobalz.business.BusinessObject;
import com.cristianobalz.web.implement.ValidationException;

public interface ServletInterface<B extends BusinessObject>  {
	
	B getBusiness();
	
	void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ValidationException;
	
	void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	void newReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ValidationException;
	
	void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	void validateForm(HttpServletRequest request) throws ValidationException; 
	
	void printMessageValidateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
