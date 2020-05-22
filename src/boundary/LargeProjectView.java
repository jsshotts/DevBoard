package boundary;

import java.util.logging.Level;

import controller.Log;
import entity.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class LargeProjectView {
	
	@FXML
	private Label headline;
	
	@FXML
	private Label description;
	
	@FXML
	private Label platform;
	
	@FXML
	private Label duration;
	
	@FXML
	private Label projectLocation;
	
	@FXML
	private Label remote;
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.LARGE_PROJECT_VIEW));
			borderPane.setCenter(switchScreen);
		}
		catch(Exception e){
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	public void populate(Project project) {	
		headline.setText(project.getTitle());
		description.setText(project.getDescription());
		duration.setText(project.getDuration());
		projectLocation.setText(project.getLocation());
		platform.setText(project.getPlatform());
		remote.setText(project.getRemote());
	}
}