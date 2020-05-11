package boundary;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class NavBar {
	
	public Text DevBoardLogo;
	public Button findProjectsButton;
	public Button activeProjectsButton;
	public Button myProjectsButton;
	public Button profileButton;
	
	public void navBar(ActionEvent event) {
		try {
			Scene newScene;
			Parent switchScreen;
			if (event.getSource() == findProjectsButton) {
				switchScreen = FXMLLoader.load(getClass().getResource(Main.FINDPROJ_SCREEN));
				newScene = new Scene(switchScreen, Main.WIN_WIDTH, Main.WIN_HEIGHT);
			}
			else if (event.getSource() == activeProjectsButton) {
				switchScreen = FXMLLoader.load(getClass().getResource(Main.ACTIVEPROJ_SCREEN));
				newScene = new Scene(switchScreen, Main.WIN_WIDTH, Main.WIN_HEIGHT);
			}
			else if (event.getSource() == myProjectsButton) {
				switchScreen = FXMLLoader.load(getClass().getResource(Main.MYPROJECTS_SCREEN));
				newScene = new Scene(switchScreen, Main.WIN_WIDTH, Main.WIN_HEIGHT);
			}
			else {
				switchScreen = FXMLLoader.load(getClass().getResource(Main.DEV_PROFILE_SCREEN));
				newScene = new Scene(switchScreen, Main.WIN_WIDTH, Main.WIN_HEIGHT);
			}
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(newScene);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	public void login(MouseEvent click) {
		try {
			Parent switchScreen = FXMLLoader.load(getClass().getResource(Main.LOGIN_SCREEN));
			Scene newScene = new Scene(switchScreen, Main.WIN_WIDTH, Main.WIN_HEIGHT);
			Stage window = (Stage)((Node)click.getSource()).getScene().getWindow();
			window.setScene(newScene);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			
			
	}
}
