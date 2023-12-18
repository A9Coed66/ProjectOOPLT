package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

import java.io.IOException;

public class ProgramController {
    @FXML
    private GridPane recentTweet;

    public void initialize() throws IOException {
        final String FILE_PATH = "/view/post/Tweet.fxml";
        int column=0, row=1;
        for(int i=0;i<1;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(FILE_PATH));
            PostController postController = new PostController();
            fxmlLoader.setController(postController);
            AnchorPane anchorPane = new AnchorPane();
            anchorPane=fxmlLoader.load();
            postController.setData("Le Tuan", "hello", "12", "12");


            // VERY IMPORTANT HERE
            recentTweet.add(anchorPane, column,row++);

            // Insets: top right bottom left
            GridPane.setMargin(anchorPane,new Insets(0,10,10,5));
    //        recentTweet.add(fxmlLoader.load(), column,row++);
        }
    }
}
