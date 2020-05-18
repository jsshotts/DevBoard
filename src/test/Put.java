package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import controller.DatabaseController;

public class Put {

	@Test
	public void testPut() {
		try {
			DatabaseController.sendPUT("https://devboard-b0a1d.firebaseio.com/Tester.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
