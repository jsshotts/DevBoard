package entity;

import java.util.ArrayList;
import java.util.List;

public class ProjectOwner extends User{
	
	public List<Project> projects;
	
	public ProjectOwner() {
		projects = new ArrayList<>();
	}

}