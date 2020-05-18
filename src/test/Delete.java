package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import controller.DatabaseController;

public class Delete {

	@Test
	public void testDelete() {
		try {
			DatabaseController.sendDELETE("https://devboard-b0a1d.firebaseio.com/12345.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
