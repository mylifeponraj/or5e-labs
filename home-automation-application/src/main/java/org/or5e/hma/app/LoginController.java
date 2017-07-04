package org.or5e.hma.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView hello(HttpServletRequest request, HttpServletResponse res) {
		String name = request.getParameter("uName");
		String password = request.getParameter("uKey");
		if(name.equals("admin") && password.equals("Welcome123!")) {
			return new ModelAndView("home").addObject("name", "Ponraj");
		}
		else {
			return new ModelAndView("index","message","Sorry, username or password error");
		}
	}
}
