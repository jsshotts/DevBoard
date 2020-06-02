
package boundary;

public class WindowManager {
	
	private WindowManager() {
	    throw new IllegalStateException("Utility class");
	} 

	public static final String CREATE_PROFILE = "boundary/fxml/CreateAccount.fxml";
	public static final String LOGIN_SCREEN = "boundary/fxml/Login.fxml";
	
	public static final String DEV_NAVBAR_VIEW = "boundary/fxml/DevNavBar.fxml";
	public static final String DEV_FINDPROJ_SCREEN = "boundary/fxml/DevFindProject.fxml";
	public static final String DEV_PROFILE_SCREEN = "boundary/fxml/DevProfile.fxml";
	public static final String DEV_ACTIVEPROJ_SCREEN = "boundary/fxml/DevActiveProjects.fxml";
	public static final String DEV_MYAPPLICATIONS_SCREEN = "boundary/fxml/DevMyApplications.fxml";
	public static final String DEV_ACTIVE_PROJECT_CARD = "boundary/fxml/DevActiveProjectCard.fxml";
	
	public static final String PO_PROFILE_SCREEN = "boundary/fxml/POProfile.fxml";
	public static final String PO_NAVBAR_VIEW = "boundary/fxml/PONavBar.fxml";
	public static final String PO_POSTPROJ_SCREEN = "boundary/fxml/POPostProject.fxml";
	public static final String PO_MYPROJECTS_SCREEN = "boundary/fxml/POMyProjects.fxml";
	public static final String PO_ACTIVE_PROJECT_CARD = "boundary/fxml/POActiveProjectCard.fxml";
	public static final String PO_OFFER_CARD = "boundary/fxml/POOfferCard.fxml";
	
	public static final String SMALL_PROJECT_VIEW = "boundary/fxml/SmallProjectView.fxml";
	public static final String LARGE_PROJECT_VIEW = "boundary/fxml/LargeProjectView.fxml";
	public static final String DEV_OFFER_VIEW = "boundary/fxml/DevOfferView.fxml";
	public static final String PROJECT_APPLICANTS_VIEW = "boundary/fxml/ProjectApplicantsView.fxml";
	public static final String PROJECT_OFFERS_VIEW = "boundary/fxml/ProjectOffersView.fxml";
	public static final String APPLICANT_CARD = "boundary/fxml/ApplicantCard.fxml";
	public static final String TOAST_CARD = "boundary/fxml/Toast.fxml";
	
	public static final String ROOT_STYLE = "boundary/css/root.css";
	public static final String NAVBAR_STYLE = "boundary/css/navBar.css";
	public static final String CARDBOX_STYLE = "boundary/css/cardBox.css";
	public static final String SMALLPROJECTVIEW_STYLE = "boundary/css/smallProjectView.css";
	public static final String BIGBUTTON_STYLE = "boundary/css/bigButton.css";
	
	public static final int WIN_WIDTH = 900;
	public static final int WIN_HEIGHT = 600;
}