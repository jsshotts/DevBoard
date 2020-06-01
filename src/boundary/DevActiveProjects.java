package boundary;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import controller.FindProjectsController;
import controller.Log;
import entity.Project;

import java.util.logging.Level;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class DevActiveProjects {
	
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private Label emptyActiveProjectsLabel;
	
	private boolean isEmpty = false;
	
	@FXML
	private void initialize() {
		
		emptyActiveProjectsLabel.setVisible(false);
		
		VBox vbox = new VBox();
		
		Task<Void> task = new Task<Void>() {
			
			@Override
			protected Void call() throws Exception {
				Map<UUID, Project> map = getDevActiveProjects();
				if(!map.isEmpty()) {
					initializeListView(getDevActiveProjects(), vbox);
				}
				else {
					isEmpty = true;
				}
				return null;
			}
		};
		task.setOnSucceeded(succeededEvent -> {
			
			if(isEmpty) {
 				emptyActiveProjectsLabel.setVisible(true);
 			}
			
			scrollPane.setContent(vbox);
			vbox.prefHeightProperty().bind(scrollPane.widthProperty());
			vbox.prefWidthProperty().bind(scrollPane.widthProperty());
			scrollPane.setFitToHeight(true);
 			 
         });
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();	
	}
	
	private Map<UUID, Project> getDevActiveProjects()
	{
		FindProjectsController findProjectsController = new FindProjectsController();
		return findProjectsController.getDevActiveProjects();
	}
	
	private void initializeListView(Map<UUID, Project> projects, VBox vbox) {
		try {
			for(Project p : projects.values()) {
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.DEV_ACTIVE_PROJECT_CARD));
				Node projectCard = fxmlLoader.load();
				DevActiveProjectCard activeProjectCard = fxmlLoader.<DevActiveProjectCard>getController();
				activeProjectCard.populate(p);
				vbox.getChildren().add(projectCard);
			}
		}
		catch (Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
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
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
}
