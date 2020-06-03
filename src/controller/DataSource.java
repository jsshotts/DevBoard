package controller;

import java.util.UUID;

public interface DataSource {
	
	public <T> T getOne(Class<T> cls, UUID id);
	public <T> T getOne(Class<T> cls, String key, String value);
}
