package controller;

import java.util.Map;
import java.util.UUID;

import entity.Project;

public class ActiveProjectsController {

	private DatabaseController database = new DatabaseController();
	
	public Map<UUID, Project> getAllProjects(){
		return database.getAll((DatabaseController.PROJECT_TYPE));
	}
	
}
