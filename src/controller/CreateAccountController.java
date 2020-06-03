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
		/*if (Collections.emptyMap() == database.getOne(Developer.class, "name", name) &&
				Collections.emptyMap() == database.getOne(Developer.class, "email", email)) {*/
			Developer d = new Developer(name, bio, email, password);
			Log.logger.log(Level.INFO, database.pushNew(d).toString());
			return 1;
		/*}
		return 0;*/
	}
	
	public int AddOwner(String name, String bio, String email, String password) {
		/*System.out.println(database.getOne(ProjectOwner.class, "name", name));
		System.out.println(database.getOne(ProjectOwner.class, "email", email));
		if (Collections.emptyMap() == database.getOne(ProjectOwner.class, "name", name) &&
				Collections.emptyMap() == database.getOne(ProjectOwner.class, "email", email)) {*/
			ProjectOwner d = new ProjectOwner(name, bio, email, password);
			Log.logger.log(Level.INFO, database.pushNew(d).toString());
			System.out.println(1);
			return 1;
		/*}
		return 0;*/

	}
}
