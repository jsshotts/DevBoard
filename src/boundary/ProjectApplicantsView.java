package boundary;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import controller.Log;
import entity.Project;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ProjectApplicantsView {

	@FXML
	private ScrollPane scrollPane;
	
	private VBox applicantViewBox;
	
	@FXML
	private void initialize() {
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				applicantViewBox = initApplicantsView();
				return null;
			}
		};
		
		task.setOnSucceeded(succeededEvent -> {
			 scrollPane.setContent(applicantViewBox);
 			 scrollPane.setFitToHeight(true);
 			 scrollPane.setFitToWidth(true);
         });
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
	
	private VBox initApplicantsView() {
		VBox vbox = new VBox();
		vbox.setSpacing(30);
		try {
			
			for(int i = 0; i < 5; i++) {
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.APPLICANT_CARD));
				Node applicantCard = fxmlLoader.load();
				//SmallProjectView smallProjectView = fxmlLoader.<SmallProjectView>getController();
				//smallProjectView.populate(p);
				vbox.getChildren().add(applicantCard);
			}
			//Log.logger.log(Level.INFO, () -> vbox.getChildren().toString());
		}
		catch (Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
		return vbox;
	}
}
