package boundary;

import java.util.logging.Level;

import controller.CreateAccountController;
import controller.DatabaseController;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

public class CreateAccount {
	
	@FXML
	public CheckBox poCheck;
	
	@FXML
	public CheckBox devCheck;
	
	@FXML
	public Button cAccount;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextField bio;
	
	@FXML
	private ImageView imageView;
	
	private CreateAccountController createAccountController = new CreateAccountController();
	
	private LoginController loginController = new LoginController(new DatabaseController());
	
	@FXML
	private void initialize() {
		Image img = new Image("res/LogoWhite.png");
		imageView.setImage(img);
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
	
	public void checkDev() {
		poCheck.setDisable(devCheck.isSelected());
	}
	
	public void checkPO() {
		devCheck.setDisable(poCheck.isSelected());
	}
	
	public void createAccount(ActionEvent event){
		
		if(!email.getText().contains("@")) {
			Window primaryWindow = name.getScene().getWindow();				
	 		Toast toast = Toast.buildToast();
	 		toast.makeText(primaryWindow, "Invalid Email");
			return;
		}
		
		if(!devCheck.isSelected() && !poCheck.isSelected()) {
			Window primaryWindow = name.getScene().getWindow();				
	 		Toast toast = Toast.buildToast();
	 		toast.makeText(primaryWindow, "Please Check One: Developer or ProjectOwner");
	 		return;
		}
		
		if(name.getText().length() < 1 || bio.getText().length() < 1) {
			Window primaryWindow = name.getScene().getWindow();				
	 		Toast toast = Toast.buildToast();
	 		toast.makeText(primaryWindow, "All Fields Are Required");
	 		return;
		}
		
		if (devCheck.isSelected()) {			
			createDeveloper(event);
		}
		else if (poCheck.isSelected()) {
			createProjectOwner(event);
		}			
	}
	
	public void createDeveloper(ActionEvent event) {
		
		Developer dev = createAccountController.addDeveloper(name.getText(), bio.getText(), email.getText());
		
		if (dev != null) {
			
			if (loginController.loginDeveloper(dev.getEmail())){
				
				Window primaryWindow = name.getScene().getWindow();				
		 		Toast toast = Toast.buildToast();
		 		toast.makeText(primaryWindow, "Account Created Successfully");
		 		
				DevNavBar.swapTo(event);
				DevFindProject.swapTo(event);
			}
			else {
				Window primaryWindow = email.getScene().getWindow();				
		 		Toast toast = Toast.buildToast();
		 		toast.makeText(primaryWindow, "Login Failed: Invalid Email");
			}
		}		
		else {
			
			Window primaryWindow = name.getScene().getWindow();				
	 		Toast toast = Toast.buildToast();
	 		toast.makeText(primaryWindow, "Account Creation Failed: Email Taken");
		}
	}
	
	public void createProjectOwner(ActionEvent event) {
		
		ProjectOwner projectOwner = createAccountController.addOwner(name.getText(), bio.getText(), email.getText());
		
		if (projectOwner != null) {
			
			if (loginController.loginProjectOwner(projectOwner.getEmail())){
				
				Window primaryWindow = name.getScene().getWindow();				
		 		Toast toast = Toast.buildToast();
		 		toast.makeText(primaryWindow, "Account Created Successfully");
				
				PONavBar.swapTo(event);
				POPostProject.swapTo(event);
			}
			else {
				Window primaryWindow = email.getScene().getWindow();				
		 		Toast toast = Toast.buildToast();
		 		toast.makeText(primaryWindow, "Login Failed: Invalid Email");
			}
		}
		else {
			Window primaryWindow = name.getScene().getWindow();				
	 		Toast toast = Toast.buildToast();
	 		toast.makeText(primaryWindow, "Account Creation Failed: Email Taken");
		}	
	}
}