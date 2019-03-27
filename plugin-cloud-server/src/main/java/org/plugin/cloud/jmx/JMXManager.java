package org.plugin.cloud.jmx;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.springframework.stereotype.Component;

@Component("jmxMgr")
public class JMXManager {
	private MBeanServer server = ManagementFactory.getPlatformMBeanServer();
	private ObjectName objectName = null;
	public JMXManager() {
		initilize();
		System.out.println("Initilizing JXM Manager.");
	}
	public void initilize() {
		try {
		    objectName = new ObjectName("org.plugin.cloud:type=basic,name=HABean");
		    server.registerMBean(HABeanObject.getHABean(), objectName);
		} catch (MalformedObjectNameException e) {
		    e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		}
	}
}
