package controller.page;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;

public class SearchPageController {
	//private String[] postList;
    public SearchPageController() {
		super();
	}
    
    
    public void setData(List<String> listItems) {
        postList.getItems().addAll(listItems);
    }
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup searchChoice;


    @FXML
    private ListView<String> postList;
    
    @FXML
    void initialize() {
        assert searchChoice != null : "fx:id=\"searchChoice\" was not injected: check your FXML file 'SearchPageView.fxml'.";
    }

}
