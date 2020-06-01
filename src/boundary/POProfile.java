package boundary;

import java.util.logging.Level;

import controller.Log;
import controller.SessionController;
import entity.ProjectOwner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class POProfile {
	
	@FXML
	private Label projectOwnerName;
	
	@FXML
	private Label email;
	
	@FXML
	private Label bio;
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private void initialize() {
		Image img = new Image("res/poProfile.png");
		imageView.setImage(img);
	}
	
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