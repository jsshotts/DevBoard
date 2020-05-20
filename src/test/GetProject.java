package test;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.UUID;

import org.junit.Test;
import controller.DatabaseController;

public class GetProject {

	DatabaseController controller = new DatabaseController();
	
	@Test
	public void testGetAllProjects() {
		System.out.println("\nAll projects:");
		System.out.println(controller.getAllProjects());
	}

	@Test
	public void testGet() {
		System.out.println("\nA single project:");
		System.out.println(controller.getProject(UUID.fromString("deea65ba-edab-47fa-b421-c9d14e852956")).toString());
	}
	
}