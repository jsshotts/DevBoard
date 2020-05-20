package controller;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import entity.Developer;
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
	private static final String DEVELOPERS = "Users/Developers/";
	private static final String PROJECTOWNERS = "Users/ProjectOwners/";
	private static final String JSON = ".json";
	
	private static final String ORDERBY_STRING = "?orderBy=\"";
	private static final String EQUALTO_STRING = "\"&equalTo=\"";
	private static final String QUOTE = "\"";
	
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
			target = PROJECTOWNERS;
		if (type.equals(PROJECTOWNER_TYPE))
			target = DEVELOPERS;
		
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
			target = PROJECTOWNERS;
		if (type.equals(PROJECTOWNER_TYPE))
			target = DEVELOPERS;
		
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
		String target = null;
		if (cls.equals(Project.class))
			target = PROJECTS;
		if (cls.equals(ProjectOwner.class))
			target = PROJECTOWNERS;
		if (cls.equals(Developer.class))
			target = DEVELOPERS;
		
		String url = BASEURL + target + JSON + ORDERBY_STRING + key + EQUALTO_STRING + value + QUOTE;
		System.out.println(url);
		String responseStr = sendHttpRequest(url, RequestType.GET);
		
		if (responseStr == null)
		{
			return null;
		}
		return gson.fromJson(responseStr, cls);
	}

	public UUID pushNew(Project project)
	{
		UUID projectId = UUID.randomUUID();
		String url = BASEURL + PROJECTS + projectId + JSON;
		return sendHttpRequest(url, RequestType.PUT, project) == null ? null : projectId;
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
	
	private String sendHttpRequest(String url, RequestType type)
	{
		try
		{
			URL target = new URL(url);
			HttpURLConnection con = (HttpURLConnection) target.openConnection();
			con.setRequestProperty(REQUESTPROPERTY, type.toString());
			return getJsonStrResponse(con);
		} catch (IOException e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			return response.toString();
		}
		throw new IOException("Http request failed with reponse code: " + responseCode);
	}
 
}