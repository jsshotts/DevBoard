package boundary;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DevActiveProjects {
	
	@FXML
	private ScrollPane scrollPane;
	private VBox vbox;;
	
	@FXML
	private void initialize() {
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				initializeListView();
				return null;
			}
		};
		task.setOnSucceeded(succeededEvent -> {
        	 scrollPane.setContent(vbox);
        	 vbox.prefHeightProperty().bind(scrollPane.widthProperty());
 			 vbox.prefWidthProperty().bind(scrollPane.widthProperty());
 			 scrollPane.setFitToHeight(true);
         });
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();	
	}
	
	private void initializeListView() {
		vbox = new VBox();
		try {	
			
			for(int i = 0; i < 10; i++) {
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.ACTIVE_PROJECT_VIEW));
				Node projectCard = fxmlLoader.load();
				vbox.getChildren().add(projectCard);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.DEV_ACTIVEPROJ_SCREEN));
			borderPane.setCenter(switchScreen);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
