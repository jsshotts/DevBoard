package test;

import static org.junit.Assert.*;

import java.util.UUID;
import java.util.logging.Level;

import org.junit.Test;
import controller.DatabaseController;
import controller.Log;
import entity.Developer;
import entity.Offer;
import entity.Project;
import entity.ProjectOwner;
import entity.Repository;

public class HttpPut {

	DatabaseController controller = new DatabaseController();
	Repository repo = new Repository();
	
	@Test
	public void testPutProject() {
		Log.logger.log(Level.INFO, "\n Add Repo Projects:");
		for(Project project : repo.projects) {
			Log.logger.log(Level.INFO, () -> controller.pushNew(project).toString());
		}
	}
	
	@Test
	public void testPutDev() {
		Log.logger.log(Level.INFO, "\n Add Repo Developers:");
		for(Developer dev : repo.developers) {
			Log.logger.log(Level.INFO, () -> controller.pushNew(dev).toString());
		}
	}
	
	@Test
	public void testPutPO() {
		Log.logger.log(Level.INFO, "\n Add Repo Project Owners:");
		for(ProjectOwner po : repo.projectOwners) {
			Log.logger.log(Level.INFO, () -> controller.pushNew(po).toString());
		}
	}
	
	@Test
	public void testPutOffer() {
		
	}
}
