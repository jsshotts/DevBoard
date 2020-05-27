package controller;

import entity.Project;

public class CreateProjectController{
	
	private DatabaseController database = new DatabaseController();

	public void pushNewProject(Project project) {
		database.pushNew(project);
	}
}