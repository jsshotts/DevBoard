package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ActiveProjectCard {
	
	@FXML
	private Button messageButton;
	
	public void initPOCard() {
		messageButton.setText("Message Developer");
	}
}