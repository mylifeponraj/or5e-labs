package org.or5e.hm.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;

@Configuration
public class ApplicationConfiguration {
	public ApplicationConfiguration() {
		System.out.println("Spring Configuration getting initilized...");
	}
	
}
