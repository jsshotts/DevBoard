package test;

import static org.junit.Assert.*;
import java.util.UUID;

import org.junit.Test;
import controller.DatabaseController;
import entity.Developer;
import entity.Project;
import entity.ProjectOwner;

public class HttpGet {

	DatabaseController controller = new DatabaseController();
	
	@Test
	public void testGetAllProjects() {
		System.out.println("\nAll projects:");
		System.out.println(controller.getAll(DatabaseController.PROJECT_TYPE));
	}
	
	@Test
	public void testGetAllDevelopers() {
		System.out.println("\nAll developers:");
		System.out.println(controller.getAll(DatabaseController.DEVELOPER_TYPE));
	}
	
	@Test
	public void testGetAllProjectOwners() {
		System.out.println("\nAll ProjectOwners:");
		System.out.println(controller.getAll(DatabaseController.PROJECTOWNER_TYPE));
	}

	@Test
	public void testGetProject() {
		System.out.println("\nA single project:");
		System.out.println(controller.getOne(UUID.fromString("d2e507a3-4314-4b27-9930-4bf2fa106553"), Project.class).toString());
	}
	
	@Test
	public void testGetDeveloper() {
		System.out.println("\nA single Developer:");
		System.out.println(controller.getOne(UUID.fromString("71711750-9eb8-4548-8e15-1f22fae0e5b9"), Developer.class).toString());
	}
	
	@Test
	public void testGetProjectOwner() {
		System.out.println("\nA single projectOwner:");
		System.out.println(controller.getOne(UUID.fromString("7758627d-3f6d-4fd1-9464-33172ea4cac7"), ProjectOwner.class).toString());
	}
	
}