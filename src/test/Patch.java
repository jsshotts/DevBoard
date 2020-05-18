package test;

import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Test;
import controller.DatabaseController;

public class Patch {
	
	@Test
	public void testPatch() {
		try {
			DatabaseController.sendPATCH("https://devboard-b0a1d.firebaseio.com/Projects/12345.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
