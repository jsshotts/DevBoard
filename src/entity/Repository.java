package entity;

import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.reflect.TypeToken;

import controller.DataSource;
import entity.Filters.Language;
import entity.Filters.ProjectPlatform;

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
					new ProjectOwner("Fresh Italian", "We make the best Italian food in the county", "italian@gmail.com"),
					new ProjectOwner("Kayak Today", "We rent kayaks to the community", "kayak@gmail.com"),
					new ProjectOwner("Gym Grind", "We provide the community's largest gym.", "gym@gmail.com"),
					new ProjectOwner("Burger Buddy", "We make the best burgers in the county", "burger@gmail.com"),
					new ProjectOwner("Car Rents", "We rent cars to the community", "car@gmail.com"),
					new ProjectOwner("Computer Today", "We rent laptops to the community", "computer@gmail.com"),
					new ProjectOwner("Speaker City", "We rent and sell speakers to the community", "speaker@gmail.com"))
			);
	
	protected List<Project> projects = new ArrayList<>(
			Arrays.asList(
					new Project("Fitness IOS App ", projectOwners.get(0).getID(), projectOwners.get(0).getName()),
					new Project("Art Android App", projectOwners.get(1).getID(), projectOwners.get(1).getName()),
					new Project("Restaurant Warehouse Automation ", projectOwners.get(2).getID(), projectOwners.get(2).getName()),
					new Project("Warehouse Automation", projectOwners.get(3).getID(), projectOwners.get(3).getName()),
					new Project("Gym App", projectOwners.get(4).getID(), projectOwners.get(4).getName()),
					new Project("Burger App", projectOwners.get(5).getID(), projectOwners.get(5).getName()),
					new Project("IOS App", projectOwners.get(6).getID(), projectOwners.get(6).getName()),
					new Project("Computer Exchange Website", projectOwners.get(7).getID(), projectOwners.get(7).getName()),
					new Project("Speaker Testing Service", projectOwners.get(8).getID(), projectOwners.get(8).getName()),
					new Project("Windows Application", projectOwners.get(0).getID(), projectOwners.get(0).getName()),
					new Project("Android App", projectOwners.get(1).getID(), projectOwners.get(1).getName()),
					new Project("Linux Server Automation", projectOwners.get(2).getID(), projectOwners.get(2).getName()),
					new Project("Linux Shell Script", projectOwners.get(4).getID(), projectOwners.get(3).getName()),
					new Project("Windows Bash Script", projectOwners.get(4).getID(), projectOwners.get(4).getName()),
					new Project("Company Website", projectOwners.get(5).getID(), projectOwners.get(5).getName()),
					new Project("Company Android App", projectOwners.get(6).getID(), projectOwners.get(6).getName()),
					new Project("Windows Shell Script", projectOwners.get(7).getID(), projectOwners.get(7).getName()),
					new Project("Server Automation", projectOwners.get(8).getID(), projectOwners.get(8).getName()))
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
	
	private static final String NAME = "John Test";
	private static final String BIO = "This is my bio.";
	
	public static final Type PROJECT_TYPE = new TypeToken<Map<UUID, Project>>(){}.getType();
	public static final Type DEVELOPER_TYPE = new TypeToken<Map<UUID, Developer>>(){}.getType();
	public static final Type PROJECTOWNER_TYPE = new TypeToken<Map<UUID, ProjectOwner>>(){}.getType();
	
	public <T> Map<UUID, T> getAll(Type type){
		Map<UUID, T> map = new HashMap<>();
		if(type == PROJECT_TYPE) {
			for(Project project : projects) {
				map.put(project.getID(), (T) project);
			}
		}
		return map;
	}
	
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
	
	public Developer getSingleDeveloper() {
		return this.developers.get(0);
	}
	
	public UUID update(Project project) {
		return project.getID();
	}
	
	public UUID update(User user) {
		return user.getID();
	}
	
	public UUID pushNew(Project project) {
		return project.getID();
	}
	
	public UUID pushNew(User user) {
		return user.getID();
	}
	
	public UUID pushNew(Offer offer) {
		return offer.getId();
	}
	
	public Project getProjectWithApplicants() {
		return this.projects.get(0);
	}
	
	public Developer getDeveloperWithActiveProject() {
		return this.developers.get(1);
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
		getDeveloperWithActiveProject().addActiveProjectId(projects.get(1).getID());
		getDeveloperWithActiveProject().addActiveProjectId(projects.get(2).getID());
		
		for(Project project : projects) {
			for(ProjectOwner po : projectOwners) {
				if(project.getProjectOwnerId().equals(po.getID())) {
					po.addProjectId(project.getID());
				}
			}
		}
	}
	
	private static final SecureRandom random = new SecureRandom();
	
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