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

public class PONavBar {
	
	@FXML
	private Button createProjectButton;
	
	@FXML
	private Button myProjectsButton;
	
	@FXML
	private Button profileButton;
	
	public void navBar(ActionEvent event) {
		try {
			
			Parent switchScreen;
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			
			if (event.getSource() == createProjectButton) {
				
				switchScreen = FXMLLoader.load(getClass().getResource(WindowManager.PO_CREATEPROJECT_SCREEN));
				borderPane.setCenter(switchScreen);
			}
			else if (event.getSource() == myProjectsButton) {
				
				switchScreen = FXMLLoader.load(getClass().getResource(WindowManager.PO_MYPROJECTS_SCREEN));
				borderPane.setCenter(switchScreen);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}
