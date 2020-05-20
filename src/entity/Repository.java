package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Dummy Data class to be used until we have Firebase fully functional
public class Repository {
	
	public static List<Developer> developers = new ArrayList<>(
			Arrays.asList(
					new Developer("Joe Python", "I am a Python Developer", "test4@gmail.com"),
					new Developer("Fred Java", "I am a Java Developer", "test5@gmail.com"),
					new Developer("Bob John", "I am an IOS developer", "test6@gmail.com"),
					new Developer("John Kotlin", "I am an Android Developer", "test7@gmail.com"))
			);
	
	public static List<ProjectOwner> projectOwners = new ArrayList<>(
			Arrays.asList(
					new ProjectOwner("Flex Technologies", "We are a fitness company", "test8@gmail.com"),
					new ProjectOwner("Proactive Co", "We sell local art to the community", "test9@gmail.com"),
					new ProjectOwner("Better Italian", "We make the best Italian food in the county", "test10@gmail.com"),
					new ProjectOwner("Kayak Today", "We rent kayaks to the community", "test11@gmail.com"))
			);
	
	public static List<Project> projects = new ArrayList<>(
			Arrays.asList(
					new Project("IOS App", projectOwners.get(0).getID()),
					new Project("Android App", projectOwners.get(0).getID()),
					new Project("Warehouse Automation", projectOwners.get(1).getID()),
					new Project("Linux Script", projectOwners.get(0).getID()),
					new Project("Windows Application", projectOwners.get(0).getID()),
					new Project("Warehouse Automation", projectOwners.get(1).getID()),
					new Project("IOS App", projectOwners.get(0).getID()),
					new Project("Linux Script", projectOwners.get(0).getID()),
					new Project("Warehouse Automation", projectOwners.get(1).getID()),
					new Project("Windows Application", projectOwners.get(0).getID()),
					new Project("Android App", projectOwners.get(0).getID()),
					new Project("Warehouse Automation", projectOwners.get(1).getID()),
					new Project("Linux Script", projectOwners.get(0).getID()),
					new Project("Windows Application", projectOwners.get(0).getID()),
					new Project("Warehouse Automation", projectOwners.get(1).getID()),
					new Project("IOS App", projectOwners.get(0).getID()),
					new Project("Windows Application", projectOwners.get(0).getID()),
					new Project("Warehouse Automation", projectOwners.get(1).getID()))
			);
	
}