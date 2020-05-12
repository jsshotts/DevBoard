package boundary;

import application.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class DevNavBar {
	@FXML
	public Text DevBoardLogo;
	@FXML
	public Button findProjectsButton;
	@FXML
	public Button activeProjectsButton;
	@FXML
	public Button myApplicationsButton;
	@FXML
	public Button profileButton;
	
	public void navBar(ActionEvent event) {
		try {
			
			Parent switchScreen;
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			
			if (event.getSource() == findProjectsButton) {
				
				switchScreen = FXMLLoader.load(getClass().getResource(WindowManager.FINDPROJ_SCREEN));
				borderPane.setCenter(switchScreen);
			}
			else if (event.getSource() == activeProjectsButton) {
				
				switchScreen = FXMLLoader.load(getClass().getResource(WindowManager.ACTIVEPROJ_SCREEN));
				borderPane.setCenter(switchScreen);
			}
			else  if (event.getSource() == profileButton){
				
				switchScreen = FXMLLoader.load(getClass().getResource(WindowManager.DEV_PROFILE_SCREEN));
				borderPane.setCenter(switchScreen);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}
