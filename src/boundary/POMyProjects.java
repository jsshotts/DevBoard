package boundary;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import controller.DatabaseController;
import controller.HireController;
import controller.Log;
import entity.Project;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class POMyProjects {
	
	@FXML
	private ScrollPane scrollPane;
	
	private VBox rootVbox;

	@FXML
	private void initialize() {
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				rootVbox = initializeListView(getActiveProjects());
				return null;
			}
		};
		task.setOnSucceeded(succeededEvent -> {
        	 scrollPane.setContent(rootVbox);
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
			Parent switchScreen = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.PO_MYPROJECTS_SCREEN));
			borderPane.setCenter(switchScreen);
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	private List<Project> getActiveProjects(){
		HireController hireController = new HireController(new DatabaseController());
		return hireController.getPOActiveProjects();
	}
	
	private VBox initializeListView(List<Project> projects) {
		VBox vbox = new VBox();
		try {	
			
			for(Project project : projects) {
				if (project != null)
				{
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.PO_ACTIVE_PROJECT_CARD));
				Node projectCard = fxmlLoader.load();			
				POActiveProjectCard activeProjectCard = fxmlLoader.<POActiveProjectCard>getController();
				
				activeProjectCard.populate(project);
				
				vbox.getChildren().add(projectCard);
				}
			}
		}
		catch (Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
		return vbox;
	}	
}
