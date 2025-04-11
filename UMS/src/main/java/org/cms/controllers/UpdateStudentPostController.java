package org.cms.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.cms.DATASOURCES.MySqlDBSource;
import org.cms.MODEL.Student;
import org.cms.REPOSITORY.StudentRepository;

/**
 * Servlet implementation class UpdateStudentPostController
 */
public class UpdateStudentPostController extends HttpServlet {
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int year = Integer.parseInt(request.getParameter("year").toString());
        Student student = new Student(id, name, email,address,year);
System.out.println(student);
       int r= studentRepo.updateStudent(student, id);
        if(r==1)
        	response.sendRedirect("Student");
        else
        	response.sendRedirect("error.jsp");
	}

}
