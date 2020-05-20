package entity;

import java.util.LinkedList;
import java.util.List;

public class ProjectOwner extends User{
	
	private List<Project> projects;
	
	public ProjectOwner(String name, String bio) {
		super(name,  bio, null);
		projects = new LinkedList<>();
	}
	
	public ProjectOwner(String name, String bio, String email) {
		super(name,  bio, email);
		projects = new LinkedList<>();
	}
	
	public void addProject(Project proj) {
		if(projects == null) {
			projects = new LinkedList<Project>();
		}
		projects.add(proj);
	}
	
	public void removeProject(Project proj) {
		if(projects != null) {
			projects.remove(proj);
		}
	}
}