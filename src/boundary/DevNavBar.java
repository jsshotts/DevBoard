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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class DevNavBar {
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.DEV_NAVBAR_VIEW));
			Node devNavBarNode = fxmlLoader.load();
			DevNavBar devNavBarController = fxmlLoader.<DevNavBar>getController();
			devNavBarController.init();
			borderPane.setTop(devNavBarNode);
			SessionController.getInstance().setDevNavBar(devNavBarController);
			SessionController.getInstance().setPrevWindow(WindowManager.DEV_FINDPROJ_SCREEN);
			SessionController.getInstance().highlightPoNavBar();
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
	@FXML
	private ImageView imageView;
	
	@FXML
	private void initialize() {
		Image img = new Image("res/LogoWhite.png");
		imageView.setImage(img);
	}
	
	public void init() {
		SessionController session = SessionController.getInstance();
		User user = session.getUser();
		userName.setText(user.getName());
	}
	
	public void navBar(ActionEvent event) {
		if (event.getSource() == findProjectsButton) {
			DevFindProject.swapTo(event);
			SessionController.getInstance().setPrevWindow(WindowManager.DEV_FINDPROJ_SCREEN);
		}
		if (event.getSource() == activeProjectsButton) {
			DevActiveProjects.swapTo(event);
			SessionController.getInstance().setPrevWindow(WindowManager.DEV_ACTIVEPROJ_SCREEN);
		}
		if (event.getSource() == myApplicationsButton) {
			DevMyApplications.swapTo(event);
			SessionController.getInstance().setPrevWindow(WindowManager.DEV_MYAPPLICATIONS_SCREEN);
		}
		if (event.getSource() == profileButton) {
			SessionController.getInstance().setPrevWindow(WindowManager.DEV_PROFILE_SCREEN);
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
		}
	}
}
