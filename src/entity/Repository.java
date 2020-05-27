package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Dummy Data class to be used until we have Firebase fully functional
public class Repository {
	
	private Repository() { throw new IllegalStateException("Utility class"); }
	
	protected static List<Developer> developers = new ArrayList<>(
			Arrays.asList(
					new Developer("Joe Python", "I am a Python Developer", "joe@gmail.com"),
					new Developer("Fred Java", "I am a Java Developer", "fred@gmail.com"),
					new Developer("Bob John", "I am an IOS developer", "bob@gmail.com"),
					new Developer("John Kotlin", "I am an Android Developer", "john@gmail.com"))
			);
	
	protected static List<ProjectOwner> projectOwners = new ArrayList<>(
			Arrays.asList(
					new ProjectOwner("Flex Technologies", "We are a fitness company", "flex@gmail.com"),
					new ProjectOwner("Proactive Co", "We sell local art to the community", "proactive@gmail.com"),
					new ProjectOwner("Better Italian", "We make the best Italian food in the county", "italian@gmail.com"),
					new ProjectOwner("Kayak Today", "We rent kayaks to the community", "kayak@gmail.com"))
			);
	
	protected static List<Project> projects = new ArrayList<>(
			Arrays.asList(
					new Project("Fitness IOS App ", projectOwners.get(0).getID(), projectOwners.get(0).getName()),
					new Project("Art Android App", projectOwners.get(1).getID(), projectOwners.get(1).getName()),
					new Project("Restaurant Warehouse Automation ", projectOwners.get(2).getID(), projectOwners.get(2).getName()),
					new Project("Kayak Server Linux Script ", projectOwners.get(3).getID(), projectOwners.get(3).getName()),
					new Project("Windows Application-0", projectOwners.get(0).getID(), projectOwners.get(0).getName()),
					new Project("Warehouse Automation-1", projectOwners.get(1).getID(), projectOwners.get(1).getName()),
					new Project("IOS App-2", projectOwners.get(2).getID(), projectOwners.get(2).getName()),
					new Project("Linux Script-2", projectOwners.get(3).getID(), projectOwners.get(3).getName()),
					new Project("Warehouse Automation-6", projectOwners.get(0).getID(), projectOwners.get(0).getName()),
					new Project("Windows Application-2", projectOwners.get(1).getID(), projectOwners.get(1).getName()),
					new Project("Android App-2", projectOwners.get(2).getID(), projectOwners.get(2).getName()),
					new Project("Warehouse Automation-3", projectOwners.get(3).getID(), projectOwners.get(3).getName()),
					new Project("Linux Script-3", projectOwners.get(0).getID(), projectOwners.get(0).getName()),
					new Project("Windows Application-4", projectOwners.get(1).getID(), projectOwners.get(1).getName()),
					new Project("Warehouse Automation-5", projectOwners.get(2).getID(), projectOwners.get(2).getName()),
					new Project("IOS App-3", projectOwners.get(3).getID(), projectOwners.get(3).getName()),
					new Project("Windows Application-3", projectOwners.get(0).getID(), projectOwners.get(0).getName()),
					new Project("Warehouse Automation-4", projectOwners.get(1).getID(), projectOwners.get(1).getName()))
			);
	
	protected static List<Offer> offers = new ArrayList<>(
			Arrays.asList(
				new Offer(projects.get(0).getID(), developers.get(0).getID(), "Here's an offer message"))
			);
	
	public static List<Developer> getDevelopers() {return developers;}
	public static List<ProjectOwner> getProjectOwners() {return projectOwners;}
	public static List<Project> getProjects() {return projects;}
	
	public static void init() {
//		Offer offer = offers.get(0);
//		developers.get(0).addOfferId(offer.getId());
//		projects.get(0).setOfferId(offer.getId());
		
		projects.get(0).addAppliedDeveloperID(developers.get(0).getID());
		projects.get(0).addAppliedDeveloperID(developers.get(1).getID());
		projects.get(0).addAppliedDeveloperID(developers.get(2).getID());
		projects.get(0).addAppliedDeveloperID(developers.get(3).getID());
		developers.get(0).addAppliedProjectId(projects.get(0).getID());
		developers.get(1).addAppliedProjectId(projects.get(0).getID());
		developers.get(2).addAppliedProjectId(projects.get(0).getID());
		developers.get(3).addAppliedProjectId(projects.get(0).getID());
		
		developers.get(1).addActiveProjectId(projects.get(1).getID());
		developers.get(1).addActiveProjectId(projects.get(2).getID());
		
		for(Project project : projects) {
			for(ProjectOwner po : projectOwners) {
				if(project.getProjectOwnerId().equals(po.getID())) {
					po.addProjectId(project.getID());
				}
			}
		}
	}
}