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
	
	public Developer AddDeveloper(String name, String bio, String email) {
		Developer dev = new Developer(name, bio, email);
		Log.logger.log(Level.INFO, database.pushNew(dev).toString());
		return dev;
	}
	
	public ProjectOwner AddOwner(String name, String bio, String email) {
		ProjectOwner projectOwner = new ProjectOwner(name, bio, email);
		Log.logger.log(Level.INFO, database.pushNew(projectOwner).toString());
		return projectOwner;
	}
}
