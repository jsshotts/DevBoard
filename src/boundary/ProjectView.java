package boundary;

import entity.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class ProjectView {
	
	@FXML
	public Label headline;
	@FXML
	public Label projectOwnerName;
	@FXML
	public Label description;
	@FXML
	public Label platform;
	@FXML
	public Label duration;
	@FXML
	public Label projectLocation;
	@FXML
	public Label remote;
	@FXML
	public Button moreDetailsButton;
	
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public void populate(Project project) {	
		setProject(project);
		headline.setText(project.getTitle());
		description.setText(project.getDescription());
		duration.setText(project.getDuration());
		projectLocation.setText(project.getLocation());
		platform.setText(project.getPlatform().getString());
		remote.setText(project.getRemote());
		projectOwnerName.setText(project.getProjectOwnerName());
	}
}