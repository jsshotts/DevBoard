package boundary;

import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


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
	private TextField usernameInput;
	
	private LoginController loginController = new LoginController();
	
	/* In SceneBuilder, I designated this function to be called when the "Developer" button is pushed.
	 * This function changes the scene to the 'Find Projects' Scene
	 */	
	public void login(ActionEvent event){

		if(event.getSource() == devLogin){
			loginController.loginDeveloper(usernameInput.getText());
			DevNavBar.swapTo(event);
			DevFindProject.swapTo(event);
		}
		else {
			loginController.loginProjectOwner(usernameInput.getText());
			PONavBar.swapTo(event);
			POPostProject.swapTo(event);
		}	
	}
}