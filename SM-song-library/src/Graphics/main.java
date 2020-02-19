package Graphics;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Graphics.Controller;

public class main extends Application {

	 @Override
	    public void start(Stage primaryStage) throws Exception{
	        FXMLLoader loader = new FXMLLoader();   
			loader.setLocation(
					getClass().getResource("/Graphics/Graphics.fxml"));
			AnchorPane root = (AnchorPane)loader.load();

			Controller listController = loader.getController();
			listController.start(primaryStage);

	        primaryStage.setTitle("Song Library");
	        primaryStage.setScene(new Scene(root, 700, 300));
	        primaryStage.show();
	    }


	    public static void main(String[] args) {
	        launch(args);
	    }
	
}
