package controller;

import entity.Developer;
import entity.ProjectOwner;
import entity.User;

public class SessionController {
	
	private static SessionController instance;
	
	private User user;
	
	private SessionController() {}
	
	public static synchronized SessionController getInstance() {
		if(instance == null) {
			instance = new SessionController();
		}
		return instance;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public Developer getDeveloper() {
		if(this.user instanceof Developer){
			return (Developer)user;
		}
		return null;
	}
	
	public ProjectOwner getProjectOwner() {
		if(this.user instanceof ProjectOwner){
			return (ProjectOwner)user;
		}
		return null;
	}
	
	public boolean isDeveloper() {
		return user instanceof Developer;
	}
}