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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ProjectApplicantsView {

	@FXML
	private ScrollPane scrollPane;
	
	private VBox applicantViewBox;
	
	private int childCount = 0;
	
	private static int childSize = 52;
	
	private HireController hireController = new HireController();
	
	public void initialize(Project project) {
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				applicantViewBox = initApplicantsView(project, getApplicants(project));
				return null;
			}
		};
		
		task.setOnSucceeded(succeededEvent -> {
			 scrollPane.setContent(applicantViewBox);
			 scrollPane.setMinViewportHeight(calculateScrollPaneHeight()); 	
         });
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
	
	private VBox initApplicantsView(Project project, List<Developer> applicants) {
		
		VBox vbox = new VBox();
		vbox.setSpacing(30);
		
		try {
			for(Developer developer : applicants) {
				
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.APPLICANT_CARD));
				Node applicantCard = fxmlLoader.load();
				ApplicantCard applicantController = fxmlLoader.<ApplicantCard>getController();
				
				applicantController.populate(developer, project);
				
				vbox.getChildren().add(applicantCard);
			}
		}
		catch (Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
		childCount = applicants.size();
		return vbox;
	}
	
	public List<Developer> getApplicants(Project project) {
		return hireController.getProjectApplicants(project);
	}
	
	public int calculateScrollPaneHeight() {
		int height = childCount * childSize;
		if(height > 500) {
			height = 500;
		}
		return height;
	}
}
