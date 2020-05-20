package controller;

import entity.Developer;
import entity.ProjectOwner;

public class LoginController {
	
	public void loginDeveloper() {
		SessionController session = SessionController.getInstance();
		session.setUser(new Developer("John", "Python"));
	}
	
	public void loginProjectOwner() {
		SessionController session = SessionController.getInstance();
		session.setUser(new ProjectOwner("Proactive Tech", "We are a company"));
	}
}
