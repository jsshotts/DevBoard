package controller;

import java.util.logging.Level;

import entity.Developer;
import entity.ProjectOwner;

public class CreateAccountController {
	DatabaseController database = new DatabaseController();
	SessionController session = SessionController.getInstance();
	
	public void AddDeveloper(String name, String bio, String email, String password) {
		Developer d = new Developer(name, bio, email, password);
		Log.logger.log(Level.INFO, database.pushNew(d).toString());
	}
	
	public void AddOwner(String name, String bio, String email, String password) {
		ProjectOwner d = new ProjectOwner(name, bio, email, password);
		Log.logger.log(Level.INFO, database.pushNew(d).toString());
	}
}
