package boundary;

import java.util.logging.Level;

import controller.CreateProjectController;
import controller.Log;
import controller.SessionController;
import entity.Filters.Language;
import entity.Filters.ProjectPlatform;
import entity.Project;
import entity.ProjectOwner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class POPostProject {
	
	@FXML
	private TextField projectName;
	
	@FXML
	private TextField platform;
	
	@FXML
	private TextField remote;
	
	@FXML
	private TextField duration;
	
	@FXML
	private TextField loc;
	
	@FXML
	private TextField language;
	
	@FXML
	private TextField description;
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.PO_POSTPROJ_SCREEN));
			borderPane.setCenter(switchScreen);
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void postProject() {
		ProjectOwner po = SessionController.getInstance().getProjectOwner();
		
		Project project = new Project(po.getID(), po.getName(), projectName.getText(), 
							description.getText(), duration.getText(), loc.getText());
		project.setRemote(remote.getText());
		project.setLanguage(extractLanguage());
		project.setPlatform(extractPlatform());
		
		CreateProjectController createProjectController = new CreateProjectController();
		createProjectController.pushNewProject(project);			
	}
	
	private Language extractLanguage() {
		if (language.getText().equalsIgnoreCase("python"))
			return Language.PYTHON;
		else if (language.getText().equalsIgnoreCase("java"))
			return Language.JAVA;
		else if (language.getText().equalsIgnoreCase("javascript"))
			return Language.JAVASCRIPT;
		else if (language.getText().equalsIgnoreCase("c"))
			return Language.C;
		else if (language.getText().equalsIgnoreCase("c++"))
			return Language.CPP;
		else if (language.getText().equalsIgnoreCase("swift"))
			return Language.SWIFT;
		else if (language.getText().equalsIgnoreCase("kotlin"))
			return Language.KOTLIN;
		else {
			Log.logger.log(Level.WARNING, () -> String.format("%s is not a supported language", language.getText()));
			return null;
		}
	}
	
	private ProjectPlatform extractPlatform() {
		if (platform.getText().equalsIgnoreCase("iOS"))
			return ProjectPlatform.IOS;
		else if (platform.getText().equalsIgnoreCase("Android"))
			return ProjectPlatform.ANDROID;
		else if (platform.getText().equalsIgnoreCase("Windows"))
			return ProjectPlatform.WINDOWS;
		else if (platform.getText().equalsIgnoreCase("Linux"))
			return ProjectPlatform.LINUX;
		else if (platform.getText().equalsIgnoreCase("Mac"))
			return ProjectPlatform.MAC;
		else {
			Log.logger.log(Level.WARNING, () -> String.format("%s is not a supported platform", platform.getText()));
			return null;
		}
	}
}