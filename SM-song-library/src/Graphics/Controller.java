package Graphics;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller {

	
	
    @FXML
    private ListView<?> songlist;

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

}