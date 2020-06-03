package test;

import static org.junit.Assert.*;

import java.util.Map;
import org.junit.Test;

import controller.MyAppsController;
import controller.SessionController;
import entity.Developer;
import entity.Offer;
import entity.Project;
import entity.Repository;

public class LoopTestOffers {

	@Test
	public void testGetOffersLoop() {
		
		Repository repo = new Repository();
		SessionController session = SessionController.getInstance();
		MyAppsController appsController = new MyAppsController(repo);
		Developer developer = new Developer("John Test", "This is my bio.", "testDevEmail@gmail.com");
		
		session.setUser(developer);
		
		Map<Project, Offer> map = appsController.getDevOfferMap();
		assertTrue(map.isEmpty());
		
		developer.addOfferId(repo.getOffers().get(0).getId());
		map = appsController.getDevOfferMap();
		assertTrue(map.size() == 1);
		
		developer.addOfferId(repo.getOffers().get(1).getId());
		map = appsController.getDevOfferMap();
		assertTrue(map.size() == 2);
		
		developer.addOfferId(repo.getOffers().get(2).getId());
		developer.addOfferId(repo.getOffers().get(3).getId());
		developer.addOfferId(repo.getOffers().get(4).getId());
		map = appsController.getDevOfferMap();
		assertTrue(map.size() == 5);
	}
}
