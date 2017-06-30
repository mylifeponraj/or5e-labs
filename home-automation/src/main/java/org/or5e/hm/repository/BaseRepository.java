package org.or5e.hm.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.or5e.core.BaseObject;

public abstract class BaseRepository extends BaseObject {
	protected static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}


}
