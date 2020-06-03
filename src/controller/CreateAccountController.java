package controller;

import entity.Developer;
import entity.ProjectOwner;

public class CreateAccountController {
	DatabaseController database = new DatabaseController();
	SessionController session = SessionController.getInstance();
	
	public Developer addDeveloper(String name, String bio, String email) {
		
		Developer dev = database.getOne(Developer.class, "email", email);
		
		if(dev != null) {
			return null;
		}
		
		dev = new Developer(name, bio, email);
		database.pushNew(dev);
		return dev;
	}
	
	public ProjectOwner addOwner(String name, String bio, String email) {
		
		ProjectOwner projectOwner = database.getOne(ProjectOwner.class, "email", email);
		
		if(projectOwner != null) {
			return null;
		}
		
		projectOwner = new ProjectOwner(name, bio, email);
		database.pushNew(projectOwner);
		return projectOwner;
//>>>>>>> 7fbf598d1ff0b447b564a495f510e212b4208988
	}
}
