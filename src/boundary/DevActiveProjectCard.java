package boundary;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import controller.HireController;
import controller.Log;
import entity.Developer;
import entity.Project;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class DevActiveProjectCard {
	
	@FXML
	private Button messageButton;
	
	@FXML
	private Label projectTitle;
	
	@FXML
	private Label status;
	
	@FXML
	private Label description;
	
	@FXML
	private VBox rootVbox;
	
	@FXML
	private AnchorPane anchor;
	
	public void populate(Project project) {
		projectTitle.setText(project.getTitle());
		description.setText(project.getDescription());
		status.setText(project.getStatusString());
	}
}