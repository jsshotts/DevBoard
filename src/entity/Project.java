package entity;

public class Project {
	
	public String description;
	public String projectOwnerID;
	
	public Project() {}
	
	public Project(String description, String poID) {
		this.description = description;
		this.projectOwnerID = poID;
	}
}
