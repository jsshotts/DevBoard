package boundary;

import entity.Developer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ApplicantCard {
	
	@FXML
	private Label devName;
	
	@FXML
	private Button viewProfile;
	
	@FXML
	private Button sendOffer;
	
	public void populate(Developer developer) {
		devName.setText(developer.getName());
	}
}