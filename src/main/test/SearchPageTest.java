package main.test;

import algorithm.GetTweetPostFromJsonFile;
import screen.controller.page.searchPage.SearchPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SearchPageTest extends Application{
	
//	CSVFileReadAlgorithm csvFileReadAlgorithm = new CSVFileReadAlgorithm("data/csv/blog_new (1).csv");
//	List<String> listItems = csvFileReadAlgorithm.csvFileRead();
	
	GetTweetPostFromJsonFile jsonFileReadAlgorithm = new GetTweetPostFromJsonFile("data/json/post/nitter/tweet_top10collection_timecrawl-20231221_184804.json");
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		final String SEARCH_PAGE_FXML_FILE_PATH = "/view/page/SearchPageView.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SEARCH_PAGE_FXML_FILE_PATH));
		SearchPageController searchPageController = new SearchPageController();
		fxmlLoader.setController(searchPageController);
		Parent root = fxmlLoader.load();
		primaryStage.setTitle("SearchPage");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
