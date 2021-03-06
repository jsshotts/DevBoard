package entity;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ProjectOwner extends User{
	
	private List<UUID> projectIds = new LinkedList<>();
	
	public ProjectOwner(String name, String bio) {
		super(name,  bio, null, null);
		projectIds = new LinkedList<>();
	}
	
	public ProjectOwner(String name, String bio, String email) {
		super(name,  bio, email, null);
		projectIds = new LinkedList<>();
	}
	
	public ProjectOwner(UUID uid, String name, String bio, String email) {
		super(uid, name,  bio, email);
		projectIds = new LinkedList<>();
	}
	
	public ProjectOwner(String name, String bio, String email, String password) {
		super(name,  bio, email, password);
		projectIds = new LinkedList<>();
	}
	
	public void addProjectId(UUID projectId) {
		if(projectIds == null) {
			projectIds = new LinkedList<>();
		}
		projectIds.add(projectId);
	}
	
	public void removeProjectId(UUID projectId) {
		if(projectIds != null) {
			projectIds.remove(projectId);
		}
	}
	
	public List<UUID> getProjectIds(){
		return projectIds;
	}
}