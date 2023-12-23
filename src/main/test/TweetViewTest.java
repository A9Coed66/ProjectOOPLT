package main.test;

import java.io.IOException;

import screen.controller.post.TweetViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.post.Tweet;

public class TweetViewTest extends Application{

	
	private static Tweet twitterPost;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		final String TWITTER_POST_FXML_FILE_PATH = "/view/post/TweetView.fxml";
		extracted(primaryStage, TWITTER_POST_FXML_FILE_PATH);
	}

	public void extracted(Stage primaryStage, final String TWITTER_POST_FXML_FILE_PATH) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(TWITTER_POST_FXML_FILE_PATH));
		TweetViewController tweetViewController = new TweetViewController(twitterPost, 180);
		fxmlLoader.setController(tweetViewController);
		Parent root = fxmlLoader.load();
		tweetViewController.setData(twitterPost, true);
		primaryStage.setTitle("Twitter");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		
		
		twitterPost = new Tweet("Shill your #NFT \\nWe are buyinggqShill your #NFT \\\\nWe are buyinggqShill your #NFT \\\\nWe are buyinggqShill your #NFT \\\\nWe are buyinggqShill your #NFT \\\\nWe are buyinggqShill your #NFT \\\\nWe are buyinggq"
				+ "Shill your #NFT \\\\nWe are buyinggq"
				+ "Shill your #NFT \\\\nWe are buyinggq"
				+ "Shill your #NFT \\\\nWe are buyinggq"
				+ "Shill your #NFT \\\\nWe are buyinggq"
				+ "Shill your #NFT \\\\nWe are buyinggq"
				+ "Shill your #NFT \\\\nWe are buyinggq"
				+ "Shill your #NFT \\\\nWe are buyinggq"
				+ "Shill your #NFT \\\\nWe are buyinggq"
				+ "Shill your #NFT \\\\nWe are buyinggq"
				+ "Shill your #NFT \\\\nWe are buyinggq"
				+ "Shill your #NFT \\\\nWe are buyinggq"
				+ "Shill your #NFT \\\\nWe are buyinggq"
				+"Kidding:))))"
				,"https://pbs.twimg.com/media/GBiDzdzaYAA0L4R?format=jpg&name=small"
				,"@NFTDegenNSM"
				,"2023-12-17T12:11:38.000Z"
				,"https://twitter.com/NFTDegenNSM/status/1736358488299696308"
				,"40"
				,"59"
				,"https://pbs.twimg.com/media/GBiDzdzaYAA0L4R?format=jpg&name=small"
				,"#NFT"
				,"2");
		
		launch(args);
	}
	
	
	
}
