package boundary;

public class WindowManager {
	
	private WindowManager() {
	    throw new IllegalStateException("Utility class");
	} 

	public static final String LOGIN_SCREEN = "../Boundary/Login.fxml";
	
	public static final String DEV_NAVBAR_VIEW = "../Boundary/DevNavBar.fxml";
	public static final String DEV_FINDPROJ_SCREEN = "../Boundary/DevFindProject.fxml";
	public static final String DEV_PROFILE_SCREEN = "../Boundary/DevProfile.fxml";
	public static final String DEV_ACTIVEPROJ_SCREEN = "../Boundary/DevActiveProjects.fxml";
	
	public static final String PO_NAVBAR_VIEW = "../Boundary/PONavBar.fxml";
	public static final String PO_POSTPROJ_SCREEN = "../Boundary/POPostProject.fxml";
	public static final String PO_MYPROJECTS_SCREEN = "../Boundary/POMyProjects.fxml";
	
	public static final int WIN_WIDTH = 900;
	public static final int WIN_HEIGHT = 600;

}