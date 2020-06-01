package controller;

import java.awt.event.ActionEvent;
import java.util.logging.Level;

import entity.Developer;
import entity.ProjectOwner;
import entity.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class SessionController {
	
	private static SessionController instance;
	
	private User user;
	
	private String prevWindow = null;
	
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
	
	public void updateUser(User user) {
		if(this.user.getID().equals(user.getID())) {
			this.user = user;
		}
		else {
			Log.logger.log(Level.WARNING, "attempting to set new user without logging in");
		}
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

	public String getPrevWindow() {
		return prevWindow;
	}

	public void setPrevWindow(String prevWindow) {
		this.prevWindow = prevWindow;
	}
	
	public void back(ActionEvent event) {
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(ClassLoader.getSystemResource(prevWindow));
			borderPane.setCenter(switchScreen);
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
}