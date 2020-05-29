package boundary;

import java.io.IOException;
import java.util.logging.Level;

import controller.Log;
import controller.SessionController;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PONavBar {
	
	private static final String UNDERLINE_TRUE = "-fx-underline: true";
	private static final String UNDERLINE_FALSE = "-fx-underline: false";
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.PO_NAVBAR_VIEW));
			Node poNavBarNode = fxmlLoader.load();
			PONavBar poNavBarController = fxmlLoader.<PONavBar>getController();
			poNavBarController.init();
			
			borderPane.setTop(poNavBarNode);
			poNavBarController.createProjectButton.getScene().getStylesheets().add(WindowManager.NAVBAR_STYLE);
			poNavBarController.createProjectButton.setStyle(UNDERLINE_TRUE);
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
	
	@FXML
	public Label userName;
	
	public void init() {
		SessionController session = SessionController.getInstance();
		User user = session.getUser();
		userName.setText(user.getName());
	}
	
	public void navBar(ActionEvent event) {
		
		if (event.getSource() == createProjectButton) {
			POPostProject.swapTo(event);
			createProjectButton.setStyle(UNDERLINE_TRUE);
			myProjectsButton.setStyle(UNDERLINE_FALSE);
			profileButton.setStyle(UNDERLINE_FALSE);
		}
		if (event.getSource() == myProjectsButton) {
			POMyProjects.swapTo(event);
			createProjectButton.setStyle(UNDERLINE_FALSE);
			myProjectsButton.setStyle(UNDERLINE_TRUE);
			profileButton.setStyle(UNDERLINE_FALSE);
		}
		if (event.getSource() == profileButton) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.PO_PROFILE_SCREEN));
				Node poProfileNode = fxmlLoader.load();
				POProfile poProfileView = fxmlLoader.<POProfile>getController();
				poProfileView.populate();
				BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
				borderPane.setCenter(poProfileNode);
			} catch (IOException e) {
				Log.logger.log(Level.WARNING, e.getMessage());
			}
			createProjectButton.setStyle(UNDERLINE_FALSE);
			myProjectsButton.setStyle(UNDERLINE_FALSE);
			profileButton.setStyle(UNDERLINE_TRUE);
		}
	}
}
