package org.or5e.hm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.or5e.hm.conf.ApplicationConfiguration;
import org.or5e.hm.conf.HibernateConfiguration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class AppInitilizer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	AnnotationConfigWebApplicationContext _context = null;
	@Override
	public void init() throws ServletException {
		super.init();
		_context = new AnnotationConfigWebApplicationContext();
		_context.register(HibernateConfiguration.class);
		_context.register(ApplicationConfiguration.class);
	}
}