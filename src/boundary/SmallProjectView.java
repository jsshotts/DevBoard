package boundary;

import java.util.logging.Level;

import entity.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
	
	public void populate(Project project) {	
		headline.setText(project.getTitle());
		description.setText(project.getDescription());
		duration.setText(project.getDuration());
		projectLocation.setText(project.getLocation());
		platform.setText(project.getPlatform());
		remote.setText(project.getRemote());
	}
	
	public void moreDetails(ActionEvent event)
	{
		if (event.getSource() == moreDetailsButton)
			LargeProjectView.swapTo(event);
	}
}