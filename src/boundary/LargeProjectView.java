package boundary;

import entity.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
	
	public void populate(Project project) {	
		headline.setText(project.getTitle());
		description.setText(project.getDescription());
		duration.setText(project.getDuration());
		projectLocation.setText(project.getLocation());
		platform.setText(project.getPlatform());
		remote.setText(project.getRemote());
	}
}