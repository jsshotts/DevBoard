package test;

import controller.DatabaseController;
import controller.MyAppsController;
import controller.SessionController;
import entity.Developer;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

public class TestMyAppsController {

	@Test
	public void TestGetUserApps() {
		DatabaseController database = new DatabaseController();
		MyAppsController controller = new MyAppsController(database);
		SessionController session = SessionController.getInstance();
		UUID id = UUID.fromString("c23f3b1e-6080-4a25-97d3-116e0d836943");
		Developer dev = database.getOne(Developer.class, id);
		session.setUser(dev);
		assertEquals(dev.getAppliedProjectIds().size(), controller.getUserApplications().size());
	}
	
	@Test
	public void TestGetDevOfferMap() {
		DatabaseController database = new DatabaseController();
		MyAppsController controller = new MyAppsController(database);
		SessionController session = SessionController.getInstance();
		UUID id = UUID.fromString("c23f3b1e-6080-4a25-97d3-116e0d836943");
		Developer dev = database.getOne(Developer.class, id);
		session.setUser(dev);
		assertEquals(dev.getOfferIds().size(), controller.getDevOfferMap().size());
	}
}
