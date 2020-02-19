package Graphics;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {

    @FXML
    private TextField Song;

    @FXML
    private TextField Artist;

    @FXML
    private TextField Year;

    @FXML
    private TextField Album;
    
    @FXML
    private Button Add;
    
    public void Add(ActionEvent e, Stage mainStage) {
    	Button b = (Button)e.getSource();
    	 if (b == Add) { 
    		
    	
    		 Song.setText("Hello"); 
    		
    		 
    	 }else {
    		 Song.setText("Bye");
    	 }
    }

}