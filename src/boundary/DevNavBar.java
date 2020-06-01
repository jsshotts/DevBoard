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

public class DevNavBar {
	
	private static final String UNDERLINE_TRUE = "-fx-underline: true";
	private static final String UNDERLINE_FALSE = "-fx-underline: false";
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.DEV_NAVBAR_VIEW));
			Node devNavBarNode = fxmlLoader.load();
			DevNavBar devNavBarController = fxmlLoader.<DevNavBar>getController();
			devNavBarController.init();
			borderPane.setTop(devNavBarNode);
			
			devNavBarController.findProjectsButton.getScene().getStylesheets().add(WindowManager.NAVBAR_STYLE);
			devNavBarController.findProjectsButton.setStyle(UNDERLINE_TRUE);
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	@FXML
	public Button findProjectsButton;
	@FXML
	public Button activeProjectsButton;
	@FXML
	public Button myApplicationsButton;
	@FXML
	public Button profileButton;
	@FXML
	public Label userName;
	
	public void init() {
		SessionController session = SessionController.getInstance();
		User user = session.getUser();
		userName.setText(user.getName());
	}
	
	public void navBar(ActionEvent event) {

		if (event.getSource() == findProjectsButton) {
			DevFindProject.swapTo(event);
			findProjectsButton.setStyle(UNDERLINE_TRUE);
			activeProjectsButton.setStyle(UNDERLINE_FALSE);
			myApplicationsButton.setStyle(UNDERLINE_FALSE);
			profileButton.setStyle(UNDERLINE_FALSE);
		}
		if (event.getSource() == activeProjectsButton) {
			DevActiveProjects.swapTo(event);
			findProjectsButton.setStyle(UNDERLINE_FALSE);
			activeProjectsButton.setStyle(UNDERLINE_TRUE);
			myApplicationsButton.setStyle(UNDERLINE_FALSE);
			profileButton.setStyle(UNDERLINE_FALSE);
		}
		if (event.getSource() == myApplicationsButton) {
			DevMyApplications.swapTo(event);
			findProjectsButton.setStyle(UNDERLINE_FALSE);
			activeProjectsButton.setStyle(UNDERLINE_FALSE);
			myApplicationsButton.setStyle(UNDERLINE_TRUE);
			profileButton.setStyle(UNDERLINE_FALSE);
		}
		if (event.getSource() == profileButton) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.DEV_PROFILE_SCREEN));
				Node devProfileNode = fxmlLoader.load();
				DevProfile devProfileView = fxmlLoader.<DevProfile>getController();
				devProfileView.populate(SessionController.getInstance().getDeveloper());
				BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
				borderPane.setCenter(devProfileNode);
			} catch (IOException e) {
				Log.logger.log(Level.WARNING, e.getMessage());
			}
			findProjectsButton.setStyle(UNDERLINE_TRUE);
			activeProjectsButton.setStyle(UNDERLINE_FALSE);
			myApplicationsButton.setStyle(UNDERLINE_FALSE);
			profileButton.setStyle(UNDERLINE_TRUE);
		}
	}
}
