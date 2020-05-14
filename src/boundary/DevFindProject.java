package boundary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class DevFindProject {
	
	@FXML
	private ScrollPane scrollPane;
	
	private GridPane gridPane = new GridPane();
	
	@FXML
	private void initialize() {
		
		Task<Void> task = new Task<Void>() {
	        @Override
	        protected Void call() throws Exception {
	            initializeGrid();
	            return null;
	        }
	    };

         task.setOnSucceeded((succeededEvent) -> {
        	 scrollPane.setContent(gridPane);
 			 gridPane.prefWidthProperty().bind(scrollPane.widthProperty());
 			 scrollPane.setFitToHeight(true);
         });
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();	
	}
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.DEV_FINDPROJ_SCREEN));
			borderPane.setCenter(switchScreen);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initializeGrid() {
		
		gridPane.addColumn(0);
		gridPane.addColumn(1);
		gridPane.addColumn(2);
		gridPane.addColumn(3);
		gridPane.addColumn(4);
		
		try {	
			
			for(int i = 0; i < 100; i++) {
				Node projectCard = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.SMALL_PROJECT_VIEW));
				gridPane.add(projectCard, (i%2)*2+1,i/2);
			}
			gridPane.setHgap(100);
			gridPane.setVgap(100);
			
			ColumnConstraints column1 = new ColumnConstraints(), column2 = new ColumnConstraints(), column3 = new ColumnConstraints(), column4 = new ColumnConstraints(), column5 = new ColumnConstraints();
			column1.setHgrow(Priority.ALWAYS);
			column3.setHgrow(Priority.ALWAYS);
			column5.setHgrow(Priority.ALWAYS);
			gridPane.getColumnConstraints().addAll(column1,column2, column3, column4, column5);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}