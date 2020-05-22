package boundary;

import java.util.logging.Level;

import controller.Log;
import entity.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class SmallProjectView {
	
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
	
	@FXML
	private Button moreDetailsButton;
	
	private Project project;
	
	public void populate(Project project) {	
		this.project = project;
		headline.setText(project.getTitle());
		description.setText(project.getDescription());
		duration.setText(project.getDuration());
		projectLocation.setText(project.getLocation());
		platform.setText(project.getPlatform());
		remote.setText(project.getRemote());
	}
	
	public void moreDetails(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.LARGE_PROJECT_VIEW));
			Node largeProjectNode = fxmlLoader.load();
			LargeProjectView largeProjectView = fxmlLoader.<LargeProjectView>getController();
			largeProjectView.populate(project);
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			borderPane.setCenter(largeProjectNode);
		}
		catch (Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
		
}