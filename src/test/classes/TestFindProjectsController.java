package test.classes;

import controller.DatabaseController;
import controller.FindProjectsController;
import controller.SessionController;
import entity.Developer;
import entity.Repository;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestFindProjectsController {

	@Test
	public void testGetAllProjects() {
		Repository repo = new Repository();
		FindProjectsController controller = new FindProjectsController(repo);
		assertEquals(controller.getAllProjects().size(), repo.getAll(DatabaseController.PROJECT_TYPE).size());
	}
	
	@Test
	public void testGetDevActiveProjects() {
		
		Repository repo = new Repository();
		FindProjectsController controller = new FindProjectsController(repo);
		SessionController session = SessionController.getInstance();
		
		Developer dev = repo.getDeveloperWithActiveProject();
		session.setUser(dev);
		assertEquals(dev.getActiveProjectIds().size(), controller.getDevActiveProjects().size());
	}
}
