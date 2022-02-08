package com.cristianobalz.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cristianobalz.web.implement.ProcedureServletImpl;

@WebServlet(name = "Procedure")
public class ProcedureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ProcedureServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new ServletManager(new ProcedureServletImpl()).process(request, response);
	}

}
