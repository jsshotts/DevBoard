package controller;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import entity.Developer;
import entity.Offer;
import entity.Project;
import entity.ProjectOwner;
import entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class DatabaseController {
	
	private static final Gson gson = new Gson();
	
	private static final String REQUESTPROPERTY = "X-HTTP-Method-Override";
	private static final String BASEURL = "https://devboard-b0a1d.firebaseio.com/";
	private static final String PROJECTS = "Projects/";
	private static final String OFFERS = "Offers/";
	private static final String DEVELOPERS = "Users/Developers/";
	private static final String PROJECTOWNERS = "Users/ProjectOwners/";
	private static final String JSON = ".json";
	
	private static final String ORDERBY_STRING = "?orderBy=\"";
	private static final String EQUALTO_STRING = "\"&equalTo=\"";
	private static final String QUOTE = "\"";
	private static final String EMPTYRESPONSE = "{}";
	
	public static final Type PROJECT_TYPE = new TypeToken<Map<UUID, Project>>(){}.getType();
	public static final Type DEVELOPER_TYPE = new TypeToken<Map<UUID, Developer>>(){}.getType();
	public static final Type PROJECTOWNER_TYPE = new TypeToken<Map<UUID, ProjectOwner>>(){}.getType();
	
	private enum RequestType { GET, DELETE, PUT, PATCH }
	
	public <T> Map<UUID, T> getAll(Type type)
	{
		String target = null;
		if (type.equals(PROJECT_TYPE))
			target = PROJECTS;
		if (type.equals(DEVELOPER_TYPE))
			target = DEVELOPERS;
		if (type.equals(PROJECTOWNER_TYPE))
			target = PROJECTOWNERS;
		
		String url = BASEURL + target + JSON;
		String responseStr = sendHttpRequest(url, RequestType.GET);
		
		if (responseStr == null)
		{
			return Collections.emptyMap();
		}
		return gson.fromJson(responseStr, type);
	}
	
	public <T> Map<UUID, T> getAll(Type type, String key, String value)
	{
		String target = null;
		if (type.equals(PROJECT_TYPE))
			target = PROJECTS;
		if (type.equals(DEVELOPER_TYPE))
			target = DEVELOPERS;
		if (type.equals(PROJECTOWNER_TYPE))
			target = PROJECTOWNERS;
		
		String url = BASEURL + target + JSON + ORDERBY_STRING + key + EQUALTO_STRING + value + QUOTE;
		String responseStr = sendHttpRequest(url, RequestType.GET);
		if (responseStr == null)
		{
			return Collections.emptyMap();
		}
		
		return gson.fromJson(responseStr, type);
	}
	
	public <T> T getOne(Class<T> cls, UUID id)
	{
		String target = null;
		if (cls.equals(Project.class))
			target = PROJECTS;
		if (cls.equals(ProjectOwner.class))
			target = PROJECTOWNERS;
		if (cls.equals(Developer.class))
			target = DEVELOPERS;
		if (cls.equals(Offer.class))
			target = OFFERS;
		
		String url = BASEURL + target + id + JSON;
		String responseStr = sendHttpRequest(url, RequestType.GET);
		
		if (responseStr == null)
		{
			return null;
		}
		return gson.fromJson(responseStr, cls);
	}
	
	public <T> T getOne(Class<T> cls, String key, String value)
	{
		Map<UUID, T> result = null;
		if (cls.equals(Project.class))
			result = this.getAll(PROJECT_TYPE, key, value);
		if (cls.equals(ProjectOwner.class))
			result = this.getAll(PROJECTOWNER_TYPE, key, value);
		if (cls.equals(Developer.class))
			result = this.getAll(DEVELOPER_TYPE, key, value);
		
		if (result == null || result.isEmpty())
			return null;
		
		return cls.cast(result.values().iterator().next());
	}
	
	public UUID update(Project project)
	{
		String url = BASEURL + PROJECTS + project.getID() + JSON;
		return sendHttpRequest(url, RequestType.PATCH, project) == null ? null : project.getID();
	}
	
	public UUID update(User user)
	{
		String target = null;
		if (user instanceof Developer)
			target = DEVELOPERS;
		if (user instanceof ProjectOwner)
			target = PROJECTOWNERS;
		
		String url = BASEURL + target + user.getID() + JSON;
		return sendHttpRequest(url, RequestType.PATCH, user) == null ? null : user.getID();
	}

	public UUID pushNew(Project project)
	{
		String url = BASEURL + PROJECTS + project.getID() + JSON;
		return sendHttpRequest(url, RequestType.PUT, project) == null ? null : project.getID();
	}
	
	public UUID pushNew(User user)
	{
		String target = null;
		if (user instanceof Developer)
			target = DEVELOPERS;
		if (user instanceof ProjectOwner)
			target = PROJECTOWNERS;
		
		String url = BASEURL + target + user.getID() + JSON;
		return sendHttpRequest(url, RequestType.PUT, user) == null ? null : user.getID();
	}
	
	public UUID pushNew(Offer offer)
	{	
		String url = BASEURL + OFFERS + offer.getId() + JSON;
		return sendHttpRequest(url, RequestType.PUT, offer) == null ? null : offer.getId();
	}
	
	public boolean deleteOffer(UUID uid) {
		
		Offer offer = getOne(Offer.class, uid);
		if(offer == null) {
			return false;
		}
		
		String url = BASEURL + OFFERS + offer.getId() + JSON;
		String responseStr = sendHttpRequest(url, RequestType.DELETE);
		
		Developer dev = getOne(Developer.class, offer.getDeveloperId());
		dev.removeOfferId(offer.getId());
		
		Project project = getOne(Project.class, offer.getProjectId());
		if(project.getPendingOfferId().equals(uid)) {
			project.setPendingOfferId(null);
		}
		
		pushNew(dev);
		pushNew(project);
		
		return responseStr != null;
	}
	
	public <T> UUID delete(Class<T> cls, UUID id)
	{
		String target = null;
		if (cls.equals(Project.class))
			target = PROJECTS;
		if (cls.equals(ProjectOwner.class))
			target = PROJECTOWNERS;
		if (cls.equals(Developer.class))
			target = DEVELOPERS;
		if (cls.equals(Offer.class))
			target = OFFERS;
		
		String url = BASEURL + target + id + JSON;
		String responseStr = sendHttpRequest(url, RequestType.DELETE);
		
		if (responseStr == null)
		{
			return null;
		}
		return id;
	}
	
	private String sendHttpRequest(String url, RequestType type)
	{
		try
		{
			URL target = new URL(url);
			HttpURLConnection con = (HttpURLConnection) target.openConnection();
			con.setRequestProperty(REQUESTPROPERTY, type.toString());
			return getJsonStrResponse(con);
		} catch (IOException e) {
			Log.logger.log(Level.WARNING, e.getMessage());
			return null;
		}
	}
	
	private String sendHttpRequest(String url, RequestType type, Object obj) 
	{
		try
		{
			URL target = new URL(url);
			HttpURLConnection con = (HttpURLConnection) target.openConnection();
			con.setRequestProperty(REQUESTPROPERTY, type.toString());
			
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(gson.toJson(obj).getBytes());
			os.flush();
			os.close();
	
			return getJsonStrResponse(con);
		} catch (IOException e) {
			Log.logger.log(Level.WARNING, e.getMessage());
			return null;
		}
	}
	
	private String getJsonStrResponse(HttpURLConnection con) throws IOException
	{	
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String inputLine;
			StringBuilder response = new StringBuilder();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
			if (response.toString().equals(EMPTYRESPONSE)) 
				return null;
			return response.toString();
		}
		throw new IOException("Http request failed with reponse code: " + responseCode);
	}
 
}