package controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import entity.Developer;
import entity.Project;
import entity.ProjectOwner;
import entity.Repository;
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
	
	private final Gson gson = new Gson();
	private Repository repo = new Repository();
	private static final String REQUESTPROPERTY = "X-HTTP-Method-Override";
	private final String BASEURL = "https://devboard-b0a1d.firebaseio.com/";
	public enum RequestType { GET, DELETE, PUT, PATCH }
	
	public List<Developer> getAllDevelopers() {
		return repo.developers;
	}
	
	public List<ProjectOwner> getAllProjectOwners(){
		return repo.projectOwners;
	}
	
	public Map<UUID, Project> getAllProjects()
	{
		String responseStr = sendHttpRequest(BASEURL + "Projects.json", RequestType.GET);
		Type type = new TypeToken<Map<UUID, Project>>(){}.getType();
		return gson.fromJson(responseStr, type);
	}
	
	public Project getProject(UUID projectId)
	{
		String responseStr = sendHttpRequest(BASEURL + "Projects/" + projectId + ".json", RequestType.GET);
		return gson.fromJson(responseStr, Project.class);
	}
	
	public UUID createProject(Project project)
	{
		UUID projectId = UUID.randomUUID();
		sendHttpRequest(BASEURL + "Projects/" + projectId + ".json", RequestType.PUT, project);
		return projectId;
	}
	
	private String sendHttpRequest(String url, RequestType type){
		try
		{
			URL target = new URL(url);
			HttpURLConnection con = (HttpURLConnection) target.openConnection();
			con.setRequestProperty(REQUESTPROPERTY, type.toString());
			return getJsonStrResponse(con);
		} catch (IOException e) {
			e.printStackTrace();
			return "Http Request Broke";
		}
	}
	
	private String sendHttpRequest(String url, RequestType type, Object obj) {
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
			return "Http Request Broke";
		}
	}
	
	private String getJsonStrResponse(HttpURLConnection con) throws IOException{
		
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