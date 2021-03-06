package entity;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import entity.Filters.Language;

public class Developer extends User {
	
	private List<UUID> offerIds = new LinkedList<>();
	private List<Language> languages = new LinkedList<>();
	private List<UUID> appliedProjectIds = new LinkedList<>();
	private List<UUID> activeProjectIds = new LinkedList<>();
	private List<String> experience = new LinkedList<>();
	
	public Developer(String name, String bio) {
		super(name,  bio, null, null);
	}
	
	public Developer(String name, String bio, String email) {
		super(name,  bio, email, null);
	}
	
	public Developer(String name, String bio, String email, String password) {
		super(name,  bio, email, password);
	}
	
	public Developer(UUID uid, String name, String bio, String email) {
		super(uid, name,  bio, email);
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
	
	public List<Language> getLanguages(){
		return languages;
	}
	
	public void addExperience(String exp) {
		if(experience == null) {
			experience = new LinkedList<>();
		}
		experience.add(exp);
	}
	
	public List<String> getExperience() {
		return experience;
	}
	
	public void addOfferId(UUID offerId) {
		if(offerIds == null) {
			offerIds = new LinkedList<>();
		}
		offerIds.add(offerId);
	}
	
	public List<UUID> getOfferIds(){
		if(offerIds == null) {
			return new LinkedList<>();
		}
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
		if(activeProjectIds == null) {
			return new LinkedList<>();
		}
		return activeProjectIds;
	}
	
	public void removeActiveProjectId(UUID projectId) {
		if(activeProjectIds != null) {
			activeProjectIds.remove(projectId);
		}
	}
	
	public List<UUID> getAppliedProjectIds(){
		if(this.appliedProjectIds == null) {
			return new LinkedList<>();
		}
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
	
	public boolean hasOffer(UUID offerId) {
		if(this.offerIds == null) {
			return false;
		}
		return this.offerIds.contains(offerId);
	}

	@Override
	public String toString() {
		String result = super.toString();
		result += offerIds == null ? "" : String.format("offerIds: %s%n", offerIds.toString());
		result += languages == null ? "" : String.format("languages: %s%n", languages.toString());
		result += experience == null ? "" : String.format("experience: %s%n", experience.toString());
		result += appliedProjectIds == null ? "" : String.format("appliedProjectIds: %s%n", appliedProjectIds.toString());
		result += activeProjectIds == null ? "" : String.format("activeProjectIds: %s%n", activeProjectIds.toString());
		return result;
	}
}
