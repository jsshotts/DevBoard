package controller;

import java.util.List;

import entity.Developer;
import entity.Project;
import entity.ProjectOwner;
import entity.Repository;

public class DatabaseController {
	
	private Repository repo = new Repository();
	
	public List<Project> getAllProjects(){
		return repo.projects;
	}
	
	public List<Developer> getAllDevelopers() {
		return repo.developers;
	}
	
	public List<ProjectOwner> getAllProjectOwners(){
		return repo.projectOwners;
	}
}