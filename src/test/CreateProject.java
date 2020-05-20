package test;

import static org.junit.Assert.*;

import org.junit.Test;
import controller.DatabaseController;
import entity.Project;

public class CreateProject {

	DatabaseController controller = new DatabaseController();
	@Test
	public void testPut() {
		Project project = new Project("Rick", "1756");
		System.out.println(controller.createProject(project));
	}
}
