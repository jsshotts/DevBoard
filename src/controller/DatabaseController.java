package controller;

import java.util.List;

import entity.Developer;
import entity.Project;
import entity.ProjectOwner;
import entity.Repository;

import com.google.gson.Gson;


public class DatabaseController {
	
	private Repository repo = new Repository();
	
	public DatabaseController() {
		test();
	}
	
	public List<Project> getAllProjects(){
		return repo.projects;
	}
	
	public List<Developer> getAllDevelopers() {
		return repo.developers;
	}
	
	public List<ProjectOwner> getAllProjectOwners(){
		return repo.projectOwners;
	}
	
	
	public void test() { 
		Gson gson = new Gson();
		//gson.toJson(new Project())
;		System.out.println("in test");
	}
}