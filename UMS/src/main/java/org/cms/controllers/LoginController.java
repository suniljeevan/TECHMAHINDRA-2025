package org.cms.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String un=request.getParameter("username");
		String pwd=request.getParameter("password");
		String role=null;
		if(un.equals("admin")&&pwd.equals("admin")) {
			role="admin";
		}else if(un.equals("presi_student")&&pwd.equals("123")) {
			role="student";
		} else if(un.equals("presi_faculty")&&pwd.equals("123")) {
			role="faculty";
		}
			else
				response.sendRedirect("error.jsp");
		
		HttpSession session=request.getSession();
		session.setAttribute("ROLE", role);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}







