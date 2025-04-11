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

import org.cms.DATASOURCES.MySqlDBSource;
import org.cms.MODEL.Student;

import org.cms.REPOSITORY.*;

import org.cms.SERVICES.*;

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
		Student s=new Student();
		s.setSid(request.getParameter("id"));
		s.setSname(request.getParameter("name"));
		s.setEmail(request.getParameter("email"));
		s.setAddress(request.getParameter("address"));
		s.setYear(Integer.parseInt(
				request.getParameter("year").toString()));
		// perform database operation
		int r=0;
		try {
		MySqlDBSource ds=new MySqlDBSource();
		StudentRepository repository=new StudentRepository(ds);
		StudentServiceImpl service=new StudentServiceImpl(repository);
	     r=service.insertStudent(s);
		}catch(Exception e) {
			System.out.println(e);
		}
		if(r==1)
			 response.sendRedirect("student-list.jsp");	
		//request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request, response);
		else
		response.sendRedirect("error.jsp");	
	}

}
