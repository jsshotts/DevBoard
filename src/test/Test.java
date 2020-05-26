package test;

import static org.junit.Assert.*;

import java.util.UUID;
import java.util.logging.Level;

import controller.Log;
import entity.Developer;

public class Test {

	@org.junit.Test
	public void test() {
		Developer bob = new Developer("mr. man", "what's this", "no");
		bob.addActiveProjectId(UUID.randomUUID());
		String result = bob.toString();
	}

}
