package entity;

import java.util.UUID;

public abstract class User {
	
	private UUID id;
	private String name;
	private String bio;
	private String email;
	private String password;

	public User(String name, String bio, String email, String password){
		this.id = UUID.randomUUID();
		this.name = name;
		this.bio = bio;
		this.email = email;
		this.password = password;
	}
	
	public User(UUID uid, String name, String bio, String email){
		this.id = uid;
		this.name = name;
		this.bio = bio;
		this.email = email;
		this.password = null;
	}
	
	public String getPassword() {
		return this.password;
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
	
	@Override
	public String toString() {
		String result = "";
		result += String.format("id: %s%n", id.toString());
		result += String.format("name: %s%n", name);
		result += String.format("bio: %s%n", bio);
		result += String.format("email: %s%n", email);
		return result;
	}
}