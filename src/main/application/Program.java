package main.application;

import controller.ProgramController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Program extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final String PROGRAM_PATH = "/view/page/HomePage.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PROGRAM_PATH));
        ProgramController viewStoreController = new ProgramController();
        fxmlLoader.setController(viewStoreController);
        Parent root = fxmlLoader.load();

        stage.setTitle("Home Page");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
