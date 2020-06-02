package boundary;

import java.util.logging.Level;

import controller.Log;
import controller.SessionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class SmallProjectView extends ProjectView{
	
	public void moreDetails(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.LARGE_PROJECT_VIEW));
			Node largeProjectNode = fxmlLoader.load();
			LargeProjectView largeProjectView = fxmlLoader.<LargeProjectView>getController();
			largeProjectView.populate(getProject());
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			borderPane.setCenter(largeProjectNode);
		}
		catch (Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
		highlightNav();
	}
	public void highlightNav() {
		SessionController session = SessionController.getInstance();
		String prev = session.getPrevWindow();
		
		if (prev.equals(WindowManager.DEV_FINDPROJ_SCREEN))
			session.getDevNavBar().findProjectsButton.requestFocus();
		if (prev.equals(WindowManager.DEV_MYAPPLICATIONS_SCREEN))
			session.getDevNavBar().myApplicationsButton.requestFocus();
		if (prev.equals(WindowManager.DEV_PROFILE_SCREEN))
			session.getDevNavBar().profileButton.requestFocus();
	}
}