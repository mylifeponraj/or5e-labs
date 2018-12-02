import org.plugin.cloud.config.AppConfig;
import org.plugin.cloud.db.dao.UserMasterDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		UserMasterDAO userDAO = context.getBean(UserMasterDAO.class);

		System.out.println(userDAO.getAllUsers().get(0).getUserName());
		
		context.close();
	}
}
