package controller;

import java.util.HashMap;
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
	
	public Map<UUID, Project> getDevActiveProjects(){
		Developer dev = (Developer) SessionController.getInstance().getUser();
		
		Map<UUID, Project> result = new HashMap<>();
		for (UUID projectId : dev.getActiveProjectIds()) {
			Project temp = database.getOne(Project.class, projectId);
			result.put(projectId, temp);
		}
		return result;
	}	
}