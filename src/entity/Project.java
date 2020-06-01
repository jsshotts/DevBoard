package entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;

import controller.Log;
import entity.Filters.Language;
import entity.Filters.ProjectPlatform;

public class Project {
	
	public static final int HIRING = 0;
	public static final int IN_PROGRESS = 1;
	public static final int CLOSED = 2;
	
	private UUID id;
	private UUID projectOwnerID;
	private List<UUID> appliedDeveloperIDs = new LinkedList<>();
	private UUID pendingOfferId;
	private List<UUID> closedOfferIds = new LinkedList<>();
	private String projectOwnerName;
	private int status;
	private String description;
	private String title;
	private String location;
	private String duration;
	private Language language;
	private ProjectPlatform platform;
	private boolean remote;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Project(String title, UUID poID, String poName) {
		this.title = title;
		this.projectOwnerID = poID;
		this.id = UUID.randomUUID();
		this.setLanguage(null);
		
		//for now, set dummy data for testing
		this.setTestData();
		this.projectOwnerName = "";
		this.status = HIRING;
		this.projectOwnerName = poName;
	}
	
	public Project(UUID poId, String poName, String title, 
			String description, String duration, String location)
	{
		this.id = UUID.randomUUID();
		this.projectOwnerID = poId;
		this.projectOwnerName = poName;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.location = location;
	}
	
	public void setTestData() {
		Random random = new Random();
		this.location = "San Luis Obispo";
		this.duration = (Math.abs(random.nextInt()) % 10) + 1 + " weeks";
		this.setLanguage(Language.PYTHON);
		this.platform = ProjectPlatform.LINUX;
		this.description = "This is a " + this.title + ". Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";
		this.remote = random.nextInt() % 2 == 0;
		this.startDate = LocalDate.of(2020, 6, 17);
		this.endDate = LocalDate.of(2020, 7, 15);
	}
	
	public void setData(String location, Language language, ProjectPlatform platform) {
		this.location = location;
		this.language = language;
		this.platform = platform;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public ProjectPlatform getPlatform() {
		return platform;
	}
	
	public String getRemote() {
		if(remote) {
			return "Yes";
		}
		return "No";
	}
	
	public UUID getID() {
		return this.id;
	}

	public void setPendingOfferId(UUID offerId) {
		this.pendingOfferId = offerId;
	}
	
	public UUID getPendingOfferId() {
		return this.pendingOfferId;
	}
	
	public void addClosedOfferId(UUID offerId) {
		if(closedOfferIds == null) {
			closedOfferIds = new LinkedList<>();
		}
		closedOfferIds.add(offerId);
	}
	
	public List<UUID> getClosedOfferIds(){
		if(closedOfferIds == null) {
			return new LinkedList<>();
		}
		return closedOfferIds;
	}
	
	public List<UUID> getAllOfferIds(){
		List<UUID> allOfferIds = getClosedOfferIds();
		if(pendingOfferId != null) {
			allOfferIds.add(pendingOfferId);
		}
		return allOfferIds;
	}
	
	public void removeClosedOfferId(UUID offerId) {
		if(closedOfferIds != null) {
			closedOfferIds.remove(offerId);
		}
	}

	public String getProjectOwnerName() {
		return projectOwnerName;
	}
	
	public int getStatus() {
		return status;
	}
	
	public String getStatusString() {
		if(this.status == HIRING) {
			return "Hiring";
		}
		else if(this.status == IN_PROGRESS) {
			return "In Progress";
		}
		else {
			return "Closed";
		}
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public UUID getProjectOwnerId() {
		return this.projectOwnerID;
	}
	
	public List<UUID> getAppliedDeveloperIDs(){
		if(appliedDeveloperIDs == null) {
			appliedDeveloperIDs = new LinkedList<>();
		}
		return this.appliedDeveloperIDs;
	}
	
	public void addAppliedDeveloperID(UUID devId){
		if(appliedDeveloperIDs == null) {
			appliedDeveloperIDs = new LinkedList<>();
		}
		appliedDeveloperIDs.add(devId);
	}
	
	public void removeAppliedDeveloperIDs(UUID devId) {
		if(appliedDeveloperIDs != null) {
			appliedDeveloperIDs.remove(devId);
		}
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setRemote(String text) {
		if (text.equalsIgnoreCase("true") || text.equalsIgnoreCase("t") ||
				text.equalsIgnoreCase("yes") || text.equalsIgnoreCase("y"))
			remote = true;
		else if (text.equalsIgnoreCase("false") || text.equalsIgnoreCase("f") ||
				text.equalsIgnoreCase("no") || text.equalsIgnoreCase("n"))
			remote = false;
		else
			Log.logger.log(Level.WARNING, "remote must be either true, fale, yes, no, t, f, y, or n");
	}

	public void setPlatform(ProjectPlatform platform) {
		this.platform = platform;
	}
	
	public LocalDate getStartDate() {
		return this.startDate;
	}
	
	public void setStartDate(LocalDate start) {
		this.startDate = start;
	}
	
	public LocalDate getEndDate() {
		return this.endDate;
	}
	
	public void setEndDate(LocalDate start) {
		this.endDate = start;
	}
}