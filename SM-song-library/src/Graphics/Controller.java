package Graphics;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;



import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//song error before confirmation or after?
public class Controller {

    @FXML
    private ListView<String> songlist;
    @FXML
    private Label details;
    @FXML
    private AnchorPane hide_show;
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
    @FXML
    private Button Delete;
    @FXML
    private Button Edit;


    private ObservableList<String> obsList;
    private ObservableList<String> fullList; 
    
   
    public void start(Stage mainStage) {                
		// create an ObservableList 
		// from an ArrayList  
		obsList = FXCollections.observableArrayList(                               
				"Giants \t NY",                               
				"Patriots \t NE",
				"49ers \t LA ",
				"Rams \t IDK",
				"Packers \t GreenBay"); 
		fullList = FXCollections.observableArrayList(                               
				"Giants \t NY \t 2015 \t city",                               
				"Patriots \t NE \t 2016 \t boston",
				"49ers \t LA \t 2017 \t Hollywood",
				"Rams \t IDK \t 2018 \t DOOR",
				"Packers \t GreenBay \t 2019 \t Green"); 

		Collections.sort(obsList, String.CASE_INSENSITIVE_ORDER);
    	Collections.sort(fullList, String.CASE_INSENSITIVE_ORDER);
    	
		songlist.setItems(obsList); 
		songlist.getSelectionModel().select(0);
		details.setText(fullList.get(0));
	    
		 songlist
	        .getSelectionModel()
	        .selectedIndexProperty()
	        .addListener(
	           (obs, oldVal, newVal) -> {
	              if(newVal.intValue() != -1){
	            	  details.setText(fullList.get(newVal.intValue()));}});
	              
		 //Song.setVisible(false);
	}
    
    
    	

		
    
    

	@FXML
   public void addSong(ActionEvent event) {

    	Button b = (Button)event.getSource();
    	 if (b == Add) { 
    		
    		addSong();
    		
    	 }
    }
	
	  @FXML
	    void deleteSong(ActionEvent event) {
		  Button b = (Button)event.getSource();
	    	 if (b == Delete) { 
	    		
	    		deleteSong();
	    		
	    	 }
	    }
	    
	  
	  @FXML
	    void editSong(ActionEvent event) {

		  Button b = (Button)event.getSource();
	    	 if (b == Edit) { 
	    		//System.out.println("whats is up");
	    		//editSong();
	    		
	    	 }
	    }
	  
	  public void editSong() {
		  int index = songlist.getSelectionModel().getSelectedIndex();
		  
		  String tempString = fullList.get(index);
		  String song =  tempString.substring(0 , tempString.indexOf(' '));
		  tempString = tempString.substring(song.length()).stripLeading();
		  String artist = tempString.substring(0,tempString.indexOf(' '));
		  tempString = tempString.substring(artist.length()).stripLeading();
		  String year = tempString.substring(0,tempString.indexOf(' '));
		  tempString = tempString.substring(year.length()).stripLeading();
		  String album = tempString;
		  

		  Song.setText(song);
		  Artist.setText(artist);
		  Year.setText(year);
		  Album.setText(album);
		  
		  
		   song = Song.getText();
	       artist = Artist.getText();
	       year = Year.getText();
	       album = Album.getText();
		  
		  if(song.isBlank() || artist.isBlank()) {
	    		 Alert alert = new Alert(AlertType.ERROR);
	 	    	
		    	 alert.setTitle("Error Screen");
		    	 alert.setHeaderText(
		    	 "You need to enter both a song name and artist to add to the library");
		    	alert.showAndWait();
		    	return;
	    	}
		  
		  if(obsList.contains(song + "\t" + artist)) {
		    	Alert alert = new Alert(AlertType.ERROR);
		        	
		       	 alert.setTitle("Error Message");
		       	 alert.setHeaderText(
		       	 "This song and artist already exist in the library");
		       	 alert.showAndWait();
		       	 return;
		    	}
		  
		  obsList.remove(index);
   	      fullList.remove(index);
   	     songlist.getSelectionModel().select(-1);

      	obsList.add(song + "\t" + artist);
      	fullList.add(song + "\t" + artist + "\t" + year + "\t" + album);
      	
      	 
      	obsList.sort(String.CASE_INSENSITIVE_ORDER);
      	fullList.sort(String.CASE_INSENSITIVE_ORDER);
      	
      	 index = obsList.indexOf(song + "\t" + artist);
      	
      	
      	songlist.getSelectionModel().select(index);
		  
		  
	  }
	  
