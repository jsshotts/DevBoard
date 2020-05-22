package entity;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ProjectOwner extends User{
	
	private List<UUID> projectIds;
	
	public ProjectOwner(String name, String bio) {
		super(name,  bio, null);
		projectIds = new LinkedList<>();
	}
	
	public ProjectOwner(String name, String bio, String email) {
		super(name,  bio, email);
		projectIds = new LinkedList<>();
	}
	
	public void addProjectId(UUID projectId) {
		if(projectIds == null) {
			projectIds = new LinkedList<UUID>();
		}
		projectIds.add(projectId);
	}
	
	public void removeProjectId(UUID projectId) {
		if(projectIds != null) {
			projectIds.remove(projectId);
		}
	}
}