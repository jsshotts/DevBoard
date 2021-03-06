package boundary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.ApplyController;
import controller.SessionController;
import entity.Developer;
import entity.Project;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Window;

public class LargeProjectView extends ProjectView {
	
	private static final String FEEDBACK = "Application Sent";
	
	@FXML
	private Button apply;
	@FXML
	private Label language;
	@FXML
	public DatePicker startDatePicker;
	@FXML
	public DatePicker endDatePicker;
	
	@Override
	public void populate(Project project) {
		super.populate(project);
		
		startDatePicker.setValue(project.getStartDate());
		endDatePicker.setValue(project.getEndDate());
		language.setText(project.getLanguage().getString());
		
		Developer developer = SessionController.getInstance().getDeveloper();
		if(developer != null && developer.getAppliedProjectIds().contains(this.project.getID())) {
			apply.setText(FEEDBACK);
			apply.setDisable(true);
		}
	}
	
	public void apply() {
		
		Task<Void> task = new Task<Void>() {
	        @Override
	        protected Void call() throws Exception {
	        	ApplyController applyController = new ApplyController();
	    		applyController.apply(getProject());
	            return null;
	        }
	    };

         task.setOnSucceeded(succeededEvent -> {
        	Window primaryWindow = apply.getScene().getWindow();				
     		Toast toast = Toast.buildToast();
     		toast.makeText(primaryWindow, FEEDBACK);
     		apply.setText(FEEDBACK);
			apply.setDisable(true);
         });
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
	
	public void back(ActionEvent event) {
		SessionController session = SessionController.getInstance();
		session.back(event);
		session.highlightDevNavBar();
	}
}