package boundary;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import controller.CreateProjectController;
import controller.Log;
import controller.SessionController;
import entity.Filters.Language;
import entity.Filters.ProjectPlatform;
import entity.Project;
import entity.ProjectOwner;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

public class POPostProject {
	
	@FXML
	private TextField projectName;	
	@FXML
	private TextField duration;	
	@FXML
	private TextField loc;
	@FXML
	private TextArea description;
	
	@FXML
	private MenuButton platformMenu;
	@FXML
	private MenuItem iosItem;
	@FXML
	private MenuItem androidItem;
	@FXML
	private MenuItem windowsItem;
	@FXML
	private MenuItem linuxItem;
	@FXML
	private MenuItem macItem;
	
	@FXML
	private MenuButton remoteMenu;
	@FXML
	private MenuItem yesItem;
	@FXML
	private MenuItem noItem;
	
	@FXML
	private MenuButton langMenu;
	@FXML
	private MenuItem pythonItem;
	@FXML
	private MenuItem javaItem;
	@FXML
	private MenuItem cItem;
	@FXML
	private MenuItem cppItem;
	@FXML
	private MenuItem jsItem;
	@FXML
	private MenuItem swiftItem;
	@FXML
	private MenuItem kotlinItem;
	
	private static final String REMOTE_LABEL = "Remote";
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.PO_POSTPROJ_SCREEN));
			borderPane.setCenter(switchScreen);
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	public void setPlatform(ActionEvent event) {
		if(event.getSource() == iosItem) {
			platformMenu.setText("Platform: iOS");
		}
		else if(event.getSource() == androidItem) {
			platformMenu.setText("Platform: Android");
		}
		else if(event.getSource() == windowsItem) {
			platformMenu.setText("Platform: Windows");
		}
		else if(event.getSource() == linuxItem) {
			platformMenu.setText("Platform: Linux");
		}
		else if(event.getSource() == macItem) {
			platformMenu.setText("Platform: Mac");
		}
	}
	
	public void setLanguage(ActionEvent event) {
		if(event.getSource() == pythonItem) {
			langMenu.setText("Language: Python");
		}
		else if(event.getSource() == javaItem) {
			langMenu.setText("Language: Java");
		}
		else if(event.getSource() == cItem) {
			langMenu.setText("Language: C");
		}
		else if(event.getSource() == cppItem) {
			langMenu.setText("Language: C++");
		}
		else if(event.getSource() == jsItem) {
			langMenu.setText("Language: Javascript");
		}
		else if(event.getSource() == swiftItem) {
			langMenu.setText("Language: Swift");
		}
		else if(event.getSource() == kotlinItem) {
			langMenu.setText("Language: Kotlin");
		}
	}
	
	public void setRemote(ActionEvent event) {
		if(event.getSource() == yesItem) {
			remoteMenu.setText("Remote: Yes");
		}
		else if(event.getSource() == noItem) {
			remoteMenu.setText("Remote: No");
		}
	}
	
	public void postProject() {
		
		if(projectName.getText().length() == 0 || platformMenu.getText().equals("Platform") || remoteMenu.getText().equals(REMOTE_LABEL)
				|| duration.getText().length() == 0 || loc.getText().length() == 0
				|| langMenu.getText().equals("Language") || description.getText().length() == 0) {
			Window primaryWindow = projectName.getScene().getWindow();
     		Toast toast = Toast.buildToast();
     		toast.makeText(primaryWindow, "All Fields Are Required");
			return;
		}
		
		SessionController.getInstance().highlightPoNavBar();
		ProjectOwner po = SessionController.getInstance().getProjectOwner();
		
		Project project = new Project(po.getID(), po.getName(), projectName.getText(), 
							description.getText(), duration.getText(), loc.getText());
		project.setRemote(getRemote());
		project.setLanguage(getLanguage());
		project.setPlatform(getPlatform());
		project.setStartDate(LocalDate.of(2020, 6, 17));
		project.setEndDate(LocalDate.of(2020, 7, 15));
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				CreateProjectController createProjectController = new CreateProjectController();
				createProjectController.pushNewProject(project);
				return null;
			}
		};
		
		task.setOnSucceeded(succeededEvent -> {
        	Window primaryWindow = projectName.getScene().getWindow();
     		Toast toast = Toast.buildToast();
     		toast.makeText(primaryWindow, "Project Created");
     		projectName.clear();
     		platformMenu.setText("Platform");
     		remoteMenu.setText(REMOTE_LABEL);
     		duration.clear();
     		loc.clear();
     		langMenu.setText("Language");
     		description.clear();
        });
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
	
	private Language getLanguage() {
		if (langMenu.getText().equalsIgnoreCase("Language: python"))
			return Language.PYTHON;
		else if (langMenu.getText().equalsIgnoreCase("Language: java"))
			return Language.JAVA;
		else if (langMenu.getText().equalsIgnoreCase("Language: javascript"))
			return Language.JAVASCRIPT;
		else if (langMenu.getText().equalsIgnoreCase("Language: c"))
			return Language.C;
		else if (langMenu.getText().equalsIgnoreCase("Language: c++"))
			return Language.CPP;
		else if (langMenu.getText().equalsIgnoreCase("Language: swift"))
			return Language.SWIFT;
		else if (langMenu.getText().equalsIgnoreCase("Language: kotlin"))
			return Language.KOTLIN;
		else {
			Log.logger.log(Level.WARNING, () -> String.format("%s is not a supported language", langMenu.getText()));
			return Language.JAVA;
		}
	}
	
	private String getRemote() {
		if (remoteMenu.getText().equalsIgnoreCase("Yes")) {
			return "yes";
		}			
		else if (remoteMenu.getText().equalsIgnoreCase("No")) {
			return "no";
		}
		return REMOTE_LABEL;
	}
	
	private ProjectPlatform getPlatform() {
		if (platformMenu.getText().equalsIgnoreCase("Platform: iOS"))
			return ProjectPlatform.IOS;
		else if (platformMenu.getText().equalsIgnoreCase("Platform: Android"))
			return ProjectPlatform.ANDROID;
		else if (platformMenu.getText().equalsIgnoreCase("Platform: Windows"))
			return ProjectPlatform.WINDOWS;
		else if (platformMenu.getText().equalsIgnoreCase("Platform: Linux"))
			return ProjectPlatform.LINUX;
		else if (platformMenu.getText().equalsIgnoreCase("Platform: Mac"))
			return ProjectPlatform.MAC;
		else {
			Log.logger.log(Level.WARNING, () -> String.format("%s is not a supported platform", platformMenu.getText()));
			return ProjectPlatform.MAC;
		}
	}
}