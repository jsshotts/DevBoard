package controller;

import entity.Developer;
import entity.ProjectOwner;

public class LoginController {
	
	DatabaseController database = new DatabaseController();
	
	public void loginDeveloper(String email) {

		Developer dev = database.getOne(Developer.class, "email", email);
		if(dev != null) {
			System.out.println("Logged In! as: " + dev.getName());
			SessionController session = SessionController.getInstance();
			session.setUser(dev);
		}
	}
	
	public void loginProjectOwner(String email) {
		SessionController session = SessionController.getInstance();
		session.setUser(new ProjectOwner("Proactive Tech", "We are a company"));
	}
}
