package controller;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;

import entity.Offer;
import entity.Project;
import entity.User;

public interface DataSource {
	
	public <T> Map<UUID, T> getAll(Type type);
	public <T> T getOne(Class<T> cls, UUID id);
	public <T> T getOne(Class<T> cls, String key, String value);
	public UUID update(Project project);
	public UUID update(User user);
	public UUID pushNew(Project project);
	public UUID pushNew(User user);
	public UUID pushNew(Offer offer);
}
