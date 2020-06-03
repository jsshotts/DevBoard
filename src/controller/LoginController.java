package controller;

import java.util.logging.Level;

import entity.Developer;
import entity.ProjectOwner;

public class LoginController {
	
	SessionController session = SessionController.getInstance();
	DataSource database;
	
	public LoginController(DataSource database) {
		this.database = database;
	}
	
	public boolean loginDeveloper(String email) {
		Developer dev = database.getOne(Developer.class, "email", email);
		if(dev != null) {
			Log.logger.log(Level.INFO, () -> "Logged In! as Dev: " + dev.getName());
			getSession().setUser(dev);
			return true;
		}
		return false;
	}
	
	public boolean loginProjectOwner(String email) {
		ProjectOwner po = database.getOne(ProjectOwner.class, "email", email);
		if(po != null) {
			Log.logger.log(Level.INFO, () -> "Logged In! as PO: " + po.getName());
			getSession().setUser(po);
			return true;
		}
		return false;
	}

	public SessionController getSession() {
		return session;
	}

	public void setSession(SessionController session) {
		this.session = session;
	}
}
