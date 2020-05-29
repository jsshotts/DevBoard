package boundary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.ApplyController;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;

public class LargeProjectView extends ProjectView{
	
	@FXML
	private Button apply;
	
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
         });
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
}