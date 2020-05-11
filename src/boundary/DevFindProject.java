package boundary;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class DevFindProject {
	
	@FXML
	public MenuBar menuBar; //requires the menu bar fx:id to match the .fxml
	
	public void backToLogin() {
		
		try {
			
			Parent loginParent = FXMLLoader.load(getClass().getResource(Main.LOGIN_SCREEN));
			Scene newScene = new Scene(loginParent, Main.WIN_WIDTH, Main.WIN_HEIGHT);
						
			Stage window = (Stage)menuBar.getScene().getWindow();
			
			window.setScene(newScene);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findProject() {

		try {
			
			Parent findProjParent = FXMLLoader.load(getClass().getResource(Main.FINDPROJ_SCREEN));
			Scene newScene = new Scene(findProjParent, Main.WIN_WIDTH, Main.WIN_HEIGHT);
			
			Stage window = (Stage)menuBar.getScene().getWindow();
			
			window.setScene(newScene);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void activeProjects() {

		try {
			
			Parent activeProjParent = FXMLLoader.load(getClass().getResource(Main.ACTIVEPROJ_SCREEN));
			Scene newScene = new Scene(activeProjParent, Main.WIN_WIDTH, Main.WIN_HEIGHT);
			
			Stage window = (Stage)menuBar.getScene().getWindow();
			
			window.setScene(newScene);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void devProfile() {

		try {
			
			Parent devProfile = FXMLLoader.load(getClass().getResource(Main.DEV_PROFILE_SCREEN));
			Scene newScene = new Scene(devProfile, Main.WIN_WIDTH, Main.WIN_HEIGHT);

			Stage window = (Stage)menuBar.getScene().getWindow();
			
			window.setScene(newScene);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}