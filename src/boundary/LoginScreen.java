package boundary;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
	private void initialize() {
		System.out.println("test");
	}
	
	/* In SceneBuilder, I designated this function to be called when the "Developer" button is pushed.
	 * This function changes the scene to the 'Find Projects' Scene
	 */	
	public void login(ActionEvent event){
		
		//Here is where the scene is switched
		try {
			
			Scene newScene;
			
			//Get access to the current stage
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			//get root element from current scene, which I know is BorderPane because it is set in Main
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			
			if(event.getSource() == devLogin){
				
				//load new FXML that you want to switch to (here I load the FXML of the Find Projects screen)
				Parent devNavBar = FXMLLoader.load(getClass().getResource(WindowManager.DEV_NAVBAR_VIEW));
				Parent findProject = FXMLLoader.load(getClass().getResource(WindowManager.FINDPROJ_SCREEN));
				
				//This changes what's on the screen, setting the center of the border pane to the screen we want
				borderPane.setTop(devNavBar);
				borderPane.setCenter(findProject);
			}
			else {
				
				//load new FXML that you want to switch to (here I load the FXML of the Find Projects screen)
				Parent poNavBar = FXMLLoader.load(getClass().getResource(WindowManager.PO_NAVBAR_VIEW));
				Parent postProject = FXMLLoader.load(getClass().getResource(WindowManager.PO_CREATEPROJECT_SCREEN));
				borderPane.setTop(poNavBar);
				borderPane.setCenter( postProject);
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
