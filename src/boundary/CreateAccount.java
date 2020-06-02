package boundary;

import java.util.logging.Level;

import controller.CreateAccountController;
import controller.Log;
import controller.LoginController;
import entity.Developer;
import entity.ProjectOwner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

public class CreateAccount {
	
	@FXML
	public CheckBox POcheck;
	
	@FXML
	public CheckBox Dcheck;
	
	@FXML
	public Button cAccount;
	
	@FXML
	private TextField name;
	
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
	
	@FXML
	public void initialize() {
		
	}
	
	public void checkDev(ActionEvent event) {
		if(Dcheck.isSelected()) {
			POcheck.setDisable(true);
		}
		else {
			POcheck.setDisable(false);
		}
	}
	
	public void checkPO(ActionEvent event) {
		if(POcheck.isSelected()) {
			Dcheck.setDisable(true);
		}
		else {
			Dcheck.setDisable(false);
		}
	}
	
	public void createAccount(ActionEvent event){
		
		if(!email.getText().contains("@")) {
			email.setText("Invalid email");
			return;
		} 
		
		if (Dcheck.isSelected()) {
			
			Developer dev = createAccount.AddDeveloper(name.getText(), bio.getText(), email.getText());
			
			if (dev != null) {								
				attemptLoginDeveloper(event, dev);
			}
		}
		if (POcheck.isSelected()) {
			
			ProjectOwner projectOwner = createAccount.AddOwner(name.getText(), bio.getText(), email.getText());
			
			if (projectOwner != null) {
				attemptLoginProjectOwner(event, projectOwner);
			}
		}
	}
	
	public void attemptLoginDeveloper(ActionEvent event, Developer dev) {
		
		if (loginController.loginDeveloper(dev.getEmail())){
			DevNavBar.swapTo(event);
			DevFindProject.swapTo(event);
		}
		else {
			Window primaryWindow = email.getScene().getWindow();				
	 		Toast toast = Toast.buildToast();
	 		toast.makeText(primaryWindow, "Login Failed: Invalid Email");
		}
	}
	
	public void attemptLoginProjectOwner(ActionEvent event, ProjectOwner projectOwner) {
		
		if (loginController.loginProjectOwner(projectOwner.getEmail()))
		{
			PONavBar.swapTo(event);
			POPostProject.swapTo(event);
		}
		else {
			Window primaryWindow = email.getScene().getWindow();				
	 		Toast toast = Toast.buildToast();
	 		toast.makeText(primaryWindow, "Login Failed: Invalid Email");
		}
	}
	
//	public void createAccount(ActionEvent event){
//		
//		if(email.getText().contains("@")) {
//			
//			if(event.getSource() == cAccount){
//				
//				boolean worked = true;
//				
//				if (Dcheck.isSelected()) {
//					
//					if (createAccount.AddDeveloper(username.getText(), bio.getText(), email.getText())) {
//						
//						POcheck.setStyle("-fx-background-color: #00ff00");
//					}
//					else {
//						worked = false;
//						Dcheck.setStyle("-fx-background-color: #ff0000");
//						Dcheck.setText("Developer: ERROR USERNAME OR EMAIL ALREADY IN USE");
//					}
//				}
//				if (POcheck.isSelected()) {
//					
//					System.out.println(1);
//					
//					if (createAccount.AddOwner(username.getText(), bio.getText(), email.getText())) {
//						
//						POcheck.setStyle("-fx-background-color: #00ff00");
//					}
//					else {
//						worked = false;
//						POcheck.setStyle("-fx-background-color: #ff0000");
//						POcheck.setText("Project Owner: ERROR USERNAME OR EMAIL ALREADY IN USE");
//					}
//				} 
//				if (worked) {
//					DevNavBar.swapTo(event);
//					DevFindProject.swapTo(event);
//				}
//			}
//		} 
//		else {		
//			email.setText("Invalid email");
//		}
//	}
}
