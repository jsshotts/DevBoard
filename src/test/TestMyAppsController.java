package test;

import controller.MyAppsController;
import controller.SessionController;
import entity.Developer;
import entity.Repository;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

public class TestMyAppsController {

	@Test
	public void testGetUserApps() {
		
		Repository repo = new Repository();
		MyAppsController controller = new MyAppsController(repo);
		SessionController session = SessionController.getInstance();
		
		UUID id = UUID.fromString("c23f3b1e-6080-4a25-97d3-116e0d836943");
		Developer dev = repo.getOne(Developer.class, id);
		
		dev.addAppliedProjectId(repo.getProjects().get(0).getID());
		session.setUser(dev);
		
		assertEquals(dev.getAppliedProjectIds().size(), controller.getUserApplications().size());
	}
	
	@Test
	public void testGetDevOfferMap() {
		
		Repository repo = new Repository();
		MyAppsController controller = new MyAppsController(repo);
		SessionController session = SessionController.getInstance();
		
		UUID id = UUID.fromString("c23f3b1e-6080-4a25-97d3-116e0d836943");
		Developer dev = repo.getOne(Developer.class, id);
		
		dev.addOfferId(repo.getOffers().get(0).getId());
		session.setUser(dev);
		
		assertEquals(dev.getOfferIds().size(), controller.getDevOfferMap().size());
	}
}
