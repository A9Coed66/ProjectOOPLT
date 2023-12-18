package main.test.data;
import data.crawl.TwitterCrawlerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TwitterCrawlerTest extends Application{
	public TwitterCrawlerTest() {
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		final String FXML_FILE_PATH = "/data/crawl/view/TwitterCrawlerView.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_FILE_PATH));
		TwitterCrawlerController twitterCrawlerController = new TwitterCrawlerController();
		fxmlLoader.setController(twitterCrawlerController);
		Parent root = fxmlLoader.load();
	
		primaryStage.setTitle("Crawler");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
