package boundary;

public class WindowManager {
	
	private WindowManager() {
	    throw new IllegalStateException("Utility class");
	} 

	public static final String LOGIN_SCREEN = "boundary/Login.fxml";
	
	public static final String DEV_NAVBAR_VIEW = "boundary/DevNavBar.fxml";
	public static final String DEV_FINDPROJ_SCREEN = "boundary/DevFindProject.fxml";
	public static final String DEV_PROFILE_SCREEN = "boundary/DevProfile.fxml";
	public static final String DEV_ACTIVEPROJ_SCREEN = "boundary/DevActiveProjects.fxml";
	
	public static final String PO_NAVBAR_VIEW = "boundary/PONavBar.fxml";
	public static final String PO_POSTPROJ_SCREEN = "boundary/POPostProject.fxml";
	public static final String PO_MYPROJECTS_SCREEN = "boundary/POMyProjects.fxml";
	
	public static final int WIN_WIDTH = 900;
	public static final int WIN_HEIGHT = 600;

}