package boundary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class PONavBar {
	
	static <T> void swapTo(ActionEvent event, Class<T> obj)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(obj.getResource(WindowManager.PO_NAVBAR_VIEW));
			borderPane.setTop(switchScreen);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private Button createProjectButton;
	
	@FXML
	private Button myProjectsButton;
	
	@FXML
	private Button profileButton;
	
	public void navBar(ActionEvent event) {
		try {
			if (event.getSource() == createProjectButton) {
				POPostProject.swapTo(event, getClass());
			}
			else if (event.getSource() == myProjectsButton) {
				POMyProjects.swapTo(event, getClass());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}
