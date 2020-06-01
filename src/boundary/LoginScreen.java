package boundary;

import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;


public class LoginScreen {
		
	@FXML
	public Button devLogin;
	
	@FXML
	public Button projectOwnerLogin;
	
	@FXML
	private TextField usernameInput;
	
	private LoginController loginController = new LoginController();

	public void login(ActionEvent event){

		devLogin.getScene().getStylesheets().add(WindowManager.CARDBOX_STYLE);
		devLogin.getScene().getStylesheets().add(WindowManager.SMALLPROJECTVIEW_STYLE);
		devLogin.getScene().getStylesheets().add(WindowManager.NAVBAR_STYLE);
		
		if(event.getSource() == devLogin){
			if (loginController.loginDeveloper(usernameInput.getText()))
			{
				DevNavBar.swapTo(event);
				DevFindProject.swapTo(event);
			}
			else {
				Window primaryWindow = usernameInput.getScene().getWindow();				
		 		Toast toast = Toast.buildToast();
		 		toast.makeText(primaryWindow, "Login Failed: Invalid Email");
			}
		}
		else {
			if (loginController.loginProjectOwner(usernameInput.getText()))
			{
				PONavBar.swapTo(event);
				POPostProject.swapTo(event);
			}
			else {
				Window primaryWindow = usernameInput.getScene().getWindow();				
		 		Toast toast = Toast.buildToast();
		 		toast.makeText(primaryWindow, "Login Failed: Invalid Email");
			}
		}
	}
}