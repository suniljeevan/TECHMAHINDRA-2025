package org.cms.controllers;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class AuthFilter
 */
public class AuthFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AuthFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession oldsession = req.getSession(false);

        String uri = req.getRequestURI();

        // Allow access to login page
        if (uri.endsWith("/login") || uri.endsWith("/logout")) {
            chain.doFilter(request, response);
            return;
        }

        // Get user role from session
        String role = (oldsession != null) ? (String) oldsession.getAttribute("ROLE") : null;

        // Role-based access control
        if (uri.endsWith("/RC") && !"admin".equals(role)) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied! Admin Only.");
            return;
        } 
        if (uri.endsWith("/FetchAll") && !"faculty".equals(role)) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied! Faculty Only.");
            return;
        } 
        if (uri.endsWith("/FetchAll") && !"student".equals(role)) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied! Students Only.");
            return;
        }

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
