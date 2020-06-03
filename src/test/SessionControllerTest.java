package test;

import static org.junit.Assert.*;


import org.junit.Test;

import application.Main;
import boundary.DevNavBar;
import boundary.LoginScreen;
import controller.LoginController;
import controller.SessionController;
import entity.Developer;
import entity.ProjectOwner;
import javafx.scene.control.TextField;

public class SessionControllerTest {

	
	@Test
	public void testDeveloper() { 
		SessionController session = SessionController.getInstance();
		Developer d = new Developer("Test Test", "This is a test account", "Test@test.com", "test");
		session.setUser(d);
		assertTrue(session.isDeveloper());
		assertEquals("This is a test account",session.getUser().getBio());
		d.setBio("This is the new bio of user.");
		session.updateUser(d);
		assertEquals("This is the new bio of user.",session.getUser().getBio());
		assertTrue(session.getDeveloper() instanceof Developer);
		
	}
	
	@Test
	public void testProjectOwner() {
		SessionController session = SessionController.getInstance();

		session.setUser(new ProjectOwner("Test Test", "This is a test account", "Test@test.com", "test"));
		assertFalse(session.isDeveloper());
		assertTrue(session.getProjectOwner() instanceof ProjectOwner);
	}

}
