package test.classes;

import static org.junit.Assert.*;
import org.junit.Test;

import controller.SessionController;
import entity.Developer;

public class SessionSingletonAndUpdateTests {
	
	private static final String TEST_BIO = "This is a test account";

	@Test
	public void testSingleton() {
		
		SessionController session1 = SessionController.getInstance();
		Developer developer = new Developer("Test Test", TEST_BIO, "Test@test.com", "test");
		session1.setUser(developer);
		
		SessionController session2 = SessionController.getInstance();
		assertEquals(developer, session2.getDeveloper());
		assertEquals(session1, session2);
	}
	
	@Test
	public void testUpdate() {
		
		SessionController session = SessionController.getInstance();
		Developer developer = new Developer("Test Test", TEST_BIO, "Test@test.com", "test");
		session.setUser(developer);
		
		developer.setBio("Here's a new bio");
		session.updateUser(developer);
		assertEquals( developer, session.getDeveloper());
		assertEquals("Here's a new bio", session.getDeveloper().getBio());
		
		Developer developer2 = new Developer("Test2 Test2", TEST_BIO, "Test2@test.com", "test");
		session.updateUser(developer2);
		
		assertFalse(session.getDeveloper() == developer2);
	}
}