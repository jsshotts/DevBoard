package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import entity.Developer;
import entity.Project;
import entity.ProjectOwner;

public class HireController {
	
	DatabaseController database = new DatabaseController();
	
	public List<Project> getPOActiveProjects() {
		
		List<Project> projects = new LinkedList<>();
		SessionController session = SessionController.getInstance();
		ProjectOwner projectOwner = session.getProjectOwner();
		
		if(projectOwner != null && projectOwner.getProjectIds() != null) {
			for(UUID uid : projectOwner.getProjectIds()) {
				projects.add(
					database.getOne(Project.class, uid)	
						);
			}
		}
		return projects;
	}
	
	public List<Developer> getProjectApplicants(Project project){
		
		List<Developer> developers = new LinkedList<>();
		
		for(UUID uid : project.getAppliedDeveloperIDs()) {
			developers.add(
					database.getOne(Developer.class, uid)
					);
		}
		return developers;
	}
}