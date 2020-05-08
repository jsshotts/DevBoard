package Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Repository {
	
	public static List<Developer> developers = new ArrayList<>(
			Arrays.asList(new Developer())
			);
	
	public static List<ProjectOwner> projectOwners = new ArrayList<>(
			Arrays.asList(new ProjectOwner())
			);
	
	public static List<User> users = new ArrayList<>(
			Arrays.asList(new ProjectOwner(), new Developer())
			);
	
}