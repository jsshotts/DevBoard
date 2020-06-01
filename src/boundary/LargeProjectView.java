package boundary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.ApplyController;
import controller.SessionController;
import entity.Developer;
import entity.Project;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;

public class LargeProjectView extends ProjectView{
	
	@FXML
	private Button apply;
	
	@Override
	public void populate(Project project) {
		super.populate(project);
		Developer developer = SessionController.getInstance().getDeveloper();
		if(developer != null && developer.getAppliedProjectIds().contains(this.project.getID())) {
			apply.setText("Application Sent");
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
     		toast.makeText(primaryWindow, "Application Sent");
     		apply.setText("Application Sent");
			apply.setDisable(true);
         });
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
}