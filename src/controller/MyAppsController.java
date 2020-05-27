package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import entity.Developer;
import entity.Project;

public class MyAppsController {
	
	private DatabaseController database = new DatabaseController();
	
	public List<Project> getUserApplications(){		
		List<Project> projects = new LinkedList<>();
		Developer developer = SessionController.getInstance().getDeveloper();
		if(developer != null && developer.getAppliedProjectIds() != null) {
			for(UUID uid : developer.getAppliedProjectIds()) {
				projects.add(
						database.getOne(Project.class, uid)
						);
			}
		}
		return projects;
	}
	
	public List<Project> getUserOffers() {
		List<Project> projects = new LinkedList<>();
		Developer developer = SessionController.getInstance().getDeveloper();
		if (developer != null && !developer.getOfferIds().isEmpty()) {
			for(UUID uid : developer.getOfferIds()) {
				projects.add(database.getOne(Project.class, uid));
			}
		}
		return projects;
	}

}
