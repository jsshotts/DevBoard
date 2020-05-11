package entity;

import java.util.UUID;

public abstract class User {
	
	private String id;
	private String name;
	public String bio;

	public User(String name, String bio){
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.bio = bio;
	}
	
	public String getID() {
		return this.id;
	}
}