package org.plugin.cloud.action;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.plugin.cloud.db.UserMaster;
import org.plugin.cloud.db.dao.UserMasterDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 5128454206810135768L;

	@Autowired
	UserMasterDAOImpl userMasterDAOImpl;
	@Override public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		userMasterDAOImpl = (UserMasterDAOImpl)webApplicationContext.getBean("userMasterDAOImpl");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");

		String name = request.getParameter("userID");
		String password = request.getParameter("userKey");
		UserMaster validUser = userMasterDAOImpl.isValidUser(name, password);
		if (validUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userID", validUser.getUserID());
			session.setAttribute("userName", validUser.getUserName());
			session.setAttribute("userDisplayName", validUser.getDisplayName());
			Cookie userIDCookie = new Cookie("userID",validUser.getUserName());
			Cookie userDisplayCookie = new Cookie("userDisplayName",validUser.getDisplayName().replaceAll(" ", "_"));
			Cookie userTypeCookie = new Cookie("userType",validUser.getUserType());
			response.addCookie(userTypeCookie);
			response.addCookie(userIDCookie);
			response.addCookie(userDisplayCookie);
			//request.getRequestDispatcher("index.jsp").include(request, response);
			response.sendRedirect("index.jsp");
		} else {
			request.getRequestDispatcher("login.html?message=LoginFailed").include(request, response);
		}
	}
}