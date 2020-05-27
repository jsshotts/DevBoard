package entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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
	
	public Project(String title, UUID poID) {
		this.title = title;
		this.projectOwnerID = poID;
		this.id = UUID.randomUUID();
		this.setLanguage(null);
		//for now, set dummy data for testing
		this.setTestData();
		this.projectOwnerName = "";
		this.status = HIRING;
	}
	
	public Project(String description, UUID poID, String poName) {
		this.description = description;
		this.projectOwnerID = poID;
		this.id = UUID.randomUUID();
		//for now, set dummy data for testing
		this.setTestData();
		this.projectOwnerName = poName;
		this.status = HIRING;
	}
	
	public void setTestData() {
		this.location = "San Luis Obispo";
		this.duration = "5 weeks";
		this.setLanguage(Language.PYTHON);
		this.platform = ProjectPlatform.LINUX;
		this.description = "This is a " + this.title + ". Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";
		this.remote = new Random().nextInt() % 2 == 0;
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
	
	public String getPlatform() {
		return platform.getString();
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
		int test = 0;
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
}