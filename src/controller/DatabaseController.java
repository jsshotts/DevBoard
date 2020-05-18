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

public class DatabaseController {
	
	private Repository repo = new Repository();
	
	public List<Project> getAllProjects(){
		return repo.projects;
	}
	
	public List<Developer> getAllDevelopers() {
		return repo.developers;
	}
	
	public List<ProjectOwner> getAllProjectOwners(){
		return repo.projectOwners;
	}

    public static void sendGET(String GET_URL) throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		//con.setRequestMethod("GET");
		//con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}

	}
//    private static void sendPOST(String POST_URL) throws IOException {
//		URL obj = new URL(POST_URL);
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//		con.setRequestMethod("POST");
//
//		// For POST only - START
//		con.setDoOutput(true);
//		OutputStream os = con.getOutputStream();
//		os.write(POST_PARAMS.getBytes());
//		os.flush();
//		os.close();
//		// For POST only - END
//
//		int responseCode = con.getResponseCode();
//		System.out.println("POST Response Code :: " + responseCode);
//
//		if (responseCode == HttpURLConnection.HTTP_OK) { //success
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//
//			// print result
//			System.out.println(response.toString());
//		} else {
//			System.out.println("POST request not worked");
//		}
//	}
}