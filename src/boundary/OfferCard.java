package boundary;

import entity.Developer;
import entity.Offer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OfferCard {
	
	@FXML
	private Label name;
	
	@FXML
	private Label status;
	
	public void populate(Developer dev, Offer offer) {
		name.setText(dev.getName());
		status.setText(offer.getStatusString());
	}
}
