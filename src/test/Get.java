package test;

import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Test;
import controller.DatabaseController;
import controller.DatabaseController.RequestType;

public class Get {

	@Test
	public void testGet() {
		try {
			DatabaseController.sendHttpRequest("https://devboard-b0a1d.firebaseio.com/.json", RequestType.GET);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}