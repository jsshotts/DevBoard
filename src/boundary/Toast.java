package boundary;

import java.util.logging.Level;

import controller.Log;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public final class Toast
{
	
	@FXML
	private Label toastMessage;
	
	@FXML
	private AnchorPane anchorPane;
	
	private int toastDelay = 2500; //2.5 seconds
	private int fadeInDelay = 500; //0.5 seconds
	private int fadeOutDelay= 500; //0.5 seconds
	
	public static Toast buildToast() {
		Toast toast = null;
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(WindowManager.TOAST_CARD));
			fxmlLoader.load();
			toast = fxmlLoader.<Toast>getController();
		}
		catch(Exception e) {
			Log.logger.log(Level.WARNING, e.getMessage());
		}
		return toast;
	}
	
    public void makeText(Stage ownerStage, String toastMsg)
    {
        Stage toastStage=new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);
       
        toastMessage.setText(toastMsg);
        anchorPane.setOpacity(0);
        
        Scene scene = new Scene(anchorPane);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.show();

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 1)); 
        fadeInTimeline.getKeyFrames().add(fadeInKey1);   
        fadeInTimeline.setOnFinished(ae -> 
        
            new Thread(() -> {
                try
                {
                    Thread.sleep(toastDelay);
                }
                catch (InterruptedException e)
                {
                	Log.logger.log(Level.WARNING, e.getMessage());
                	Thread.currentThread().interrupt();
                }
                   Timeline fadeOutTimeline = new Timeline();
                    KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay), new KeyValue (toastStage.getScene().getRoot().opacityProperty(), 0)); 
                    fadeOutTimeline.getKeyFrames().add(fadeOutKey1);   
                    fadeOutTimeline.setOnFinished(aeb -> toastStage.close()); 
                    fadeOutTimeline.play();
            }).start()
        ); 
        fadeInTimeline.play();
    }
}
