package boundary;

import java.util.logging.Level;

import controller.CreateAccountController;
import controller.Log;
import controller.LoginController;
import entity.Developer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class CreateAccount {
	
	@FXML
	public CheckBox POcheck;
	
	@FXML
	public CheckBox Dcheck;
	
	@FXML
	public Button cAccount;
	
	@FXML
	private TextField username;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextField bio;
	
	private CreateAccountController createAccount = new CreateAccountController();
	private LoginController loginController = new LoginController();
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.CREATE_PROFILE));
			borderPane.setCenter(switchScreen);
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	public void createAccount(ActionEvent event){
		
		if(email.getText().contains("@")) {
			
			if(event.getSource() == cAccount){
				
				if (Dcheck.isSelected()) {
					
					Developer dev = createAccount.AddDeveloper(username.getText(), bio.getText(), email.getText());
					
				} else if (POcheck.isSelected()) {
					
					createAccount.AddOwner(username.getText(), bio.getText(), email.getText());
				} else {
					
					return;
				}
				DevNavBar.swapTo(event);
				DevFindProject.swapTo(event);
			}
		} else {
			email.setText("Invalid email");
		}
	}
}
