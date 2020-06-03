package test;

import controller.DatabaseController;
import controller.FindProjectsController;
import controller.SessionController;
import entity.Developer;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;


public class TestFindProjectsController {

	@Test
	public void TestGetAllProjects() {
		FindProjectsController controller = new FindProjectsController();
		DatabaseController database = new DatabaseController();
		assertEquals(controller.getAllProjects().size(), database.getAll(database.PROJECT_TYPE).size());
	}
	
	@Test
	public void TestGetDevActiveProjects() {
		DatabaseController database = new DatabaseController();
		FindProjectsController controller = new FindProjectsController();
		SessionController session = SessionController.getInstance();
		UUID id = UUID.fromString("c23f3b1e-6080-4a25-97d3-116e0d836943");
		Developer dev = database.getOne(Developer.class, id);
		session.setUser(dev);
		assertEquals(dev.getActiveProjectIds().size(), controller.getDevActiveProjects().size());
	}
}
