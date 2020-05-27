package boundary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.HireController;
import entity.Developer;
import entity.Project;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ApplicantCard {
	
	@FXML
	private Label devName;
	
	@FXML
	private Button viewProfile;
	
	@FXML
	private Button sendOffer;
	
	private Developer developer;
	
	private Project project;
	
	public void populate(Developer developer, Project project) {
		this.developer = developer;
		this.project = project;
		devName.setText(developer.getName());
		if(project.getPendingOfferId() != null || project.getStatus() != Project.HIRING) {
			sendOffer.setDisable(true);
		}
	}
	
	public void sendOffer() {
		
		sendOffer.setDisable(true);
		
		HireController hireController = new HireController();
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				hireController.sendOffer(project, developer);
				return null;
			}
		};
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
}