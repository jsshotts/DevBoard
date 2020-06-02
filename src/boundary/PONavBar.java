package boundary;

import java.util.logging.Level;

import controller.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class PONavBar {
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.PO_NAVBAR_VIEW));
			borderPane.setTop(switchScreen);
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	@FXML
	private Button createProjectButton;
	
	@FXML
	private Button myProjectsButton;
	
	@FXML
	private Button profileButton;
	
	public void navBar(ActionEvent event) {
		
		if (event.getSource() == createProjectButton) {
			POPostProject.swapTo(event);
		}
		if (event.getSource() == myProjectsButton) {
			POMyProjects.swapTo(event);
		}
		if (event.getSource() == profileButton) {
			POProfile.swapTo(event);
		}
	}
	
	
}
