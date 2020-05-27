package boundary;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import controller.FindProjectsController;
import controller.Log;
import controller.MyAppsController;
import entity.Project;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class DevMyApplications {
	
	@FXML
	private ScrollPane projectOffers;
	@FXML
	private ScrollPane submittedApplications;
	
	private VBox projectOffersBox;
	private VBox submittedApplicationsBox;
	
	@FXML
	private void initialize() {
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				projectOffersBox = initOffersView(getOffers());
				submittedApplicationsBox = initApplicationsView(getApplications());
				return null;
			}
		};
		task.setOnSucceeded(succeededEvent -> {
			projectOffers.setContent(projectOffersBox);
			projectOffersBox.prefHeightProperty().bind(projectOffers.widthProperty());
			projectOffersBox.prefWidthProperty().bind(projectOffers.widthProperty());
			projectOffers.setFitToHeight(true);
			
			submittedApplications.setContent(submittedApplicationsBox);
			submittedApplicationsBox.prefHeightProperty().bind(submittedApplications.widthProperty());
			submittedApplicationsBox.prefWidthProperty().bind(submittedApplications.widthProperty());
			submittedApplications.setFitToHeight(true);
         });
		task.setOnFailed(failedEvent -> {
			System.out.println(task.getException().getMessage());
		});
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
	
	private List<Project> getApplications() {
		MyAppsController controller = new MyAppsController();
		return controller.getUserApplications();
	}
	
	private List<Project> getOffers() {
		MyAppsController controller = new MyAppsController();
		return controller.getUserOffers();
	}
	
	private VBox initApplicationsView(List<Project> projects) {
		VBox vbox = new VBox();
		vbox.setSpacing(30);
		try {
			
			for(Project p : projects) {
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.SMALL_PROJECT_VIEW));
				Node projectCard = fxmlLoader.load();
				SmallProjectView smallProjectView = fxmlLoader.<SmallProjectView>getController();
				smallProjectView.populate(p);
				vbox.getChildren().add(projectCard);
			}
			Log.logger.log(Level.INFO, () -> vbox.getChildren().toString());
		}
		catch (Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
		return vbox;
	}
	
	private VBox initOffersView(List <Project> projects) {
		VBox vbox = new VBox();
		vbox.setSpacing(30);
		try {
			
			for(Project p : projects) {
				System.out.println("rrrip");
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.OFFER_VIEW));
				Node projectCard = fxmlLoader.load();
				DevOfferCard offerView = fxmlLoader.<DevOfferCard>getController();
				offerView.populate(p);
				vbox.getChildren().add(projectCard);
			}
			Log.logger.log(Level.INFO, () -> vbox.getChildren().toString());
		}
		catch (Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
		return vbox;
	}
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.DEV_MYAPPLICATIONS_SCREEN));
			borderPane.setCenter(switchScreen);
		}
		catch(Exception e){
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
}
