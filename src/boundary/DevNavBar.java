package boundary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.*;

public class DevNavBar {
	
	static <T> void swapTo(ActionEvent event, Class<T> obj)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(obj.getResource(WindowManager.DEV_NAVBAR_VIEW));
			borderPane.setTop(switchScreen);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public Button findProjectsButton;
	@FXML
	public Button activeProjectsButton;
	@FXML
	public Button myApplicationsButton;
	@FXML
	public Button profileButton;
	
	public void navBar(ActionEvent event) {

		if (event.getSource() == findProjectsButton) {
			DevFindProject.swapTo(event, getClass());
		}
		else if (event.getSource() == activeProjectsButton) {
			DevActiveProjects.swapTo(event, getClass());
		}
		else if (event.getSource() == profileButton) {
			DevProfile.swapTo(event, getClass());
		}
	}
}
