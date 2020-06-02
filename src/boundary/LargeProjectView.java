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
import javafx.stage.Window;

public class LargeProjectView extends ProjectView {
	
	private static final String FEEDBACK = "Application Sent";
	
	@FXML
	private Button apply;
	@FXML
	public DatePicker startDatePicker;
	@FXML
	public DatePicker endDatePicker;
	
	@Override
	public void populate(Project project) {
		super.populate(project);
		
		startDatePicker.setValue(project.getStartDate());
		endDatePicker.setValue(project.getEndDate());
		
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
	
	@FXML
	public void back(ActionEvent event) {
		SessionController session = SessionController.getInstance();
		String prev = session.getPrevWindow();
		
		session.back(event);
		
		if (prev.equals(WindowManager.DEV_FINDPROJ_SCREEN))
			session.getDevNavBar().findProjectsButton.requestFocus();
		if (prev.equals(WindowManager.DEV_MYAPPLICATIONS_SCREEN))
			session.getDevNavBar().myApplicationsButton.requestFocus();
		if (prev.equals(WindowManager.DEV_PROFILE_SCREEN))
			session.getDevNavBar().profileButton.requestFocus();
	}
}