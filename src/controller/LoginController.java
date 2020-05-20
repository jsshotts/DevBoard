package controller;

import java.util.Map;
import java.util.UUID;

import entity.Developer;
import entity.ProjectOwner;

public class LoginController {
	
	DatabaseController database = new DatabaseController();
	
	public void loginDeveloper(String email) {
		
		Map<UUID, Object> map = database.getAll(DatabaseController.DEVELOPER_TYPE, "email", email);
		
		if(!map.isEmpty()) {
			Developer dev = ((Developer)database.getAll(DatabaseController.DEVELOPER_TYPE, "email", email).values().iterator().next());
			if(dev != null) {
				System.out.println("Logged In! as: " + dev.getName());
				SessionController session = SessionController.getInstance();
				session.setUser(new Developer("John", "Python"));
			}
		}
	}
	
	public void loginProjectOwner(String email) {
		SessionController session = SessionController.getInstance();
		session.setUser(new ProjectOwner("Proactive Tech", "We are a company"));
	}
}
