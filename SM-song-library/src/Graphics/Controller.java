package Graphics;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Controller {

    @FXML
    private ListView<?> songlist;

    @FXML
    private Label details;

    @FXML
    private Button Add;

    @FXML
    private Button Edit;

    @FXML
    private Button Delete;
    
    @FXML
    private Label Error;

}