package boundary;

import controller.ApplyController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LargeProjectView extends ProjectView{
	
	@FXML
	private Button apply;
	
	public void apply() { 
		ApplyController applyController = new ApplyController();
		applyController.apply(getProject());
	}

}