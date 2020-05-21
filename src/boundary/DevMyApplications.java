package boundary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import controller.Log;
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
	VBox projectOffersBox;
	VBox submittedApplicationsBox;
	
	@FXML
	private void initialize() {
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				projectOffersBox = initScrollView(WindowManager.OFFER_VIEW);
				submittedApplicationsBox = initScrollView(WindowManager.SMALL_PROJECT_VIEW);
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
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
	
	private VBox initScrollView(String resource) {
		VBox vbox = new VBox();
		try {
			
			for(int i = 0; i < 10; i++) {
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(resource));
				Node projectCard = fxmlLoader.load();
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
