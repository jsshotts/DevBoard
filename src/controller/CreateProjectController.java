package controller;

import java.util.logging.Level;

import entity.Project;
import entity.ProjectOwner;

public class CreateProjectController{
	
	private DatabaseController database = new DatabaseController();

	public void pushNewProject(Project project) {
		
		SessionController session = SessionController.getInstance();
		ProjectOwner projectOwner = session.getProjectOwner();
		if(projectOwner != null) {
			projectOwner.addProjectId(project.getID());
			session.updateUser(projectOwner);
			database.pushNew(project);
			database.pushNew(projectOwner);
		}
		else {
			Log.logger.log(Level.WARNING, "Error getting current user from SessionController");
		}
	}
}