package boundary;

import entity.Project;
import javafx.fxml.FXML;
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