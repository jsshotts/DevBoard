package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(new URL("file:Boundary/Login.fxml"));
			
			VBox vbox = loader.<VBox>load();
			Scene scene = new Scene(vbox);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("DevBoard");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
