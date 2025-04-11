package org.cms.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import org.cms.DATASOURCES.MySqlDBSource;
import org.cms.MODEL.Student;
import org.cms.REPOSITORY.StudentRepository;
import org.cms.SERVICES.StudentServiceImpl;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session 
    	HttpSession session = request.getSession();
		try {
    		MySqlDBSource ds=new MySqlDBSource();
    		StudentRepository repository=new StudentRepository(ds);
    		StudentServiceImpl service=new StudentServiceImpl(repository);
    	    List<Student> fetched=service.fetchAll();
    	    session.setAttribute("xyz", fetched);
    		}catch(Exception e) {
    			System.out.println(e);
    		}
		RequestDispatcher rd = request.getRequestDispatcher("student-list.jsp");
		rd.forward(request, response);
	}

}
