package boundary;

import java.util.ArrayList;


import java.util.List;
import java.util.logging.Level;
import controller.SessionController;
import controller.Log;
import entity.Developer;
import entity.Filters.Language;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class DevProfile {
	
	private static final String WHITESPACE = "     ";
	
	@FXML
	private Label devName;
	
	@FXML
	private Label bio;
	
	@FXML
	private Label email;
	
	@FXML
	private Label languages;
	
	@FXML
	private Label experience;
	
	@FXML
	private Button back;
	
	static void swapTo(ActionEvent event)
	{
		try {
			BorderPane borderPane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
			Parent switchScreen = FXMLLoader.load(ClassLoader.getSystemResource(WindowManager.DEV_PROFILE_SCREEN));
			borderPane.setCenter(switchScreen);
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	public void populate(Developer dev) {
		devName.setText(dev.getName());
		email.setText(dev.getEmail());
		bio.setText(dev.getBio());
		languages.setText(langsToString(dev.getLanguages()));
		experience.setText(devExperienceToString(dev.getExperience()));
		if(SessionController.getInstance().isDeveloper())
			back.setVisible(false);
		else
			back.setVisible(true);
	}
	
	private String langsToString(List<Language> langs) {
		
		if (langs == null || langs.isEmpty())
			return "";
		
		String result = WHITESPACE;
		List<String> langsAsStr = new ArrayList<>();
		for (Language L : langs) {
			langsAsStr.add(L.toString());
		}
		result += String.join(", ", langsAsStr);
		return result;
	}
	
	private String devExperienceToString(List<String> expers) {
		
		if (expers == null || expers.isEmpty())
			return "";
		
		String result = WHITESPACE;
		result += String.join(", ", expers);
		return result;
	}
	
	public void back(ActionEvent event) {
		if(event.getSource() == back) {
			POMyProjects.swapTo(event);
		}
	}
}
