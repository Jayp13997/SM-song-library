/*
 * 
 * Jay Patel             netid: jsp202
 * Anhad Bir Singh       netid: as2816
 * 
 */



package Graphics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

import javafx.collections.FXCollections;
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
	    private Button Add;

	    @FXML
	    private Button Delete;

	    @FXML
	    private Button Edit;

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
	    private Button Ok;

	    @FXML
	    private Button Cancel;
	    boolean add = false;
	    boolean edit = false;


    private ObservableList<String> obsList = null;
    private ObservableList<String> fullList = null; 
    
   
    public void start(Stage mainStage) throws FileNotFoundException {                
		// create an ObservableList 
		// from an ArrayList  
    	
    	File newFile = new File("./src/Graphics/Songs");

        if (newFile.length() == 0) {

        	hide_show.setVisible(false);
        	Edit.setVisible(false);
        	Delete.setVisible(false);
        }
        else{
    	
    	Scanner scanner = null;
    	try {
    		scanner = new Scanner(new File("./src/Graphics/Songs"));
    	} 
    	
    	catch(FileNotFoundException e) {
            System.out.println("Couldn't find the file");   
        }
    	while (scanner.hasNextLine()) {
    	   String line = scanner.nextLine();
    	   String[] newline = line.split("_");
    	   //System.out.println(newline[0]);
    	   //System.out.println(newline[1]);
    	   String song_artist = newline[0] + " \t " + newline[1];
    	   String full_song = "";
    	   for(int i = 0; i < newline.length; i++) {
    		   full_song += newline[i] + " \t ";
    	   }
    	   full_song = full_song.substring(0, full_song.length()-3);
    	   if (fullList == null) { 
    		   fullList = FXCollections.observableArrayList(full_song);
    		   obsList = FXCollections.observableArrayList(song_artist);
    	   }
    	   else {
    		   fullList.addAll(FXCollections.observableArrayList(full_song));
    		   obsList.addAll(FXCollections.observableArrayList(song_artist));
    	   }
    	}
    	scanner.close();
    	//System.out.println(fullList.toString());
    	//System.out.println(obsList.toString());
    	         
        
		Collections.sort(obsList, String.CASE_INSENSITIVE_ORDER);
    	Collections.sort(fullList, String.CASE_INSENSITIVE_ORDER);
    	
		songlist.setItems(obsList); 
		songlist.getSelectionModel().select(0);
		details.setText(fullList.get(0));
	    
		
	              
		 hide_show.setVisible(false);
		 
        }	
        
        songlist
        .getSelectionModel()
        .selectedIndexProperty()
        .addListener(
           (obs, oldVal, newVal) -> {
              if(newVal.intValue() != -1){
            	  details.setText(fullList.get(newVal.intValue()));}});
	}
    
    
    	

		
    @FXML
    void cancel(ActionEvent event) {
    	Button b = (Button)event.getSource();
   	 	if (b == Cancel){ 
   	 	 Song.clear();
		 Artist.clear();
		 Year.clear();
		 Album.clear();
		 hide_show.setVisible(false);
		 return;
   	 	}
    }

    @FXML
    void confirm(ActionEvent event) throws IOException {
    	Button b = (Button)event.getSource();
    	 if (b == Ok && add == true){ 
    		 addSongToSongList();
    		 Song.clear();
     		 Artist.clear();
     		 Year.clear();
     		 Album.clear();
     		hide_show.setVisible(false);
     		add = false;
     		if(fullList.size() != 0) {
     			Edit.setVisible(true);
     			Delete.setVisible(true);
     		}
    	 }else if(b == Ok && edit == true) {
    		 editSongtoSongList();
    		 Song.clear();
     		 Artist.clear();
     		 Year.clear();
     		 Album.clear();
     		hide_show.setVisible(false);
     		edit = false;
    	 }
    }

   

	@FXML
   public void addSong(ActionEvent event) {

    	Button b = (Button)event.getSource();
    	 if (b == Add) { 
    		 hide_show.setVisible(true);
    		 add = true;
    	 }
    }
	
	  @FXML
	    void deleteSong(ActionEvent event) throws IOException {
		  Button b = (Button)event.getSource();
	    	 if (b == Delete) { 
	    		
	    		deleteSong();
	    		hide_show.setVisible(false);
	    		
	    	 }
	    }
	    
	  
	  @FXML
	    void editSong(ActionEvent event) {
		  int index = songlist.getSelectionModel().getSelectedIndex();
		  
		  Button b = (Button)event.getSource();
	    	 if (b == Edit && !obsList.isEmpty()) { 
	    		hide_show.setVisible(true);
	    		edit = true;
	    	 
	   		  
	   		  String tempString = fullList.get(index);
	   		  String song =  tempString.substring(0 , tempString.indexOf('	'));
	   		  song = song.strip();
	   		  tempString = tempString.substring(song.length()).stripLeading();
	   		  String artist = "";
	   		  try {
	   			  artist = tempString.substring(0,tempString.indexOf('	'));
	   			  tempString = tempString.substring(artist.length()).stripLeading();
	   		  }
	   		  catch (Exception e) {
	   			  artist = tempString;
	   			  tempString = tempString.substring(artist.length()).stripLeading();
	   		  }
	   		  artist = artist.strip();
	   		 String year = "";
	   		  try {
	   			  year = tempString.substring(0,tempString.indexOf('	'));
	   			  tempString = tempString.substring(year.length()).stripLeading();
	   		  }
	   		  catch (Exception e) {
	   			  year = tempString;
	   			tempString = tempString.substring(year.length()).stripLeading();
	   		  }
	   		  year = year.strip();
	   		String album = "";
	   		  try {
	   			  album = tempString.substring(0,tempString.indexOf('	'));
	   			  tempString = tempString.substring(album.length()).stripLeading();
	   		  }
	   		  catch (Exception e) {
	   			  album = tempString;
	   			tempString = tempString.substring(album.length()).stripLeading();
	   		  }
	   		  album = album.strip();
	   		
	   		  Song.setText(song);
	   		  Artist.setText(artist);
	   		  Year.setText(year);
	   		  Album.setText(album);
	   		  
	    	 }else if(obsList.isEmpty()){
	    		 Alert alert = new Alert(AlertType.ERROR);
			    	
		    	 alert.setTitle("Error Screen");
		    	 alert.setHeaderText(
		    	 "List is empty");
		    
		    	 
		    	 alert.showAndWait();
		    	
		    	 return;
	    		 
	    	 }
	    }
	  
	  public void  editSongtoSongList() throws IOException{
		  int index = songlist.getSelectionModel().getSelectedIndex();
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
	    	
	    	for(int i = 0; i < obsList.size(); i++) {
	    		if(i != index) {
	    		 String temp = obsList.get(i).replaceAll(" ", "");
	    		 temp = temp.replaceAll("\t", "");
	    	 
	    		 if(temp.equalsIgnoreCase(song + artist)){
				
			 
	    	Alert alert = new Alert(AlertType.ERROR);
	        	
	       	 alert.setTitle("Error Message");
	       	 alert.setHeaderText(
	       	 "This song and artist already exist in the library");
	       	 alert.showAndWait();
	       	 return;
	    		 }
	    	}
	    	 }
	    	
	    	
	    	
	    	
	    	//System.out.println("DELETE THIS: " + fullList.get(index).toString());
    		String test = fullList.get(index).toString().replaceAll(" 	 ", "_");
    		//System.out.println(test);
    		while(test.charAt(test.length()-1) == '_') {
        		test = test.substring(0, test.length()-1);
        	}
    		
    		
    	//	System.out.println("TEST IS: " +test);
    		
    	
    		String filename = "./src/Graphics/Songs";
    		File songFile = new File(filename);
    		BufferedReader reader = new BufferedReader(new FileReader(songFile));
    	
    		String file = "";
    		String line = "";
    		line = reader.readLine();
    		while(line != null) {
    			//System.out.println("LINE IS: " + line);
    		    // trim newline when comparing with lineToRemove
    		    String newline = line.trim();
    		    if(newline.equals(test) == false) {
    		    	//System.out.println("LINE IS: " + newline);
    		    	file = file + newline + "\n";
    		    }
    		    line = reader.readLine();
    		}
    		file += song + "_" + artist + "_" + year + "_" + album;
    		while(file.charAt(file.length()-1) == '_') {
        		file = file.substring(0, file.length()-1);
        	}
    		System.out.println("FILE IS: " + file);
    		//writer.close(); 
    		reader.close(); 
    		//boolean successful = tempFile.renameTo(songFile);
    		new FileWriter(filename, false).close();
    		
    		file = file.trim();
    		BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true)); //Set true for append mode

    		writer.write(file);
    		writer.close();
    		
    		
	    	
	    	
	    	
	    	obsList.remove(index);	
	    	obsList.add(song + " \t " + artist);
	    	fullList.remove(index);
	    	fullList.add(song + " \t " + artist + " \t " + year + " \t " + album);
	    	
	    	 
	    	obsList.sort(String.CASE_INSENSITIVE_ORDER);
	    	fullList.sort(String.CASE_INSENSITIVE_ORDER);
	    	
	    	 index = obsList.indexOf(song + " \t " + artist);
	    	
	    	
	    	
	    	songlist.getSelectionModel().select(index);
	    	
	  }
	  
	  
	  public void deleteSong() throws IOException {
		  if(obsList.size() == 0) {
			  Alert alert = new Alert(AlertType.ERROR);
		    	
		    	 alert.setTitle("Error Screen");
		    	 alert.setHeaderText(
		    	 "List is empty");
		    
		    	 
		     alert.showAndWait();
		    	
			  return;//nothing to delete throw error?
		  }
		 int index = songlist.getSelectionModel().getSelectedIndex();
		// System.out.println("Index is: " + index);
		  
		  Alert alert = new Alert(AlertType.CONFIRMATION);
	    	
	    	 alert.setTitle("Confirmation Screen");
	    	 alert.setHeaderText(
	    	 "Are you sure you want to delete the following song?");
	    
	    	 String content = songlist.getSelectionModel().getSelectedItem();
	    	 alert.setContentText(content);
	    			 //alert.showAndWait();
	    	Optional<ButtonType> result = alert.showAndWait();
	    	
	    	if(result.get() == ButtonType.OK) {
	    		
	    		
	    		
	    		
	    		
	    		//System.out.println("DELETE THIS: " + fullList.get(index).toString());
	    		String test = fullList.get(index).toString().replaceAll(" 	 ", "_");
	    		
	    		while(test.charAt(test.length()-1) == '_') {
	        		test = test.substring(0, test.length()-2);
	        	}
	    		//System.out.println("TEST IS: " +test);
	    	
	    		String filename = "./src/Graphics/Songs";
	    		File songFile = new File(filename);
	    		BufferedReader reader = new BufferedReader(new FileReader(songFile));
	    	
	    		String file = "";
	    		String line = "";
	    		line = reader.readLine();
	    		while(line != null) {
	    			//System.out.println("LINE IS: " + line);
	    		    // trim newline when comparing with lineToRemove
	    		    String newline = line.trim();
	    		    if(newline.equals(test) == false) {
	    		    	//System.out.println("LINE IS: " + newline);
	    		    	file = file + newline + "\n";
	    		    }
	    		    line = reader.readLine();
	    		}
	    		//System.out.println("FILE IS: " + file);
	    		//writer.close(); 
	    		reader.close(); 
	    		//boolean successful = tempFile.renameTo(songFile);
	    		new FileWriter(filename, false).close();
	    		file = file.trim();
	    		BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true)); //Set true for append mode
   
	    		writer.write(file);
	    		writer.close();
	    		
	    		
	        	   
	        	   obsList.remove(index);
	        	   fullList.remove(index);
	        	   
	        	   System.out.println("INDEX in delete is: "+ index);
	        	
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
	              
		return;
		  
	  }
	  
	 

    
    public void addSongToSongList() throws IOException {
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
    	
    	
    	
    	
    	if(obsList != null) {
    		for(int i = 0; i < obsList.size(); i++) {
    			String temp = obsList.get(i).replaceAll(" 	 ", "");
    			// temp = temp.replaceAll("\t", "");
   	 
    			if(temp.equalsIgnoreCase(song + artist)) {

   		
    				Alert alert = new Alert(AlertType.ERROR);
       	
    				alert.setTitle("Error Message");
    				alert.setHeaderText(
      			 "This song and artist already exist in the library");
    				alert.showAndWait();
    				return;
    			}
    		}
    	}
    	
    	
    	
    	if (fullList == null && obsList == null) { 
   		   fullList = FXCollections.observableArrayList(song + " \t " + artist + " \t " + year + " \t " + album);
   		   obsList = FXCollections.observableArrayList(song + " \t " + artist);
   	   	}
   	   	else {
   	   	obsList.addAll(FXCollections.observableArrayList(song + " \t " + artist));
   		fullList.addAll(FXCollections.observableArrayList(song + " \t " + artist + " \t " + year + " \t " + album));
   	   	}
    //	obsList.add(song + " \t " + artist);
    //	fullList.add(song + " \t " + artist + " \t " + year + " \t " + album);
    	//System.out.println("Full List: " + fullList.toString());
    	//System.out.println("obs List:" + obsList.toString());
    	
    	String addSong = song + "_" + artist + "_" + year + "_" + album;
    	
    	while(addSong.charAt(addSong.length()-1) == '_') {
    		addSong = addSong.substring(0, addSong.length()-1);
    	}
    	String filename = "./src/Graphics/Songs";
		
    	
    	File newFile = new File(filename);

        if (newFile.length() == 0) {
        	
        	BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));  //Set true for append mode
            
            
            // writer.newLine();   //Add new line
             writer.write(addSong);
             writer.close();
             songlist.setItems(obsList);
             songlist.getSelectionModel().select(0);
     		 details.setText(fullList.get(0));
        	
        }
        else {
        	BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));  //Set true for append mode
        
        
        	writer.newLine();   //Add new line
        	writer.write(addSong);
        	writer.close();
    	
        }
    	obsList.sort(String.CASE_INSENSITIVE_ORDER);
    	fullList.sort(String.CASE_INSENSITIVE_ORDER);
    	
    	int index = obsList.indexOf(song + " \t " + artist);
    	
    	//System.out.println("INDEX is " + index);
    	
    	
    	songlist.getSelectionModel().select(index);
    	
    	return;
    }
    
    
    
   
    
    
    
    
    
    	 
}