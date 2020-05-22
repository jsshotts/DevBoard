package test;

import static org.junit.Assert.*;

import java.util.logging.Level;

import org.junit.Test;
import controller.DatabaseController;
import controller.Log;
import entity.Developer;
import entity.Project;
import entity.ProjectOwner;
import entity.Repository;

public class HttpPut {

	DatabaseController controller = new DatabaseController();
	
	@Test
	public void testPushRepoDataToFirebase() {
		Log.logger.log(Level.INFO, "\n Add Repo Projects:");
		for(Project project : Repository.getProjects()) {
			String result = controller.pushNew(project).toString();
			Log.logger.log(Level.INFO, result);
		}
		
		Log.logger.log(Level.INFO, "\n Add Repo Developers:");
		for(Developer dev : Repository.getDevelopers()) {
			String result = controller.pushNew(dev).toString();
			Log.logger.log(Level.INFO, result);
		}
		
		Log.logger.log(Level.INFO, "\n Add Repo Project Owners:");
		for(ProjectOwner po : Repository.getProjectOwners()) {
			String result = controller.pushNew(po).toString();
			Log.logger.log(Level.INFO, result);
		}
	}
}
