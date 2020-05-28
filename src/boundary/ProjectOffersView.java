package boundary;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import controller.HireController;
import controller.Log;
import entity.Developer;
import entity.Offer;
import entity.Project;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ProjectOffersView {
	
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private VBox acceptedOfferBox;
	
	@FXML
	private VBox pendingOfferBox;
	
	@FXML
	private VBox declinedOfferBox;
	
	private static final int CHILD_HEIGHT = 50;
	
	private static final int DEFAULT_HEIGHT = 150;
	
	private int childCount = 0;
	
	private HireController hireController = new HireController();
	
	private List<Node> accepted = new LinkedList<>();
	
	private List<Node> declined = new LinkedList<>();
	
	private List<Node> pending = new LinkedList<>();
	
	@FXML
	public void initialize() {
		scrollPane.setMinHeight(DEFAULT_HEIGHT);
	}
	
	public void initOfferView(Project project) {
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				initOfferCards(getOfferMap(project));
				return null;
			}
		};
		
		task.setOnSucceeded(succeededEvent -> {
			updateOfferBoxes();
			scrollPane.setMinHeight(getScrollPaneHeight());
         });
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
	
	public int getScrollPaneHeight() {
		return (childCount * CHILD_HEIGHT) + DEFAULT_HEIGHT;
	}
	
	public Map<Developer, Offer> getOfferMap(Project project){
		return hireController.getProjectOfferMap(project);
	}
	
	public void initOfferCards(Map<Developer, Offer> map) {
		
		try {
			
			for(Map.Entry<Developer, Offer> entry : map.entrySet()) {
				
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.PO_OFFER_CARD));
				Node offerCard = fxmlLoader.load();
				POOfferCard offerCardController = fxmlLoader.<POOfferCard>getController();
				
				offerCardController.populate(entry.getKey(), entry.getValue());
				
				if(entry.getValue().getStatus() == Offer.ACCEPTED) {
					accepted.add(offerCard);
				}
				else if(entry.getValue().getStatus() == Offer.PENDING) {
					pending.add(offerCard);
				}
				if(entry.getValue().getStatus() == Offer.DECLINED) {
					declined.add(offerCard);
				}
			}
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	public void updateOfferBoxes() {
		for(Node node : accepted) {
			acceptedOfferBox.getChildren().add(node);
		}
		for(Node node : declined) {
			declinedOfferBox.getChildren().add(node);
		}
		for(Node node : pending) {
			pendingOfferBox.getChildren().add(node);
		}
	}
}