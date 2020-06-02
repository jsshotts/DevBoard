package controller;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import com.google.gson.reflect.TypeToken;

import entity.Developer;
import entity.ProjectOwner;

public class CreateAccountController {
	DatabaseController database = new DatabaseController();
	SessionController session = SessionController.getInstance();
	
	public int AddDeveloper(String name, String bio, String email, String password) {
		Developer d = new Developer(name, bio, email, password);
		Log.logger.log(Level.INFO, database.pushNew(d).toString());
		return 1;
	}
	
	public int AddOwner(String name, String bio, String email, String password) {
		ProjectOwner d = new ProjectOwner(name, bio, email, password);
		Log.logger.log(Level.INFO, database.pushNew(d).toString());
		System.out.println(1);
		return 1;

	}
}
