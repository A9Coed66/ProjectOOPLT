package main.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import screen.controller.page.searchPage.SearchPageController;

public class RunProgram extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final String SEARCH_PAGE_FXML_FILE_PATH = "/screen/view/page/SearchPageView.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SEARCH_PAGE_FXML_FILE_PATH));
        SearchPageController searchPageController = new SearchPageController();
        fxmlLoader.setController(searchPageController);
        Parent root = fxmlLoader.load();
        stage.setTitle("SearchPage");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
