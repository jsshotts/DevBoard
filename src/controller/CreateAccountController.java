package controller;

import java.util.logging.Level;

import entity.Developer;
import entity.ProjectOwner;

public class CreateAccountController {
	DatabaseController database = new DatabaseController();
	SessionController session = SessionController.getInstance();
	
	public Developer AddDeveloper(String name, String bio, String email) {
		Developer dev = new Developer(name, bio, email);
		Log.logger.log(Level.INFO, database.pushNew(dev).toString());
		return dev;
	}
	
	public void AddOwner(String name, String bio, String email) {
		ProjectOwner d = new ProjectOwner(name, bio, email);
		Log.logger.log(Level.INFO, database.pushNew(d).toString());
	}
}
