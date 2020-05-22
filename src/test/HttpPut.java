package test;

import static org.junit.Assert.*;

import java.util.UUID;
import java.util.logging.Level;

import org.junit.Test;
import controller.DatabaseController;
import controller.Log;
import entity.Developer;
import entity.Project;
import entity.ProjectOwner;

public class HttpPut {

	DatabaseController controller = new DatabaseController();
	
	@Test
	public void testPutProject() {
		Log.logger.log(Level.INFO, "\n New Project:");
		Log.logger.log(Level.INFO, () -> controller.pushNew(new Project("Rick", UUID.randomUUID())).toString());
	}
	
	@Test
	public void testPutDev() {
		Log.logger.log(Level.INFO, "\n New Developer:");
		Developer dev = new Developer("John", "Let's get this bread.");
		dev.setEmail("test1@gmail.com");
		Log.logger.log(Level.INFO, () -> controller.pushNew(dev).toString());
	}
	
	@Test
	public void testPutPO() {
		Log.logger.log(Level.INFO, "\n New Project Owner:");
		ProjectOwner po = new ProjectOwner("Mary", "Super Project");
		po.setEmail("test2@gmail.com");
		Log.logger.log(Level.INFO, () -> controller.pushNew(po).toString());
	}
	
	@Test
	public void testPutOffer() {
		Log.logger.log(Level.INFO, "\n New Offer:");
	}
}
