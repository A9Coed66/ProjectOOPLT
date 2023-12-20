package controller.page;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import model.post.Tweet;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchPageControllerv1 {
	//private String[] postList;
    private ObservableList<Tweet> listItems = FXCollections.observableArrayList();
    public SearchPageControllerv1() {
		super();
	}
    @FXML
    private TableView<Tweet> tblData;

    @FXML
    private TableColumn<String, String> authorCol;

    @FXML
    private TableColumn<String, String> contentCol;

    @FXML
    private TableColumn<String, String> timeCol;

    @FXML
    private ToggleGroup searchChoice;


    @FXML
    void initialize() {
        authorCol.setCellValueFactory(new PropertyValueFactory<String, String>("author"));
        contentCol.setCellValueFactory(new PropertyValueFactory<String, String>("content"));
        timeCol.setCellValueFactory(new PropertyValueFactory<String, String>("dateCreated"));

        tblData.setItems(listItems);
    }

    public SearchPageControllerv1(ObservableList<Tweet> listItems){
        this.listItems = listItems;
    }

}
