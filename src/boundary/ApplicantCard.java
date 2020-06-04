package boundary;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;
import java.util.logging.Level;

import controller.DatabaseController;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import javafx.event.ActionEvent;

public class ApplicantCard {
	
	@FXML
	private Label devName;
	
	@FXML
	private Button viewProfile;
	
	@FXML
	private Button sendOffer;
	
	private Developer developer;
	
	private Project project;
	
	private ProjectApplicantsView applicantsView;
	
	public void populate(Developer developer, Project project, ProjectApplicantsView applicantsView) {
		
		this.developer = developer;
		this.project = project;
		this.applicantsView = applicantsView;
		
		devName.setText(developer.getName());
		if(project.getPendingOfferId() != null || project.getStatus() != Project.HIRING) {
			sendOffer.setDisable(true);
		}
	}
	
	public void sendOffer() {
		
		sendOffer.setDisable(true);
		
		HireController hireController = new HireController(new DatabaseController());
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				applicantsView.disableOfferButtons();
				hireController.sendOffer(project, developer);
				return null;
			}
		};
		
		task.setOnSucceeded(succeededEvent -> {
        	Window primaryWindow = devName.getScene().getWindow();
     		Toast toast = Toast.buildToast();
     		toast.makeText(primaryWindow, "Offer Sent");
        });
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
	
	public void viewProfile(ActionEvent event) {
		if (event.getSource() == viewProfile) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.DEV_PROFILE_SCREEN));
				Node devProfileNode = fxmlLoader.load();
				DevProfile devProfileView = fxmlLoader.<DevProfile>getController();
				devProfileView.populate(developer);
				BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
				borderPane.setCenter(devProfileNode);
			} 
			catch (IOException e) {
				Log.logger.log(Level.WARNING, e.getMessage());
			}
		}
	}
	
	public void disable() {
		sendOffer.setDisable(true);
	}
}