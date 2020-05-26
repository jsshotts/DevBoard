package entity;

import java.util.UUID;

public class Offer {
	
	public static final int ACCEPTED = 0;
	public static final int DECLINED = 1;
	public static final int PENDING = 2;

	private UUID id;
	private UUID projectId;
	private UUID developerId;
	private String message;
	
	public Offer(UUID projectId, UUID developerId, String message) {
		this.id = UUID.randomUUID();
		this.projectId = projectId;
		this.developerId = developerId;
		this.message = message;
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public UUID getProjectId() {
		return this.projectId;
	}
	
	public UUID getDeveloperId() {
		return this.developerId;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
}