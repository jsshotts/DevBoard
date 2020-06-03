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

public class PONavBar {
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private void initialize() {
		Image img = new Image("res/LogoWhite.png");
		imageView.setImage(img);
	}
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.PO_NAVBAR_VIEW));
			Node poNavBarNode = fxmlLoader.load();
			PONavBar poNavBarController = fxmlLoader.<PONavBar>getController();
			poNavBarController.init();
			borderPane.setTop(poNavBarNode);
			SessionController.getInstance().setPoNavBar(poNavBarController);
			SessionController.getInstance().setPrevWindow(WindowManager.PO_POSTPROJ_SCREEN);
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	@FXML
	public Button createProjectButton;
	
	@FXML
	public Button myProjectsButton;
	
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
		
		if (event.getSource() == createProjectButton) {
			POPostProject.swapTo(event);
			SessionController.getInstance().setPrevWindow(WindowManager.PO_POSTPROJ_SCREEN);
		}
		if (event.getSource() == myProjectsButton) {
			POMyProjects.swapTo(event);
			SessionController.getInstance().setPrevWindow(WindowManager.PO_MYPROJECTS_SCREEN);
		}
		if (event.getSource() == profileButton) {
			SessionController.getInstance().setPrevWindow(WindowManager.PO_PROFILE_SCREEN);
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
		}
	}
	
	
}
