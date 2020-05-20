package test;

import static org.junit.Assert.*;
import java.util.UUID;
import java.util.logging.Level;

import org.junit.Test;
import controller.DatabaseController;
import controller.Log;
import entity.Developer;
import entity.Project;
import entity.ProjectOwner;

public class HttpGet {

	DatabaseController controller = new DatabaseController();
	
	@Test
	public void testGetAllProjects() {
		Log.logger.log(Level.INFO, "\nAll projects:");
		Log.logger.log(Level.INFO, () -> controller.getAll(DatabaseController.PROJECT_TYPE).toString());
	}
	
	@Test
	public void testGetAllDevelopers() {
		Log.logger.log(Level.INFO, "\nAll developers:");
		Log.logger.log(Level.INFO, () -> controller.getAll(DatabaseController.DEVELOPER_TYPE).toString());
	}
	
	@Test
	public void testGetAllProjectOwners() {
		Log.logger.log(Level.INFO, "\nAll ProjectOwners:");
		Log.logger.log(Level.INFO, () -> controller.getAll(DatabaseController.PROJECTOWNER_TYPE).toString());
	}

	@Test
	public void testGetProject() {
		Log.logger.log(Level.INFO, "\nA single project:");
		Log.logger.log(Level.INFO, () -> controller.getOne(Project.class, UUID.fromString("d2e507a3-4314-4b27-9930-4bf2fa106553")).toString());
	}
	
	@Test
	public void testGetDeveloper() {
		Log.logger.log(Level.INFO, "\nA single Developer:");
		Log.logger.log(Level.INFO, () -> controller.getOne(Developer.class, UUID.fromString("71711750-9eb8-4548-8e15-1f22fae0e5b9")).toString());
	}
	
	@Test
	public void testGetProjectOwner() {
		Log.logger.log(Level.INFO, "\nA single projectOwner:");
		Log.logger.log(Level.INFO, () -> controller.getOne(ProjectOwner.class, UUID.fromString("7758627d-3f6d-4fd1-9464-33172ea4cac7")).toString());
	}
	
	@Test
	public void testGetEmail() {
		Log.logger.log(Level.INFO, "\nA user based on Email:");
		Log.logger.log(Level.INFO, () -> controller.getAll(DatabaseController.DEVELOPER_TYPE, "email", "test1@gmail.com").toString());
	}
	
}