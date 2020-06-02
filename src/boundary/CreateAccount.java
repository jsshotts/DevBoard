package boundary;

import java.util.logging.Level;

import controller.CreateAccountController;
import controller.Log;
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
	private TextField usernameInput; //username
	
	@FXML
	private TextField usernameInput1; //password
	
	@FXML
	private TextField usernameInput2; //email
	
	@FXML
	private TextField usernameInput3; //bio
	
	private CreateAccountController createAccount = new CreateAccountController();
	
	/* In SceneBuilder, I designated this function to be called when the "Developer" button is pushed.
	 * This function changes the scene to the 'Find Projects' Scene
	 */	
	
	public void navBar(ActionEvent event) {
		
		if (event.getSource() == cAccount) {
			swapTo(event);
		}
	}
	
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
		if(usernameInput2.getText().contains("@")) {
			if(event.getSource() == cAccount){
				if (Dcheck.isSelected()) {
					
					createAccount.AddDeveloper(usernameInput.getText(), usernameInput3.getText(), usernameInput2.getText(), usernameInput1.getText());
				} else if (POcheck.isSelected()) {
					createAccount.AddOwner(usernameInput.getText(), usernameInput3.getText(), usernameInput2.getText(), usernameInput1.getText());
				} else {
					return;
				}
				DevNavBar.swapTo(event);
				DevFindProject.swapTo(event);
			}
		} else {
			usernameInput2.setText("Invalid email");
		}
	}
}
