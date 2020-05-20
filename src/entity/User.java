package entity;

import java.util.UUID;

public abstract class User {
	
	private UUID id;
	private String name;
	private String bio;
	private String email;

	public User(String name, String bio, String email){
		this.id = UUID.randomUUID();
		this.name = name;
		this.bio = bio;
		this.email = email;
	}
	
	public UUID getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getBio() {
		return this.bio;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}