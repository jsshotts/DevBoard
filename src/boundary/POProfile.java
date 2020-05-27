package boundary;

import java.util.logging.Level;

import controller.Log;
import controller.SessionController;
import entity.Developer;
import entity.ProjectOwner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class POProfile {
	
	@FXML
	private Label projectOwnerName;
	
	@FXML
	private Label email;
	
	@FXML
	private Label bio;
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.PO_PROFILE_SCREEN));
			borderPane.setCenter(switchScreen);
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	public void populate() {
		ProjectOwner po = SessionController.getInstance().getProjectOwner();
		projectOwnerName.setText(po.getName());
		email.setText(po.getEmail());
		bio.setText(po.getBio());
	}
}