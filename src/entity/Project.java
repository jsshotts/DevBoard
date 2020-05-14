package entity;

import entity.Filters.Language;
import entity.Filters.ProjectPlatform;

public class Project {
	
	public String projectOwnerID;
	public String description, title, location, duration;
	public Language language;
	public ProjectPlatform platform;
	public boolean remote;
	
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
		this.language = Language.Python;
		this.platform = ProjectPlatform.Linux;
	}
}