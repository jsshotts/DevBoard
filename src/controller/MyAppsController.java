package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import entity.Developer;
import entity.Offer;
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
	
	public Map<Project, Offer> getDevOfferMap() {
		
		Developer developer = SessionController.getInstance().getDeveloper();
		
		List<Offer> devOffers = new LinkedList<>();
		List<Project> devProjects = new LinkedList<>();
		Map<Project, Offer> map = new HashMap<>();
		
		if (developer != null && !developer.getOfferIds().isEmpty()) {
			
			for(UUID uid : developer.getOfferIds()) {
				
				Offer offer = database.getOne(Offer.class, uid);
				if(offer != null) {
					devOffers.add(offer);
				}
				else {
					Log.logger.log(Level.WARNING, "developer has offerID that does not exist");
				}
			}
			
			for(Offer offer : devOffers) {
				
				Project project = database.getOne(Project.class, offer.getProjectId());
				if(project != null) {
					map.put(project, offer);
				}
				else {
					Log.logger.log(Level.WARNING, "offer has projectID that does not exist");
				}
			}
		}
		return map;
	}

}
