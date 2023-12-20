package controller.page;
import controller.algorithm.JsonFileReadAlgorithm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import model.post.Tweet;

public class SearchPageController {

    @FXML
    private TableColumn<Tweet, String> colContent;

    @FXML
    private TableColumn<Tweet, String> colHashtag;

    @FXML
    private TableColumn<Tweet, String> colTime;

    @FXML
    private TableColumn<Tweet,String> colUserName;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private RadioButton radioBtnFilterContent;

    @FXML
    private RadioButton radioBtnFilterHastag;

    @FXML
    private TableView<Tweet> tblPost;

    @FXML
    private TextField tfQuery;
    
    @FXML
    public void initialize() {
    	colUserName.setCellValueFactory(new PropertyValueFactory<Tweet, String>("author"));
    	colTime.setCellValueFactory(new PropertyValueFactory<Tweet, String>("dateCreated"));
    	colHashtag.setCellValueFactory(new PropertyValueFactory<Tweet, String>("hashtag"));
    	colContent.setCellValueFactory(cellData -> {
    	    String fullContent = cellData.getValue().getContent();
    	    String formattedContent = (fullContent.replaceAll("\n", "\\n"));
    	        
    	    return javafx.beans.binding.Bindings.createObjectBinding(() -> formattedContent);
    	});
    	if(JsonFileReadAlgorithm.jsonReadAlgorithm() != null ) tblPost.setItems(JsonFileReadAlgorithm.jsonReadAlgorithm());
    }

    @FXML
    void btnCrawlPressed(ActionEvent event) {

    }

}
