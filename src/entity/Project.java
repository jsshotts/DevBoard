package entity;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import entity.Filters.Language;
import entity.Filters.ProjectPlatform;

public class Project {
	
	private UUID id;
	private UUID projectOwnerID;
	private List<UUID> appliedDeveloperIDs;
	private String projectOwnerName;
	private String status;
	private String description;
	private String title;
	private String location;
	private String duration;
	private Language language;
	private ProjectPlatform platform;
	private boolean remote;
	
	public Project() {}
	
	public Project(String description, UUID poID) {
		this.description = description;
		this.projectOwnerID = poID;
		this.id = UUID.randomUUID();
		//for now, set dummy data for testing
		this.setTestData();
		this.projectOwnerName = "";
		this.status = "Hiring";
	}
	
	public Project(String description, UUID poID, String poName) {
		this.description = description;
		this.projectOwnerID = poID;
		this.id = UUID.randomUUID();
		//for now, set dummy data for testing
		this.setTestData();
		this.projectOwnerName = poName;
		this.status = "Hiring";
	}
	
	public void setTestData() {
		this.title = "Sample Project Title";
		this.location = "San Luis Obispo";
		this.duration = "5 weeks";
		this.language = Language.PYTHON;
		this.platform = ProjectPlatform.LINUX;
		this.description += ". Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";
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
	
	public String getProjectOwnerName() {
		return projectOwnerName;
	}
	
	public String getStatus() {
		return status;
	}
}