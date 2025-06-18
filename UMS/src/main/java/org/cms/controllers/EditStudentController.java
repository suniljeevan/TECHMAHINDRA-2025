package org.cms.controllers;

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
 * Servlet implementation class EditStudentController
 */
public class EditStudentController extends HttpServlet {
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

        String sid = request.getParameter("id");

        Student student = studentRepo.fetchOneStudent(sid); // Get student from DB

        if (student != null) {
            request.setAttribute("student", student);
            request.getRequestDispatcher("edit-student.jsp").forward(request, response);
        } else {
            response.getWriter().println("Student not found");
        }
    }
}