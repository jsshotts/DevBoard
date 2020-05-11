package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	public static final String LOGIN_SCREEN = "../Boundary/Login.fxml";
	public static final String FINDPROJ_SCREEN = "../Boundary/DevFindProject.fxml";
	public static final String POSTPROJ_SCREEN = "../Boundary/postProject.fxml";
	public static final String DEV_PROFILE_SCREEN = "../Boundary/devProfile.fxml";
	public static final String ACTIVEPROJ_SCREEN = "../Boundary/DevActiveProjects.fxml";
	public static final String MYPROJECTS_SCREEN = "../Boundary/POMyProjects.fxml";
	public static final String POSTPROJECTS_SCREEN = "../Boundary/postProject.fxml";
	public static final String NAVBAR_VIEW= "../Boundary/NavBar.fxml";
	public static final int WIN_WIDTH = 900;
	public static final int WIN_HEIGHT = 600;
	
	@Override
	public void start(Stage primaryStage){
		try {
			Parent root = FXMLLoader.load(getClass().getResource(LOGIN_SCREEN));
	        
			primaryStage.setScene(new Scene(root, WIN_WIDTH, WIN_HEIGHT));
			primaryStage.setTitle("DevBoard");
			primaryStage.show();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
