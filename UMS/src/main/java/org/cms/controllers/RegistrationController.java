package org.cms.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class RegistrationController
 */
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletConfig config;
    private ServletContext context;
    public RegistrationController() {
    }

	public void init(ServletConfig config) throws ServletException {
		//Logger
		this.config=config;
		this.context=getServletContext();
		super.init(config);
		System.out.println("servlet loaded");
		}

	public ServletConfig getServletConfig() {
		return config;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		//Create ServletContext
		
		String email=context.getInitParameter("admin");
		//Create ServletConfig
		config=getServletConfig();
		String drivername=config.getInitParameter("driver");
		String un=config.getInitParameter("username");
		String pwd=config.getInitParameter("password");
		response.getWriter().print("<h2>"+"Driver:"+drivername+"</h2>");
		response.getWriter().print("<h2>"+"User"+un+"</h2>");
		response.getWriter().print("<h2>"+"Password"+pwd+"</h2>");
		response.getWriter().print("<h2>"+"mailto:"+email+"</h2>");
		//Create Session 
		HttpSession session=request.getSession();
		//HttpSession session=request.getSession(false);
		//Receive from Request
		
		// perform database operation
		response.sendRedirect("success.jsp");
	}

}
