package boundary;

import controller.ApplyController;
import entity.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	
	@FXML
	private Button apply;
	
	private Project project;
	
	public void populate(Project project) {	
		this.project = project;
		description.setText(project.getDescription());
		duration.setText(project.getDuration());
		headline.setText(project.getTitle());
		projectLocation.setText(project.getLocation());
		platform.setText(project.getPlatform());
		remote.setText(project.getRemote());
	}
	
	public void apply() { 
		ApplyController applyController = new ApplyController();
		applyController.apply(project);
	}

}