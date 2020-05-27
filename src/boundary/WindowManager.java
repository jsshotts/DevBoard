
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
	public static final String DEV_MYAPPLICATIONS_SCREEN = "boundary/DevMyApplications.fxml";
	public static final String DEV_ACTIVE_PROJECT_CARD = "boundary/DevActiveProjectCard.fxml";
	
	public static final String PO_PROFILE_SCREEN = "boundary/POProfile.fxml";
	public static final String PO_NAVBAR_VIEW = "boundary/PONavBar.fxml";
	public static final String PO_POSTPROJ_SCREEN = "boundary/POPostProject.fxml";
	public static final String PO_MYPROJECTS_SCREEN = "boundary/POMyProjects.fxml";
	public static final String PO_ACTIVE_PROJECT_CARD = "boundary/POActiveProjectCard.fxml";
	public static final String PO_OFFER_CARD = "boundary/POOfferCard.fxml";
	
	public static final String SMALL_PROJECT_VIEW = "boundary/SmallProjectView.fxml";
	public static final String LARGE_PROJECT_VIEW = "boundary/LargeProjectView.fxml";
	public static final String OFFER_VIEW = "boundary/OfferView.fxml";
	public static final String PROJECT_APPLICANTS_VIEW = "boundary/ProjectApplicantsView.fxml";
	public static final String PROJECT_OFFERS_VIEW = "boundary/ProjectOffersView.fxml";
	public static final String APPLICANT_CARD = "boundary/ApplicantCard.fxml";
	
	public static final int WIN_WIDTH = 900;
	public static final int WIN_HEIGHT = 600;

}