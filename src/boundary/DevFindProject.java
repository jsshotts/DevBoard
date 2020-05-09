package boundary;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class DevFindProject {
	
	@FXML
	public MenuBar menuBar; //requires the menu bar fx:id to match the .fxml
	
	public void backToLogin() {
		
		try {
			
			Parent loginParent = FXMLLoader.load(getClass().getResource(Main.LOGIN_SCREEN));
			Scene newScene = new Scene(loginParent, Main.WIN_WIDTH, Main.WIN_HEIGHT);
						
			Stage window = (Stage)menuBar.getScene().getWindow();
			
			window.setScene(newScene);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}