package controller;

import java.util.List;

import entity.Project;

public class FindProjectsController{
	
	private DatabaseController database = new DatabaseController();
	
	public List<Project> getAllProjects(){
		return database.getAllProjects();
	}
}
