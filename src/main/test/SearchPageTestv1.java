package main.test;

import controller.algorithm.JsonFileReadAlgorithm;
import controller.algorithm.JsonFileReadAlgorithmv1;
import controller.page.SearchPageController;
import controller.page.SearchPageControllerv1;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.post.Tweet;

import java.util.List;

public class SearchPageTestv1 extends Application{
	
//	CSVFileReadAlgorithm csvFileReadAlgorithm = new CSVFileReadAlgorithm("data/csv/blog_new (1).csv");
//	List<String> listItems = csvFileReadAlgorithm.csvFileRead();
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// Thiết lập scene builder
		JsonFileReadAlgorithmv1 jsonFileReadAlgorithm = new JsonFileReadAlgorithmv1("/data/json/datatwitter.json");
		final String SEARCH_PAGE_FXML_FILE_PATH = "/view/page/SearchPageViewv1.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SEARCH_PAGE_FXML_FILE_PATH));
		// Thiết lập controller và khởi tạo dữ liệu
		ObservableList<Tweet> listItems = jsonFileReadAlgorithm.jsonReadAlgorithm();
		SearchPageControllerv1 searchPageController = new SearchPageControllerv1(listItems);
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
