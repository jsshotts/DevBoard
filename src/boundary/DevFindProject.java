package boundary;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.FindProjectsController;
import entity.Project;
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
	
	private GridPane gridPane;
	
	@FXML
	private void initialize() {
		
		Task<Void> task = new Task<Void>() {
	        @Override
	        protected Void call() throws Exception {
	            initializeGrid(getProjects());
	            return null;
	        }
	    };

         task.setOnSucceeded(succeededEvent -> {
        	 scrollPane.setContent(gridPane);
 			 gridPane.prefWidthProperty().bind(scrollPane.widthProperty());
 			 scrollPane.setFitToHeight(true);
 			 scrollPane.setFitToWidth(true);
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
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private List<Project> getProjects(){
		FindProjectsController controller = new FindProjectsController();
		return controller.getAllProjects();
	}
	
	private void initializeGrid(List<Project> projects) {
		
		gridPane = new GridPane();
		gridPane.addColumn(0);
		gridPane.addColumn(1);
		gridPane.addColumn(2);
		gridPane.addColumn(3);
		gridPane.addColumn(4);
		
		try {	
			
			for(int i = 0; i < projects.size(); i++) {
				
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.SMALL_PROJECT_VIEW));
				Node projectCard = fxmlLoader.load();
				SmallProjectView smallProjectView = fxmlLoader.<SmallProjectView>getController();
				smallProjectView.populate(projects.get(i));
				gridPane.add(projectCard, (i%2)*2+1,i/2);
			}
			
			gridPane.setHgap(100);
			gridPane.setVgap(100);
			
			ColumnConstraints column1 = new ColumnConstraints();
			ColumnConstraints column2 = new ColumnConstraints();
			ColumnConstraints column3 = new ColumnConstraints();
			ColumnConstraints column4 = new ColumnConstraints(); 
			ColumnConstraints column5 = new ColumnConstraints();
			
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