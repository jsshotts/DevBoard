package test.testClasses;

import static org.junit.Assert.*;


import org.junit.Test;

import controller.LoginController;
import controller.SessionController;
import entity.Repository;

public class TestLoginController {

	@Test
	public void testLoginDeveloper() {
		LoginController loginController = new LoginController(new Repository());
		String email = "testDevEmail@gmail.com";
		assertTrue(loginController.loginDeveloper(email));
		assertTrue(SessionController.getInstance().getDeveloper().getEmail().equals(email));
	}
	
	@Test
	public void testLoginProjectOwner() {
		LoginController loginController = new LoginController(new Repository());
		String email = "testPOEmail@gmail.com";
		assertTrue(loginController.loginProjectOwner(email));
		assertTrue(SessionController.getInstance().getProjectOwner().getEmail().equals(email));
	}
}