package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import entity.Developer;
import entity.Offer;
import entity.Project;
import entity.ProjectOwner;

public class HireController {
	
	DatabaseController database = new DatabaseController();
	
	public List<Project> getPOActiveProjects() {
		
		List<Project> projects = new LinkedList<>();
		SessionController session = SessionController.getInstance();
		ProjectOwner projectOwner = session.getProjectOwner();
		
		if(projectOwner != null && projectOwner.getProjectIds() != null) {
			for(UUID uid : projectOwner.getProjectIds()) {
				
				projects.add(database.getOne(Project.class, uid));
			}
		}
		return projects;
	}
	
	public List<Developer> getProjectApplicants(Project project){
		
		List<Developer> developers = new LinkedList<>();
		
		for(UUID uid : project.getAppliedDeveloperIDs()) {
			
			developers.add(database.getOne(Developer.class, uid));
		}
		return developers;
	}
	
	public void sendOffer(Project project, Developer developer) {
		
		if(!existingOffer(project, developer) && project.getPendingOfferId() == null) {
			
			Offer offer = new Offer(project.getID(), developer.getID(), "");
			
			project.setPendingOfferId(offer.getId());
			developer.addOfferId(offer.getId());
			developer.removeAppliedProjectId(project.getID());
			database.pushNew(offer);
			database.update(project);
			database.update(developer);		
		}
	}
	
	public boolean existingOffer(Project project, Developer developer) {
		if(developer.hasOffer(project.getPendingOfferId())) {
			return true;
		}
		return false;
	}
	
	public Map<Developer, Offer> getProjectOfferMap(Project project){
		
		List<Offer> offers = new LinkedList<>();		
		for(UUID uid : project.getAllOfferIds()) {
			
			offers.add(database.getOne(Offer.class, uid));
		}
		
		Map<Developer, Offer> map = new HashMap<>();		
		for(Offer offer : offers) {
			
			map.put(database.getOne(Developer.class, offer.getDeveloperId()), offer);
		}		
		
		return map;
	}
}