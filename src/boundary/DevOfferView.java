package boundary;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.FindProjectsController;
import entity.Offer;
import entity.Project;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;

public class DevOfferView {
	
	@FXML
	private Label projectTitle;
	
	@FXML
	private Label shortDescription;
	
	@FXML
	private Label platform;
	
	@FXML
	private Label projectLocation;
	
	@FXML 
	private Label duration;
	
	@FXML
	private Label remote;
	
	@FXML
	private Label language;
	
	@FXML
	private Label bigDescription;
	
	@FXML
	private Label offerStatus;
	
	@FXML
	private Label projectOwnerName;
	
	@FXML
	private Button acceptOfferButton;
	
	@FXML
	private Button declineOfferButton;
	
	@FXML
	private Button viewProjectDetailsButton;
	
	private UUID offerId;
	
	public void populate(Project project, Offer offer) {
		
		this.offerId = offer.getId();
		
		projectTitle.setText(project.getTitle());
		bigDescription.setText(project.getDescription());
		language.setText(project.getLanguage().getString());
		remote.setText(project.getRemote());
		platform.setText(project.getPlatform().getString());
		projectLocation.setText(project.getLocation());
		offerStatus.setText(offer.getStatusString());
		projectOwnerName.setText(project.getProjectOwnerName());
		
		if(offer.getStatus() != Offer.PENDING) {
			acceptOfferButton.setDisable(true);
			declineOfferButton.setDisable(true);
		}
	}
	
	public void acceptOffer() {
		closeOffer(Offer.ACCEPTED);
		acceptOfferButton.setDisable(true);
		declineOfferButton.setDisable(true);
	}
	
	public void declineOffer() {
		closeOffer(Offer.DECLINED);
		acceptOfferButton.setDisable(true);
		declineOfferButton.setDisable(true);
	}
	
	public void closeOffer(int offerStatus) {
		
		Task<Void> task = new Task<Void>() {
	        @Override
	        protected Void call() throws Exception {
	        	
	        	FindProjectsController findProjectsController = new FindProjectsController();
	    		findProjectsController.closeOffer(offerId, offerStatus);
	    		
	            return null;
	        }
	    };
	    
	    task.setOnSucceeded(succeededEvent -> {
        	Window primaryWindow = projectTitle.getScene().getWindow();				
     		Toast toast = Toast.buildToast();
     		if(offerStatus == Offer.ACCEPTED) {
     			toast.makeText(primaryWindow, "Offer Accepted");
     		}
     		else {
     			toast.makeText(primaryWindow, "Offer Declined");
     		}
         });
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
	
	public void test() {
		
		Window primaryWindow = projectTitle.getScene().getWindow();				
		Toast toast = Toast.buildToast();
		toast.makeText(primaryWindow, "Test Toast");
	}
}
