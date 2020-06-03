package boundary;

import controller.DatabaseController;
import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;


public class LoginScreen {
	
	@FXML
	public Button createAccount;
		
	@FXML
	public Button devLogin;
	
	@FXML
	public Button projectOwnerLogin;
	
	@FXML
	public TextField usernameInput;
	
	@FXML
	private ImageView imageView;
	
<<<<<<< HEAD
	public LoginController loginController = new LoginController();
=======
	private LoginController loginController = new LoginController(new DatabaseController());
>>>>>>> 7fbf598d1ff0b447b564a495f510e212b4208988
	
	@FXML
	private void initialize() {
		Image img = new Image("res/LogoWhite.png");
		imageView.setImage(img);
	}	

	public void login(ActionEvent event){
		
		if(event.getSource() == createAccount){
			CreateAccount.swapTo(event);
		}
		else if(event.getSource() == devLogin){
			
			if (loginController.loginDeveloper(usernameInput.getText())){
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