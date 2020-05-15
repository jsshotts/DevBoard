package entity;

import entity.Filters.Language;
import entity.Filters.ProjectPlatform;

public class Project {
	
	private String projectOwnerID;
	private String description;
	private String title;
	private String location;
	private String duration;
	private Language language;
	private ProjectPlatform platform;
	private boolean remote;
	
	public Project() {}
	
	public Project(String description, String poID) {
		this.description = description;
		this.projectOwnerID = poID;
		//for now, set dummy data for testing
		this.setTestData();
	}
	
	public void setTestData() {
		this.title = "Sample Project Title";
		this.location = "San Luis Obispo";
		this.duration = "5 weeks";
		this.language = Language.PYTHON;
		this.platform = ProjectPlatform.LINUX;
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
}