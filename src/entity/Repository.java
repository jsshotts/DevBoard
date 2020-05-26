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
					new Project("IOS App ", projectOwners.get(0).getID()),
					new Project("Android App", projectOwners.get(0).getID()),
					new Project("Warehouse Automation ", projectOwners.get(1).getID()),
					new Project("Linux Script ", projectOwners.get(0).getID()),
					new Project("Windows Application0", projectOwners.get(0).getID()),
					new Project("Warehouse Automation1", projectOwners.get(1).getID()),
					new Project("IOS App", projectOwners.get(0).getID()),
					new Project("Linux Script", projectOwners.get(0).getID()),
					new Project("Warehouse Automation ", projectOwners.get(1).getID()),
					new Project("Windows Application2", projectOwners.get(0).getID()),
					new Project("Android App", projectOwners.get(0).getID()),
					new Project("Warehouse Automation3", projectOwners.get(1).getID()),
					new Project("Linux Script", projectOwners.get(0).getID()),
					new Project("Windows Application", projectOwners.get(0).getID()),
					new Project("Warehouse Automation", projectOwners.get(1).getID()),
					new Project("IOS App", projectOwners.get(0).getID()),
					new Project("Windows Application", projectOwners.get(0).getID()),
					new Project("Warehouse Automation", projectOwners.get(1).getID()))
			);
	
	protected static List<Offer> offers = new ArrayList<>(
			Arrays.asList(
				new Offer(projects.get(0).getID(), developers.get(0).getID(), "Here's an offer message"))
			);
	
	public static List<Developer> getDevelopers() {return developers;}
	public static List<ProjectOwner> getProjectOwners() {return projectOwners;}
	public static List<Project> getProjects() {return projects;}
	
	public static void init() {
		Offer offer = offers.get(0);
		developers.get(0).addOfferId(offer.getId());
		projects.get(0).setOfferId(offer.getId());
		
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