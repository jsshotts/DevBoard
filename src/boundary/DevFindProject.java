package boundary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class DevFindProject {
	
	static <T> void swapTo(ActionEvent event, Class<T> obj)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(obj.getResource(WindowManager.FINDPROJ_SCREEN));
			borderPane.setCenter(switchScreen);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void initialize() {}
	
}