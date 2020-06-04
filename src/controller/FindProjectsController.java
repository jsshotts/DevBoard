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
import entity.ProjectOwner;

public class FindProjectsController{
	
	private DataSource database;
	
	public FindProjectsController(DataSource database) {
		this.database = database;
	}
	
	public Map<UUID, Project> getAllProjects(){
		
		Map<UUID, Project> map = database.getAll(DatabaseController.PROJECT_TYPE);
		
		if(map != null) {
			
			List<UUID> removeProjectIds = new LinkedList<>();
			for(Map.Entry<UUID, Project> entry : map.entrySet()) {
				if(entry.getValue().getStatus() != Project.HIRING) {
					removeProjectIds.add(entry.getKey());
				}
			}
			
			for(UUID uid : removeProjectIds) {
				map.remove(uid);
			}			
			return map;
		}
		return new HashMap<>();
	}
	
	public Map<UUID, Project> getDevActiveProjects(){
		Developer dev = (Developer) SessionController.getInstance().getUser();
		
		Map<UUID, Project> result = new HashMap<>();
		for (UUID projectId : dev.getActiveProjectIds()) {
			Project temp = database.getOne(Project.class, projectId);
			result.put(projectId, temp);
		}
		return result;
	}	
	
	public void closeOffer(UUID offerId, int status) {
		
		Offer offer = database.getOne(Offer.class, offerId);
		
		if(offer != null) {
			
			Project project = database.getOne(Project.class, offer.getProjectId());
			Developer developer = database.getOne(Developer.class, offer.getDeveloperId());
			
			if(project != null && developer != null) {
				
				if(project.getPendingOfferId().equals(offer.getId())) {
					
					offer.setStatus(status);
					
					project.addClosedOfferId(offer.getId());
					project.setPendingOfferId(null);
					if(status == Offer.ACCEPTED) {
						project.setStatus(Project.IN_PROGRESS);
						developer.addActiveProjectId(project.getID());
						SessionController.getInstance().updateUser(developer);
					}
					
					database.pushNew(offer);
					database.pushNew(project);
					database.pushNew(developer);
				}
				else {
					Log.logger.log(Level.WARNING, "attempting to close a non-pending offer");
				}
			}
			else {
				Log.logger.log(Level.WARNING, "offer has a projectID or developerID that references nothing");
			}
		}
		else {
			Log.logger.log(Level.WARNING, "attempting to close non-existing offer");
		}
	}
	
	public ProjectOwner getProjectOwner(UUID uid) {
		return database.getOne(ProjectOwner.class, uid);		
	}
}