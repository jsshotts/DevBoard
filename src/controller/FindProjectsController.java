package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import entity.Developer;
import entity.Project;

public class FindProjectsController{
	
	private DatabaseController database = new DatabaseController();
	
	public Map<UUID, Project> getAllProjects(){
		return database.getAll(DatabaseController.PROJECT_TYPE);
	}
	
	public List<Project> getUserApplications(){		
		List<Project> projects = new LinkedList<>();
		Developer developer = SessionController.getInstance().getDeveloper();
		if(developer != null) {
			for(UUID uid : developer.getAppliedProjectIds()) {
				projects.add(
						database.getOne(Project.class, uid)
						);
			}
		}
		return projects;
	}
}