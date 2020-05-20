package test;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import controller.DatabaseController;
import entity.Developer;
import entity.Project;
import entity.ProjectOwner;

public class HttpPut {

	DatabaseController controller = new DatabaseController();
	
	@Test
	public void testPutProject() {
		System.out.println("\n New Project:");
		System.out.println(controller.pushNew(new Project("Rick", UUID.randomUUID())));
	}
	
	@Test
	public void testPutDev() {
		System.out.println("\n New Developer:");
		System.out.println(controller.pushNew(new Developer("John", "Let's get this bread.")));
	}
	
	@Test
	public void testPutPO() {
		System.out.println("\n New Project Owner:");
		System.out.println(controller.pushNew(new ProjectOwner("Mary", "Super Project")));
	}
}
