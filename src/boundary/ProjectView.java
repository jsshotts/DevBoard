package boundary;

import controller.DatabaseController;
import controller.FindProjectsController;
import entity.Project;
import entity.ProjectOwner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class ProjectView {
	
	@FXML
	public Label headline;
	@FXML
	public Button projectOwnerName;
	@FXML
	public Label description;
	@FXML
	public Label projectPlatform;
	@FXML
	public Label duration;
	@FXML
	public Label projectLocation;
	@FXML
	public Label remote;
	@FXML
	public Button moreDetailsButton;
	
	protected Project project;

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
		projectPlatform.setText(project.getPlatform().getString());
		remote.setText(project.getRemote());
		projectOwnerName.setText(project.getProjectOwnerName().toUpperCase());
	}
	
	public void viewProjectOwnerProfile(ActionEvent event) {
		FindProjectsController findProjectsController = new FindProjectsController(new DatabaseController());
		ProjectOwner projectOwner = findProjectsController.getProjectOwner(project.getProjectOwnerId());
		if(projectOwner != null) {
			POProfile.swapTo(event, projectOwner);
		}
	}
}