	  public void deleteSong() {
		  if(obsList.size() == 0) {
			  Alert alert = new Alert(AlertType.ERROR);
		    	
		    	 alert.setTitle("Error Screen");
		    	 alert.setHeaderText(
		    	 "List is empty");
		    
		    	 
		     alert.showAndWait();
		    	
			  return;//nothing to delete throw error?
		  }
		 int index = songlist.getSelectionModel().getSelectedIndex();
		  
		  Alert alert = new Alert(AlertType.CONFIRMATION);
	    	
	    	 alert.setTitle("Confirmation Screen");
	    	 alert.setHeaderText(
	    	 "Are you sure you want to delete the following song?");
	    
	    	 String content = songlist.getSelectionModel().getSelectedItem();
	    	 alert.setContentText(content);
	    			 //alert.showAndWait();
	    	Optional<ButtonType> result = alert.showAndWait();
	    	
	    	if(result.get() == ButtonType.OK) {
	    		
	    		
	        	   
	        	   obsList.remove(index);
	        	   fullList.remove(index);
	        	   //details.setText("");
	        	  songlist.getSelectionModel().select(-1);
	        	   if(obsList.size() == 0){
	        		   details.setText("");
	        	   }else if(obsList.size() == 1 || index == 0){
	        		   songlist.getSelectionModel().select(0);
	        	   }else if(obsList.size() == index) {
	        		   
	        		   songlist.getSelectionModel().select(index - 1);
	        	   }else{
	        		   
	        		   songlist.getSelectionModel().select(index);
	        	   }
	           
	    	}else {
	    		return;
	    	}
	              
		
		  
	  }
	  
	 
    
 
    private void addSong() {
    	 Alert alert = new Alert(AlertType.CONFIRMATION);
    	
    	 alert.setTitle("Confirmation Screen");
    	 alert.setHeaderText(
    	 "Are you sure you want to add the following song?");
    
    	 String content = "Song: " + Song.getText() + "\nArtist: " + Artist.getText();
    	 alert.setContentText(content);
    			 //alert.showAndWait();
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if(result.get() == ButtonType.OK) {
    		addSongToSongList();
    		
    	}
    	
    	Song.clear();
		Artist.clear();
		Year.clear();
		Album.clear();
    	
    }
    
    public void addSongToSongList() {
    	String song = Song.getText();
    	String artist = Artist.getText();
    	String year = Year.getText();
    	String album = Album.getText();
    	
    	if(song.isBlank() || artist.isBlank()) {
    		 Alert alert = new Alert(AlertType.ERROR);
 	    	
	    	 alert.setTitle("Error Screen");
	    	 alert.setHeaderText(
	    	 "You need to enter both a song name and artist to add to the library");
	    	alert.showAndWait();
	    	return;
    	}
    	
    	if(obsList.contains(song + "\t" + artist)) {
    	Alert alert = new Alert(AlertType.ERROR);
        	
       	 alert.setTitle("Error Message");
       	 alert.setHeaderText(
       	 "This song and artist already exist in the library");
       	 alert.showAndWait();
       	 return;
    	}
    	
    	
    	obsList.add(song + "\t" + artist);
    	fullList.add(song + "\t" + artist + "\t" + year + "\t" + album);
    	
    	 
    	obsList.sort(String.CASE_INSENSITIVE_ORDER);
    	fullList.sort(String.CASE_INSENSITIVE_ORDER);
    	
    	int index = obsList.indexOf(song + "\t" + artist);
    	
    	
    	songlist.getSelectionModel().select(index);
    	
    	
    }
    
    
    
   
    
    
    
    
    
    	 
}