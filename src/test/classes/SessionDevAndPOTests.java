package test.classes;

import static org.junit.Assert.*;


import org.junit.Test;

import controller.SessionController;
import entity.Developer;
import entity.ProjectOwner;

public class SessionDevAndPOTests {
	
	private static final String TEST_BIO = "This is a test account";
	
	@Test
	public void testDeveloper() { 
		SessionController session = SessionController.getInstance();
		Developer d = new Developer("Test Test", TEST_BIO, "Test@test.com", "test");
		session.setUser(d);
		assertTrue(session.isDeveloper());
		assertEquals(TEST_BIO,session.getUser().getBio());
		d.setBio("This is the new bio of user.");
		
		assertEquals(null, session.getProjectOwner());
	}
	
	@Test
	public void testProjectOwner() {
		SessionController session = SessionController.getInstance();
		session.setUser(new ProjectOwner("Test Test", TEST_BIO, "Test@test.com", "test"));
		assertFalse(session.isDeveloper());
		assertTrue(session.getProjectOwner() instanceof ProjectOwner);
		assertEquals(null, session.getDeveloper());
	}
}
