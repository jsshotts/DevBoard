package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import controller.MyAppsController;
import controller.SessionController;
import entity.Developer;
import entity.Project;
import entity.Repository;

public class LoopTestApplications {

	@Test
	public void testGetApplicationsLoop() {
		
		Repository repo = new Repository();
		SessionController session = SessionController.getInstance();
		MyAppsController appsController = new MyAppsController(repo);
		Developer developer = new Developer("John Test", "This is my bio.", "testDevEmail@gmail.com");
		List<Project> projects = repo.getProjects();
		
		session.setUser(developer);
		
		List<Project> applications = appsController.getUserApplications();
		assertTrue(applications.isEmpty());
		
		developer.addAppliedProjectId(projects.get(0).getID());
		applications = appsController.getUserApplications();
		assertTrue(applications.size() == 1);
		
		developer.addAppliedProjectId(projects.get(1).getID());
		applications = appsController.getUserApplications();
		assertTrue(applications.size() == 2);
		
		developer.addAppliedProjectId(projects.get(2).getID());
		developer.addAppliedProjectId(projects.get(3).getID());
		developer.addAppliedProjectId(projects.get(4).getID());
		applications = appsController.getUserApplications();
		assertTrue(applications.size() == 5);
	}
}
