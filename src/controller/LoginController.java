package controller;

import java.util.logging.Level;

import entity.Developer;
import entity.ProjectOwner;

public class LoginController {
	
	DatabaseController database = new DatabaseController();
	SessionController session = SessionController.getInstance();
	
	public void loginDeveloper(String email) {

		Developer dev = database.getOne(Developer.class, "email", email);
		if(dev != null) {
			Log.logger.log(Level.INFO, () -> "Logged In! as: " + dev.getName());
			session.setUser(dev);
		}
	}
	
	public void loginProjectOwner(String email) {
		ProjectOwner po = database.getOne(ProjectOwner.class, "email", email);
		if(po != null) {
			Log.logger.log(Level.INFO, () -> "Logged In! as: " + po.getName());
			session.setUser(po);
		}
	}
}
