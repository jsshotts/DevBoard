package boundary;

import java.util.logging.Level;

import controller.Log;
import entity.Project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ActiveProjectCard {
	
	@FXML
	private Button messageButton;
	
	@FXML
	private Button applicantsButton;
	
	@FXML
	private Label projectTitle;
	
	@FXML
	private Label status;
	
	@FXML
	private Label description;
	
	@FXML
	private VBox rootVbox;
	
	private Node applicantsView;
	
	private boolean applicantsVisible = false;
	
	@FXML
	private AnchorPane anchor;
	
	public void initDevCard() {
		anchor.getChildren().remove(applicantsButton);
	}
	
	public void initPOCard() {
		messageButton.setText("Message Developer");
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.PROJECT_APPLICANTS_VIEW));
			applicantsView = fxmlLoader.load();
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	public void toggleApplicants() {
		if(applicantsVisible) {
			rootVbox.getChildren().remove(applicantsView);
			applicantsVisible = false;
		}
		else {
			rootVbox.getChildren().add(applicantsView);
			applicantsVisible = true;
		}
	}
	
	public void populate(Project project) {
		projectTitle.setText(project.getTitle());
		description.setText(project.getDescription());
	}
}