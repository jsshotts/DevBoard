package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;

import entity.Developer;

public class Temp {
	
	
	@Test
	public void testGson() {
		Gson gson = new Gson();
		Developer dev = new Developer("Joe Python", "I am a Python Developer", "test4@gmail.com");
		String json = gson.toJson(dev);
		Developer newDev = gson.fromJson(json, Developer.class);
		int test = 5;
	}
}
