package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import controller.DataSource;
import entity.Filters.Language;
import entity.Filters.ProjectPlatform;

//Dummy Data class to be used until we have Firebase fully functional
public class Repository implements DataSource{
	
	private static final String OFFER_MESSAGE = "Here's an offer message";
	
	protected String[] locations = {"San Luis Obispo, CA", "Los Angeles, CA", "Chicago, Illinois", 
			"New York, New York", "San Francisco, CA", "Miami, Florida", "Denver, Colorado"};
	
	protected Language[] languages = {Language.PYTHON, Language.C, Language.CPP, Language.JAVA,
			Language.JAVASCRIPT, Language.KOTLIN, Language.SWIFT};
	
	protected ProjectPlatform[] platforms = {ProjectPlatform.IOS, ProjectPlatform.ANDROID, ProjectPlatform.LINUX,
			ProjectPlatform.MAC, ProjectPlatform.WINDOWS};
	
	protected List<Developer> developers = new ArrayList<>(
			Arrays.asList(
					new Developer("Joe Python", "I am a Python Developer", "joe@gmail.com"),
					new Developer("Fred Java", "I am a Java Developer", "fred@gmail.com"),
					new Developer("Bob John", "I am an IOS developer", "bob@gmail.com"),
					new Developer("John Kotlin", "I am an Android Developer", "john@gmail.com"),
					new Developer("Tom Joe", "I am an Windows Developer", "tom@gmail.com"),
					new Developer("Jerry Smith", "I am an Linux Developer", "jerry@gmail.com"),
					new Developer("Antonio Swift", "I am an Swift Developer", "antonio@gmail.com"),
					new Developer("Jacob Plus", "I am an C++ Developer", "jacob@gmail.com"))
			);
	
	protected List<ProjectOwner> projectOwners = new ArrayList<>(
			Arrays.asList(
					new ProjectOwner("Flex Technologies", "We are a fitness company", "flex@gmail.com"),
					new ProjectOwner("Proactive Co", "We sell local art to the community", "proactive@gmail.com"),
					new ProjectOwner("Better Italian", "We make the best Italian food in the county", "italian@gmail.com"),
					new ProjectOwner("Kayak Today", "We rent kayaks to the community", "kayak@gmail.com"))
			);
	
	protected List<Project> projects = new ArrayList<>(
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
	
	protected List<Offer> offers = new ArrayList<>(
			Arrays.asList(
				new Offer(projects.get(0).getID(), developers.get(0).getID(), OFFER_MESSAGE),
				new Offer(projects.get(1).getID(), developers.get(1).getID(), OFFER_MESSAGE),
				new Offer(projects.get(2).getID(), developers.get(2).getID(), OFFER_MESSAGE),
				new Offer(projects.get(3).getID(), developers.get(3).getID(), OFFER_MESSAGE),
				new Offer(projects.get(4).getID(), developers.get(4).getID(), OFFER_MESSAGE),
				new Offer(projects.get(5).getID(), developers.get(5).getID(), OFFER_MESSAGE))
			);
	
	public List<Developer> getDevelopers() {return developers;}
	public List<ProjectOwner> getProjectOwners() {return projectOwners;}
	public List<Project> getProjects() {return projects;}
	public List<Offer> getOffers() {return offers;}
	
	public Project getProjectWithApplicants() {
		return this.projects.get(0);
	}
	
	private static final String NAME = "John Test";
	private static final String BIO = "This is my bio.";
	
	public <T> T getOne(Class<T> cls, UUID id) {
		if (cls.equals(Project.class)) {
			return cls.cast(new Project(id, "Project Title", UUID.randomUUID(), "Sample Project Owner"));
		}
		else if (cls.equals(ProjectOwner.class)) {
			return cls.cast(new ProjectOwner(id, NAME, BIO, "testDevEmail@gmail.com"));
		}
		else if (cls.equals(Developer.class)) {
			return cls.cast(new Developer(id, NAME, BIO, "testDevEmail@gmail.com"));
		}
		else if (cls.equals(Offer.class)) {
			return cls.cast(new Offer(id, UUID.randomUUID()));
		}
		return null;
	}
	
	public <T> T getOne(Class<T> cls, String key, String value) {
			
		if(cls == Developer.class && key.equals("email")) {
			return cls.cast(new Developer(NAME, BIO, value));
		}
		else if(cls == ProjectOwner.class && key.equals("email")) {
			return cls.cast(new ProjectOwner("Test Company", BIO, value));
		}
		return null;
	}
	
	public Repository() {
		
		setProjectData();
		setDeveloperData();
		
		getProjectWithApplicants().addAppliedDeveloperID(developers.get(0).getID());
		getProjectWithApplicants().addAppliedDeveloperID(developers.get(1).getID());
		getProjectWithApplicants().addAppliedDeveloperID(developers.get(2).getID());
		getProjectWithApplicants().addAppliedDeveloperID(developers.get(3).getID());
		developers.get(0).addAppliedProjectId(getProjectWithApplicants().getID());
		developers.get(1).addAppliedProjectId(getProjectWithApplicants().getID());
		developers.get(2).addAppliedProjectId(getProjectWithApplicants().getID());
		developers.get(3).addAppliedProjectId(getProjectWithApplicants().getID());
		
		projects.get(1).setStatus(Project.IN_PROGRESS);
		projects.get(2).setStatus(Project.IN_PROGRESS);
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
	
	private static final Random random = new Random();
	
	private void setProjectData() {
		for(Project project : projects) {
			int i = random.nextInt();
			i = i == Integer.MIN_VALUE ? 0 : Math.abs(i);
			project.setData(
					locations[i % (locations.length-1)], 
					languages[i % (languages.length-1)], 
					platforms[i % (platforms.length-1)]);
		}
	}
	
	private void setDeveloperData() {
		for(Developer developer : developers) {
			
			int i = random.nextInt();
			i = i == Integer.MIN_VALUE ? 0 : Math.abs(i);
			
			developer.addLanguage(languages[i % (languages.length-1)]);
			developer.addExperience(platforms[i % (platforms.length-1)].getString());			
			i++;
			developer.addLanguage(languages[i % (languages.length-1)]);
			developer.addExperience(platforms[i % (platforms.length-1)].getString());
		}
	}
}