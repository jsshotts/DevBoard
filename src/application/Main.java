package application;
	
import boundary.WindowManager;
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
	        
			primaryStage.setScene(new Scene(borderPane, WindowManager.WIN_WIDTH, WindowManager.WIN_HEIGHT));
			primaryStage.setMaximized(true);
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
