package controller;

import java.util.logging.Level;

import boundary.DevNavBar;
import boundary.PONavBar;
import boundary.WindowManager;
import entity.Developer;
import entity.ProjectOwner;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class SessionController {
	
	private static SessionController instance;
	
	private User user;
	
	private String prevWindow = null;
	private DevNavBar devNavBar;
	private PONavBar poNavBar;
	
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
	
	public void highlightDevNavBar() {
		if (prevWindow.equals(WindowManager.DEV_FINDPROJ_SCREEN))
			devNavBar.findProjectsButton.requestFocus();
		if (prevWindow.equals(WindowManager.DEV_MYAPPLICATIONS_SCREEN))
			devNavBar.myApplicationsButton.requestFocus();
		if (prevWindow.equals(WindowManager.DEV_PROFILE_SCREEN))
			devNavBar.profileButton.requestFocus();
	}
	
	public void highlightPoNavBar() {
		if (prevWindow.equals(WindowManager.PO_POSTPROJ_SCREEN))
			poNavBar.createProjectButton.requestFocus();
		if (prevWindow.equals(WindowManager.PO_MYPROJECTS_SCREEN))
			poNavBar.myProjectsButton.requestFocus();
		if (prevWindow.equals(WindowManager.PO_PROFILE_SCREEN))
			devNavBar.profileButton.requestFocus();
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

	public DevNavBar getDevNavBar() {
		return devNavBar;
	}

	public void setDevNavBar(DevNavBar devNavBar) {
		this.devNavBar = devNavBar;
	}

	public PONavBar getPoNavBar() {
		return poNavBar;
	}

	public void setPoNavBar(PONavBar poNavBar) {
		this.poNavBar = poNavBar;
	}
}