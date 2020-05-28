package boundary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import controller.Log;
import entity.Project;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class POActiveProjectCard {
	
	@FXML
	private Button messageButton;
	
	@FXML
	private Button applicantsButton;
	
	@FXML
	private Button offersButton;
	
	@FXML
	private Label projectTitle;
	
	@FXML
	private Label status;
	
	@FXML
	private Label description;
	
	@FXML
	private VBox rootVbox;
	
	private Node applicantsView;
	
	private Node offersView;
	
	private boolean applicantsVisible = false;
	
	private boolean getApplicantsInProgress = false;
	
	private boolean offersVisible = false;
	
	private boolean getOffersInProgress = false;
	
	private Project activeProject;
	
	@FXML
	private AnchorPane anchor;
	
	public void toggleApplicants() {
		
		removeOffersView();
		
		if(applicantsVisible) {
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
			projectApplicantsView.initialize(activeProject);
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
		
	public void populate(Project project) {
		this.activeProject = project;
		projectTitle.setText(project.getTitle());
		description.setText(project.getDescription());
		status.setText(project.getStatusString());
	}
	
	public void toggleOffers() {
		
		removeApplicantsView();
		
		if(offersVisible) {
			rootVbox.getChildren().remove(offersView);
			offersVisible = false;
		}
		else if(!getOffersInProgress){			
			getOffersInProgress = true;
			displayProjectOffers();
			offersVisible = true;
			getOffersInProgress = false;
		}
	}
	
	public void displayProjectOffers() {
		
		try {
			
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.PROJECT_OFFERS_VIEW));
			offersView = fxmlLoader.load();
			ProjectOffersView projectOffersView = fxmlLoader.<ProjectOffersView>getController();
			projectOffersView.initOfferView(activeProject);
			rootVbox.getChildren().add(offersView);
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	public void removeApplicantsView() {
		rootVbox.getChildren().remove(applicantsView);
		applicantsVisible = false;
	}
	
	public void removeOffersView() {
		rootVbox.getChildren().remove(offersView);
		offersVisible = false;
	}
}