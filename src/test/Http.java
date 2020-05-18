package test;

import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Test;
import controller.DatabaseController;

public class Http {

	@Test
	public void testGet() {
		try {
			DatabaseController.sendGET("https://devboard-b0a1d.firebaseio.com/.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
