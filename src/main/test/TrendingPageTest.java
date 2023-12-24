package main.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import screen.controller.page.trendingPage.TrendingPageController;

public class TrendingPageTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screen/view/page/TrendingPage.fxml"));
        TrendingPageController trendingPageController = new TrendingPageController();
        fxmlLoader.setController(trendingPageController);
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Trending");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}