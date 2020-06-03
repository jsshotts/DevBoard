package test.classes;

import static org.junit.Assert.*;

import java.util.Collections;
import org.junit.Test;
import controller.DatabaseController;

public class DatabaseGetAll {

	DatabaseController controller = new DatabaseController();
	
	@Test
	public void getAllValid() {
		assertNotEquals(Collections.emptyMap(), controller.getAll(DatabaseController.PROJECT_TYPE));
	}
	
	@Test
	public void getAllInvalid() {
		assertEquals(Collections.emptyMap(), controller.getAll(DatabaseController.PROJECT_TYPE, "invalid", "invalid"));
	}
	
}