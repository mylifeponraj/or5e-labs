package org.or5e.hm;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.or5e.hm.dao.UserMaster;

public class TestHibermate {
	private static SessionFactory factory;

	public static void main(String[] args) {
		try {
			factory = new AnnotationConfiguration().configure().
					// addPackage("com.xyz") //add package if used.
					addAnnotatedClass(UserMaster.class).buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		TestHibermate ME = new TestHibermate();

		/* Add few employee records in database */
		Integer empID1 = ME.addEmployee("Zara", "Ali", "A");
		Integer empID2 = ME.addEmployee("Daisy", "Das", "C");
		Integer empID3 = ME.addEmployee("John", "Paul", "S");

		/* List down all the employees */
		ME.listEmployees();

		/* Update employee's records */
		ME.updateEmployee(empID1);

		/* Delete an employee from the database */
		ME.deleteEmployee(empID2);

		/* List down new list of the employees */
		ME.listEmployees();
	}

	/* Method to CREATE an employee in the database */
	public Integer addEmployee(String uName, String uKey, String userType) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
		try {
			tx = session.beginTransaction();
			UserMaster employee = new UserMaster();
			employee.setUserName(uName);
			employee.setUserKey(uKey);
			employee.setUserType(userType);
			employeeID = (Integer) session.save(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeID;
	}

	/* Method to READ all the employees */
	public void listEmployees() {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List employees = session.createQuery("FROM UserMaster").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				UserMaster employee = (UserMaster) iterator.next();
				System.out.print(" ID: " + employee.getUserID());
				System.out.print("  Name: " + employee.getUserName());
				System.out.println(" Status: " + employee.getUserStatus());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE salary for an employee */
	public void updateEmployee(Integer EmployeeID) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserMaster employee = (UserMaster) session.get(UserMaster.class, EmployeeID);
			employee.setLastLoggedIn(new Timestamp(new java.util.Date().getTime()));
			session.update(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee from the records */
	public void deleteEmployee(Integer EmployeeID) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserMaster employee = (UserMaster) session.get(UserMaster.class, EmployeeID);
			session.delete(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
