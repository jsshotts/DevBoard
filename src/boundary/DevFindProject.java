package boundary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.stream.Collectors;

import controller.DatabaseController;
import controller.FindProjectsController;
import controller.Log;
import controller.SessionController;
import entity.Filters.Language;
import entity.Filters.ProjectPlatform;
import entity.Project;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class DevFindProject {
	
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private CheckBox langPython;
	
	@FXML
	private CheckBox langJava;
	
	@FXML
	private CheckBox langC;
	
	@FXML
	private CheckBox langCPP;
	
	@FXML
	private CheckBox langSwift;
	
	@FXML
	private CheckBox langKotlin;
	
	@FXML
	private CheckBox langJavascript;
	
	@FXML
	private CheckBox platIOS;
	
	@FXML
	private CheckBox platAndroid;
	
	@FXML
	private CheckBox platWindows;
	
	@FXML
	private CheckBox platLinux;
	
	@FXML
	private CheckBox platMacOS;
	
	@FXML
	private CheckBox remoteYes;
	
	@FXML
	private CheckBox remoteNo;
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.DEV_FINDPROJ_SCREEN));
			borderPane.setCenter(switchScreen);
		}
		catch(Exception e){
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	private Collection<Project> getProjects(){
		FindProjectsController controller = new FindProjectsController(new DatabaseController());
		return controller.getAllProjects().values();
	}
	
	@FXML
	private void initialize() {
		initializeGrid(getProjects());
	}
	
	private void initializeGrid(Collection<Project> projects) {
		
		GridPane gridPane = new GridPane();
		
		Task<Void> task = new Task<Void>() {
	        @Override
	        protected Void call() throws Exception {
	            initializeGridHelper(projects, gridPane);
	            return null;
	        }
	    };

         task.setOnSucceeded(succeededEvent -> {
        	 scrollPane.setContent(gridPane);
 			 gridPane.prefWidthProperty().bind(scrollPane.widthProperty());
 			 scrollPane.setFitToHeight(true);
 			 scrollPane.setFitToWidth(true);
         });
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
        executorService.shutdown();
	}
	
	private void initializeGridHelper(Collection<Project> projects, GridPane gridPane) {
		
		gridPane.addColumn(0);
		gridPane.addColumn(1);
		gridPane.addColumn(2);
		gridPane.addColumn(3);
		gridPane.addColumn(4);
		
		try {	
			int i = 0;
			for(Project p : projects) {
				FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.SMALL_PROJECT_VIEW));
				Node projectCard = fxmlLoader.load();
				SmallProjectView smallProjectView = fxmlLoader.<SmallProjectView>getController();
				smallProjectView.populate(p);
				gridPane.add(projectCard, (i%2)*2+1,i/2);
				i++;
			}
			
			gridPane.setHgap(20);
			gridPane.setVgap(100);
			
			ColumnConstraints column1 = new ColumnConstraints();
			ColumnConstraints column2 = new ColumnConstraints();
			ColumnConstraints column3 = new ColumnConstraints();
			ColumnConstraints column4 = new ColumnConstraints(); 
			ColumnConstraints column5 = new ColumnConstraints();
			
			column1.setHgrow(Priority.ALWAYS);
			column3.setHgrow(Priority.ALWAYS);
			column5.setHgrow(Priority.ALWAYS);
			gridPane.getColumnConstraints().addAll(column1,column2, column3, column4, column5);
		}
		catch (Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	public void search() {
		
		SessionController.getInstance().highlightDevNavBar();
		
		ArrayList<Language> langFilters = new ArrayList<>();
		if (langPython.isSelected()) 
			langFilters.add(Language.PYTHON);
		if (langJava.isSelected()) 
			langFilters.add(Language.JAVA);
		if (langC.isSelected()) 
			langFilters.add(Language.C);
		if (langCPP.isSelected()) 
			langFilters.add(Language.CPP);
		if (langSwift.isSelected()) 
			langFilters.add(Language.SWIFT);
		if (langKotlin.isSelected()) 
			langFilters.add(Language.KOTLIN);
		if (langJavascript.isSelected()) 
			langFilters.add(Language.JAVASCRIPT);
		
		ArrayList<ProjectPlatform> platFilters = new ArrayList<>();
		if (platIOS.isSelected()) 
			platFilters.add(ProjectPlatform.IOS);
		if (platAndroid.isSelected()) 
			platFilters.add(ProjectPlatform.ANDROID);
		if (platWindows.isSelected()) 
			platFilters.add(ProjectPlatform.WINDOWS);
		if (platLinux.isSelected()) 
			platFilters.add(ProjectPlatform.LINUX);
		if (platMacOS.isSelected()) 
			platFilters.add(ProjectPlatform.MAC);
		
		ArrayList<String> remoteFilters = new ArrayList<>();
		if (remoteYes.isSelected()) 
			remoteFilters.add("Yes");
		if (remoteNo.isSelected()) 
			remoteFilters.add("No");
		
		//all projects must be get every time
		Collection<Project> projects = getProjects();
		projects = projects.stream().filter(p -> langFilters.contains(p.getLanguage()))
						 			.filter(p -> platFilters.contains(p.getPlatform()))
						 			.filter(p -> remoteFilters.contains(p.getRemote()))
						 			.collect(Collectors.toCollection(ArrayList::new));
		
		initializeGrid(projects);
	}
}