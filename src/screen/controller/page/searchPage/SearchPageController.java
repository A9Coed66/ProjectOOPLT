package screen.controller.page.searchPage;
import java.io.IOException;

import screen.controller.page.analyzePage.CorrelationAnalysisControllerv1;
import screen.controller.post.BlogViewController;
import screen.controller.post.TweetViewController;
import data.connector.BlogPostDB;
import data.connector.IPostDB;
import data.connector.TweetDB;
import data.crawl.TwitterCrawlerController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.post.BlogPost;
import model.post.Post;
import model.post.Tweet;
import javafx.collections.transformation.FilteredList;

public class SearchPageController {
	private IPostDB postDB;
	private TweetDB tweetDB=  new TweetDB();
	private BlogPostDB blogPostDB = new BlogPostDB();
	//**
	//Constructor
	//**
	
	public SearchPageController(TableView<Post> tblPost) {
		this.tblPost = tblPost;
		this.postDB =  new TweetDB();
		listItems = new FilteredList<Post>(postDB.init("data/json/post/nitter/tweet_1d_top10collection_timecrawl-20231222_140522.json"));
	}
	
	public SearchPageController() {
		this.postDB =  new TweetDB();
		listItems = new FilteredList<Post>(postDB.init("data/json/post/nitter/tweet_1d_top10collection_timecrawl-20231222_140522.json"));
		this.blogPostDB.init("data/csv/blogPostNew.csv");
		this.tweetDB.init("data/json/post/nitter/tweet_1d_top10collection_timecrawl-20231222_140522.json");
	}
	
	//**
	//Initialization
	//**
		    
	@FXML
	public void initialize() {
		colUserName.setCellValueFactory(new PropertyValueFactory<Post, String>("author"));
		colTime.setCellValueFactory(new PropertyValueFactory<Post, String>("dateCreated"));
		colHashtag.setCellValueFactory(new PropertyValueFactory<Post, String>("hashtag"));
		colContent.setCellValueFactory(cellData -> {
			String fullContent = cellData.getValue().getContent();
		    String formattedContent = (fullContent.replaceAll("\n", "\\n"));
		    	        
		    return javafx.beans.binding.Bindings.createObjectBinding(() -> formattedContent);
		});
		
		if(listItems != null ) tblPost.setItems(listItems);
		
//		tfQuery.textProperty().addListener(new ChangeListener<String>(){
//
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				showFilteredPost(newValue);
//			}
//        });
	}
	
	//**
	//Action Performed
	//**
	

    @FXML
    void searching(KeyEvent event) {
    	tfQuery.textProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				showFilteredPost(newValue);
			}
        });
    }
    
	@FXML
	void trendingButtonPressed(ActionEvent event) {
		// TODO có lỗi ở đoạn code này song tôi chưa tìm thấy được :>
		try{
			final String ANALYSIS_PAGE_FXML_FILE_PATH = "/screen/view/page/CorrelationAnalysisPagev1.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ANALYSIS_PAGE_FXML_FILE_PATH));
			CorrelationAnalysisControllerv1 controller = new CorrelationAnalysisControllerv1();
			fxmlLoader.setController(controller);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/screen/controller/page/analyzePage/chart.css").toExternalForm());
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	   
	@FXML 
	void btnSwitchPostPressed(ActionEvent event) throws IOException{
		if( togglePost.getText().equals("Blog")) {
			
			listItems =  new FilteredList<Post>(blogPostDB.getPosts());
			tblPost.setItems(listItems);
			togglePost.setText("Tweet");
		}
		else {
			togglePost.setText("Blog");
			tblPost.setItems(listItems);
			listItems = new FilteredList<Post>(tweetDB.getPosts());
		}
	}
	@FXML
	void btnCrawlPressed(ActionEvent event) throws IOException {
//	    dataCrawlPopUp();
	}

	@FXML
	void detailButtonPressed(ActionEvent event) throws IOException {
		PostDetailPopUp();
	}

	@FXML
    void searchByContentRadioButtonClicked(MouseEvent event) {
		clearSearchTextField();
    }

	@FXML
    void searchByHashtagRadioButtonClicked(MouseEvent event) {
		clearSearchTextField();
    }
    
	//**
	//Action
	//**
	
	private void clearSearchTextField() {
		tfQuery.setText("");
	}
	
	private void showFilteredPost(String filter) {
		RadioButton selectedButton = (RadioButton)filterCategory.getSelectedToggle();
		if(selectedButton == radioBtnFilterContent) {
			listItems.setPredicate(item -> item.getContent().contains(filter));
		} else {
			listItems.setPredicate(item -> item.getHashtag().contains(filter));
		}
	}
	
//	private void dataCrawlPopUp() throws IOException {
//		final String FXML_FILE_PATH = "/data/crawl/TwitterCrawlerView.fxml";
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_FILE_PATH));
//		TwitterCrawlerController twitterCrawlerController = new TwitterCrawlerController(tblPost, colContent, colHashtag, colTime, colUserName, crawlButton, tfQuery, filterCategory, radioBtnFilterContent);
//		fxmlLoader.setController(twitterCrawlerController);
//		Parent root = fxmlLoader.load();
//		Stage stage = new Stage();
//		stage.setTitle("Crawler");
//		stage.setScene(new Scene(root));
//		stage.show();
//		crawlButton.setDisable(true);
//	}
	
	private void PostDetailPopUp() throws IOException {
		Post post = tblPost.getSelectionModel().getSelectedItem();
		if (post instanceof Tweet) {
			final String TWITTER_POST_FXML_FILE_PATH = "/screen/view/post/TweetView.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(TWITTER_POST_FXML_FILE_PATH));
			TweetViewController PostViewController = new TweetViewController((Tweet)post, 180);
			fxmlLoader.setController(PostViewController);
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			PostViewController.setData((Tweet)post, true);
			stage.setTitle("Twitter");
			stage.setScene(new Scene(root));
			stage.show();
		}
		if (post instanceof BlogPost) {
			final String TWITTER_POST_FXML_FILE_PATH = "/screen/view/post/TweetView.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(TWITTER_POST_FXML_FILE_PATH));
			BlogViewController PostViewController = new BlogViewController((BlogPost)post, 180);
			fxmlLoader.setController(PostViewController);
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			PostViewController.setData((BlogPost)post, true);
			stage.setTitle("Twitter");
			stage.setScene(new Scene(root));
			stage.show();
		}
	}
	
    @FXML
    private TableColumn<Post, String> colContent;

    @FXML
    private TableColumn<Post, String> colHashtag;

    @FXML
    private TableColumn<Post, String> colTime;

    @FXML
    private TableColumn<Post,String> colUserName;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private RadioButton radioBtnFilterContent;

    @FXML
    private RadioButton radioBtnFilterHastag;

    @FXML
    private TableView<Post> tblPost;

    @FXML
    private TextField tfQuery;
    
    @FXML
    private Button detailButton;
 
    @FXML
    private Button tredingButton;

    @FXML
    private Button crawlButton;
    @FXML
    private Button togglePost;
    
    private static FilteredList<Post> listItems;

}
