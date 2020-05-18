package entity;

import java.util.LinkedList;
import java.util.List;

import entity.Filters.Language;

public class Developer extends User {
	
	private List<Language> languages;
	
	private List<String> experience;
	
	public Developer(String name, String bio) {
		super(name,  bio);
	}
	
	public void addLanguage(Language lang) {
		if(languages == null) {
			languages = new LinkedList<Language>();
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
			experience = new LinkedList<String>();
		}
	}
}
