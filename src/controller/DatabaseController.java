package controller;

import java.util.List;

import entity.Developer;
import entity.Project;
import entity.ProjectOwner;
import entity.Repository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;


public class DatabaseController {
	
	private Repository repo = new Repository();
	private static final String REQUESTPROPERTY = "X-HTTP-Method-Override";
	public enum RequestType { GET, DELETE, PUT, PATCH }
	
	public List<Project> getAllProjects(){
		return repo.projects;
	}
	
	public List<Developer> getAllDevelopers() {
		return repo.developers;
	}
	
	public List<ProjectOwner> getAllProjectOwners(){
		return repo.projectOwners;
	}
	
	public static void sendHttpRequest(String url, RequestType type) throws IOException {
		
		URL target = new URL(url);
		HttpURLConnection con = (HttpURLConnection) target.openConnection();
		con.setRequestProperty(REQUESTPROPERTY, type.toString());
		System.out.println(getJsonStrResponse(con));
	}
	
	public static void sendHttpRequest(String url, RequestType type, Object obj) throws IOException {
		
		URL target = new URL(url);
		HttpURLConnection con = (HttpURLConnection) target.openConnection();
		con.setRequestProperty(REQUESTPROPERTY, type.toString());
		
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		Gson gson = new Gson();
		os.write(gson.toJson(obj).getBytes());
		os.flush();
		os.close();

		System.out.println(getJsonStrResponse(con));
	}
	
	public static String getJsonStrResponse(HttpURLConnection con) throws IOException{
		
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
		return Integer.toString(responseCode);
	}
 
}