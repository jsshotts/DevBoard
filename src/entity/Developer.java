package entity;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import entity.Filters.Language;

public class Developer extends User {
	
	private List<UUID> offerIds;
	private List<Language> languages;
	private List<UUID> appliedProjectIds;
	private List<UUID> activeProjectIds;
	private List<String> experience;
	
	public Developer(String name, String bio) {
		super(name,  bio, null, null);
	}
	
	public Developer(String name, String bio, String email) {
		super(name,  bio, email, null);
	}
	
	public Developer(String name, String bio, String email, String password) {
		super(name,  bio, email, password);
	}
	
	public void addLanguage(Language lang) {
		if(languages == null) {
			languages = new LinkedList<>();
		}
		languages.add(lang);
	}
	
	public void removeLanguage(Language lang) {
		if(languages != null) {
			languages.remove(lang);
		}
	}
	
	public void addExperience(String exp) {
		if(experience == null) {
			experience = new LinkedList<>();
		}
		experience.add(exp);
	}
	
	public void addOfferId(UUID offerId) {
		if(offerIds == null) {
			offerIds = new LinkedList<>();
		}
		offerIds.add(offerId);
	}
	
	public List<UUID> getOfferIds(){
		return offerIds;
	}
	
	public void removeOfferId(UUID offerId) {
		if(offerIds != null) {
			offerIds.remove(offerId);
		}
	}
	
	public void addActiveProjectId(UUID projectId) {
		if(activeProjectIds == null) {
			activeProjectIds = new LinkedList<>();
		}
		activeProjectIds.add(projectId);
	}
	
	public List<UUID> getActiveProjectIds(){
		return activeProjectIds;
	}
	
	public void removeActiveProjectId(UUID projectId) {
		if(activeProjectIds != null) {
			activeProjectIds.remove(projectId);
		}
	}
	
	public List<UUID> getAppliedProjectIds(){
		return this.appliedProjectIds;
	}
	
	public void addAppliedProjectId(UUID projectId){
		if(appliedProjectIds == null) {
			appliedProjectIds = new LinkedList<>();
		}
		appliedProjectIds.add(projectId);
	}
	
	public void removeAppliedProjectId(UUID projectId) {
		if(appliedProjectIds != null) {
			appliedProjectIds.remove(projectId);
		}
	}
}
