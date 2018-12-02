package org.plugin.cloud.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet{

	private static final long serialVersionUID = 7685375591577227636L;

	@Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie ck=new Cookie("userID","");  
		ck.setMaxAge(0);
		Cookie ck1=new Cookie("userType","");
		ck1.setMaxAge(0);
		
		response.addCookie(ck);
		response.addCookie(ck1);
		request.getRequestDispatcher("Login.html?message=LoginFailed").include(request, response);
	}

	
}
