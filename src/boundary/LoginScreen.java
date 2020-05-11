package boundary;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/* This class manages the login screen, and is associated with Login.fxml
 * In SceneBuilder, I set the fx:controller attribute of Login.fxml to "Boundary.LoginScreen" (i.e. this class), and that
 * links the fxml file to this class. Then, I can set the onAction attribute of the login button to any function in this class 
 */

public class LoginScreen {
		
	@FXML
	public Button devLogin;
	
	@FXML
	public Button projectOwnerLogin;
	
	@FXML
	private void initialize() {}
	
	/* In SceneBuilder, I designated this function to be called when the "Developer" button is pushed.
	 * This function changes the scene to the 'Find Projects' Scene
	 */	
	public void login(ActionEvent event) {
		
		//Here is where the scene is switched
		try {
			
			Scene newScene;
			
			if(event.getSource() == devLogin) {
				//load new FXML that you want to switch to (here I load the FXML of the Find Projects screen)
				Parent findProjParent = FXMLLoader.load(getClass().getResource(Main.FINDPROJ_SCREEN));
				newScene = new Scene(findProjParent, Main.WIN_WIDTH, Main.WIN_HEIGHT);
			}
			else {
				//load new FXML that you want to switch to (here I load the FXML of the Find Projects screen)
				Parent postProjParent = FXMLLoader.load(getClass().getResource(Main.POSTPROJ_SCREEN));
				newScene = new Scene(postProjParent, Main.WIN_WIDTH, Main.WIN_HEIGHT);
			}
			
			//Get access to the current stage
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			//set the new scene
			window.setScene(newScene);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
