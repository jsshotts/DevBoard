package controller;

import entity.Developer;
import entity.ProjectOwner;

public class CreateAccountController {
	DatabaseController database = new DatabaseController();
	SessionController session = SessionController.getInstance();
	
<<<<<<< HEAD
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

=======
	public Developer addDeveloper(String name, String bio, String email) {
		
		Developer dev = database.getOne(Developer.class, "email", email);
		
		if(dev != null) {
			return null;
		}
		
		dev = new Developer(name, bio, email);
		database.pushNew(dev);
		return dev;
	}
	
	public ProjectOwner addOwner(String name, String bio, String email) {
		
		ProjectOwner projectOwner = database.getOne(ProjectOwner.class, "email", email);
		
		if(projectOwner != null) {
			return null;
		}
		
		projectOwner = new ProjectOwner(name, bio, email);
		database.pushNew(projectOwner);
		return projectOwner;
>>>>>>> 7fbf598d1ff0b447b564a495f510e212b4208988
	}
}
