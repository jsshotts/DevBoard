package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import controller.DatabaseController;
import controller.DatabaseController.RequestType;

public class Delete {

	@Test
	public void testDelete() {
		try {
			DatabaseController.sendHttpRequest("https://devboard-b0a1d.firebaseio.com/12345.json", RequestType.DELETE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
