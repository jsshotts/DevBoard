package entity;

import java.util.UUID;

public abstract class User {
	
	private UUID id;
	private String name;
	private String bio;

	public User(String name, String bio){
		this.id = UUID.randomUUID();
		this.name = name;
		this.bio = bio;
	}
	
	public UUID getID() {
		return this.id;
	}
}