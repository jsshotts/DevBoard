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
		Developer dev = new Developer("John", "Let's get this bread.");
		dev.setEmail("test1@gmail.com");
		System.out.println(controller.pushNew(dev));
	}
	
	@Test
	public void testPutPO() {
		System.out.println("\n New Project Owner:");
		ProjectOwner po = new ProjectOwner("Mary", "Super Project");
		po.setEmail("test2@gmail.com");
		System.out.println(controller.pushNew(po));
	}
}
