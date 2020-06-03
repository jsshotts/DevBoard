package application;
	
import java.util.logging.Level;

import boundary.WindowManager;
import controller.Log;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage){
		try {
			Parent login = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.LOGIN_SCREEN));
			BorderPane borderPane = new BorderPane();
			borderPane.setCenter(login);
			borderPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        
			primaryStage.setScene(new Scene(borderPane, WindowManager.WIN_WIDTH, WindowManager.WIN_HEIGHT));
			primaryStage.setMaximized(true);
			primaryStage.setTitle("DevBoard");
			primaryStage.show();
			primaryStage.getScene().getStylesheets().add(WindowManager.CARDBOX_STYLE);
			primaryStage.getScene().getStylesheets().add(WindowManager.SMALLPROJECTVIEW_STYLE);
			primaryStage.getScene().getStylesheets().add(WindowManager.NAVBAR_STYLE);
			primaryStage.getScene().getStylesheets().add(WindowManager.BIGBUTTON_STYLE);
			primaryStage.getScene().getStylesheets().add(WindowManager.SMALLBUTTON_STYLE);
			
		}catch(Exception e){
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	public static void main() {
		Log.initLogger();
		launch();
	}
}