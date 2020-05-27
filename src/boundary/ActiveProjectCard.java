package boundary;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import controller.HireController;
import controller.Log;
import entity.Developer;
import entity.Project;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ActiveProjectCard {
	
	@FXML
	private Button messageButton;
	
	@FXML
	private Button applicantsButton;
	
	@FXML
	private Label projectTitle;
	
	@FXML
	private Label status;
	
	@FXML
	private Label description;
	
	@FXML
	private VBox rootVbox;
	
	private Node applicantsView;
	
	private boolean applicantsVisible = false;
	
	private boolean getApplicantsInProgress = false;
	
	private HireController hireController = new HireController();
	
	private Project activeProject;
	
	@FXML
	private AnchorPane anchor;
	
	public void initDevCard() {
		anchor.getChildren().remove(applicantsButton);
	}
	
	public void initPOCard() {
		messageButton.setText("Message Developer");
	}
	
	public void toggleApplicants() {
		
		if(applicantsVisible && rootVbox.getChildren().contains(applicantsView)) {
			rootVbox.getChildren().remove(applicantsView);
			applicantsVisible = false;
		}
		else if(!getApplicantsInProgress){
			
			getApplicantsInProgress = true;
			displayProjectApplicants();
		}
	}
	
	public void displayProjectApplicants() {
		
		Task<Void> task = new Task<Void>() {
	        @Override
	        protected Void call() throws Exception {
	            setApplicantsView();
	            return null;
	        }
	    };

         task.setOnSucceeded(succeededEvent -> {
        	 rootVbox.getChildren().add(applicantsView);
			 applicantsVisible = true;
			 getApplicantsInProgress = false;
         });
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
	
	public void setApplicantsView() {
		
		try {
				
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.PROJECT_APPLICANTS_VIEW));
			applicantsView = fxmlLoader.load();
			ProjectApplicantsView projectApplicantsView = fxmlLoader.<ProjectApplicantsView>getController();
			projectApplicantsView.initialize(activeProject, getApplicants());
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	public List<Developer> getApplicants() {
		return hireController.getProjectApplicants(activeProject);
	}
	
	public void populate(Project project) {
		this.activeProject = project;
		projectTitle.setText(project.getTitle());
		description.setText(project.getDescription());
		status.setText(project.getStatusString());
	}
}