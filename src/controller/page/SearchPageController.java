package controller.page;
import java.io.IOException;

import controller.algorithm.JsonFileReadAlgorithm;
import controller.post.TweetViewController;
import data.crawl.TwitterCrawlerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.post.Tweet;

public class SearchPageController {
	
	public SearchPageController(TableView<Tweet> tblPost) {
		this.tblPost = tblPost;
	}
	
	public SearchPageController() {

	}
	
	 @FXML
	 void trendingButtonPressed(ActionEvent event) {

	 }
	    
	 @FXML
	 void btnCrawlPressed(ActionEvent event) throws IOException {
	    final String FXML_FILE_PATH = "/data/crawl/TwitterCrawlerView.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_FILE_PATH));
		TwitterCrawlerController twitterCrawlerController = new TwitterCrawlerController(tblPost, colContent, colHashtag, colTime, colUserName);
		fxmlLoader.setController(twitterCrawlerController);
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Crawler");
		stage.setScene(new Scene(root));
		stage.show();
	 }

	 @FXML
	 void detailButtonPressed(ActionEvent event) throws IOException {
		Tweet tweet = tblPost.getSelectionModel().getSelectedItem();
		
//		System.out.println(tweet.getAuthor());
//		System.out.println("--------------------------------------------------------------------------------------------");
//		System.out.println(tweet.getAvatarUrl());
//		System.out.println("--------------------------------------------------------------------------------------------");
//		System.out.println(tweet.getContent());
//		System.out.println("--------------------------------------------------------------------------------------------");
//		System.out.println(tweet.getDateCreated());
//		System.out.println("--------------------------------------------------------------------------------------------");
//		System.out.println(tweet.getHashtag());
//		System.out.println("--------------------------------------------------------------------------------------------");
//		System.out.println(tweet.getImageUrl());
//		System.out.println("--------------------------------------------------------------------------------------------");
//		System.out.println(tweet.getLike());
//		System.out.println("--------------------------------------------------------------------------------------------");
//		System.out.println(tweet.getReply());
//		System.out.println("--------------------------------------------------------------------------------------------");
//		System.out.println(tweet.getRetweet());
//		System.out.println("--------------------------------------------------------------------------------------------");
//		System.out.println(tweet.getUrl());
		
		final String TWITTER_POST_FXML_FILE_PATH = "/view/post/TweetView.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(TWITTER_POST_FXML_FILE_PATH));
		TweetViewController tweetViewController = new TweetViewController(tweet, 180);
		fxmlLoader.setController(tweetViewController);
		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		tweetViewController.setData(tweet, true);
		stage.setTitle("Twitter");
		stage.setScene(new Scene(root));
		stage.show();
	 }
	    
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
    private Button detailButton;
 
    @FXML
    private Button tredingButton;
    
    
}
