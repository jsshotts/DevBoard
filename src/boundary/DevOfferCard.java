package boundary;

import entity.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DevOfferCard {
	
	@FXML
	private Label projectTitle;
	
	@FXML
	private Label shortDescription;
	
	@FXML
	private Label platform;
	
	@FXML
	private Label location;
	
	@FXML 
	private Label duration;
	
	@FXML
	private Label remote;
	
	@FXML
	private Label language;
	
	@FXML
	private Label bigDescription;
	
	@FXML
	private Button acceptOfferButton;
	
	@FXML
	private Button declineOfferButton;
	
	@FXML
	private Button viewProjectDetailsButton;
	
	public void populate(Project project) {
		projectTitle.setText(project.getTitle());
		bigDescription.setText(project.getDescription());
		language.setText(project.getLanguage().getString());
		remote.setText(project.getRemote());
		platform.setText(project.getPlatform());
		location.setText(project.getLocation());
	}
	
	

}
