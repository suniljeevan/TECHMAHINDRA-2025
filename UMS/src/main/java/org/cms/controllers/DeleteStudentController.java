package org.cms.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.cms.DATASOURCES.MySqlDBSource;
import org.cms.REPOSITORY.StudentRepository;

/**
 * Servlet implementation class DeleteStudentController
 */
public class DeleteStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MySqlDBSource ds;
	private StudentRepository studentRepo;

    @Override
    public void init() throws ServletException {
    	try {
    	ds=new MySqlDBSource();
        studentRepo = new StudentRepository(ds);
    	}catch(Exception e) {}
    }

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	       String id = request.getParameter("sid");
	        studentRepo.deleteStudent(id);
	        response.sendRedirect("student-list.jsp");
	    }
	}