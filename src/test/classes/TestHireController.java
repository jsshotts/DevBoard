package test.classes;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import controller.HireController;
import entity.Developer;
import entity.Offer;
import entity.Project;
import entity.Repository;

public class TestHireController {
	
	@Test
	public void testGetProjectApplicants() {
		
		HireController hireController = new HireController(new Repository());
		Repository repo = new Repository();
		
		Project project = repo.getProjectWithApplicants();
		
		project = repo.getOne(Project.class, project.getID());
		
		List<Developer> applicants = hireController.getProjectApplicants(project);	
		assertEquals(project.getAppliedDeveloperIDs().size(), applicants.size());		
	}
	
	@Test
	public void testExistingOffer() {
		
		HireController hireController = new HireController(new Repository());
		Developer developer = new Developer("Joe Python", "I am a Python Developer", "joe@gmail.com");
		Project project = new Project("Fitness IOS App ", UUID.randomUUID(), "Flex Technology");
		
		assertFalse(hireController.existingOffer(project, developer));
		
		Offer offer = new Offer(project.getID(), developer.getID(), "");	
		project.setPendingOfferId(offer.getId());
		developer.addOfferId(offer.getId());
		
		assertTrue(hireController.existingOffer(project, developer));
	}
}
