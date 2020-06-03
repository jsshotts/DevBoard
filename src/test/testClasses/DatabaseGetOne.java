package test.testClasses;

import static org.junit.Assert.*;

import org.junit.Test;
import controller.DatabaseController;
import entity.Developer;

public class DatabaseGetOne {

	DatabaseController controller = new DatabaseController();
	
	@Test
	public void getOneValid() {
		Developer testChad = new Developer("Test Chad", "I like test code, but i'm also a Chad", "testChad@test.com");
		controller.pushNew(testChad);
		assertNotEquals(null, controller.getOne(Developer.class, "email", "testChad@test.com"));
		controller.delete(Developer.class, testChad.getID());
	}
	
	@Test
	public void getOneInvalid() {
		assertEquals(null, controller.getOne(Developer.class, "invalid", "invalid"));
	}

}