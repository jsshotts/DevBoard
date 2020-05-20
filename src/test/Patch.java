package test;

import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Test;
import controller.DatabaseController;
import controller.DatabaseController.RequestType;
import entity.Project;

public class Patch {
	
	@Test
	public void testPatch() {
		try {
			DatabaseController.sendHttpRequest("https://devboard-b0a1d.firebaseio.com/Projects/12345.json", RequestType.PATCH, new Project("The description", "The project Owner's ID"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
