package controller;

import java.util.logging.Level;

import entity.Developer;
import entity.Project;

public class ApplyController{
	
	private DatabaseController database = new DatabaseController();
	
	public void apply(Project project){
		Developer dev = (Developer) SessionController.getInstance().getUser();
		
		if (dev.getAppliedProjectIds() != null && dev.getAppliedProjectIds().contains(project.getID()))
			return;
		
		dev.addAppliedProjectId(project.getID());
		project.addAppliedDeveloperID(dev.getID());
		
		Log.logger.log(Level.INFO, () -> "dev id:" + dev.getID().toString());
		database.update(dev);
		
		Log.logger.log(Level.INFO, () -> "project id:" + project.getID().toString());
		database.update(project);
	}
	
}
