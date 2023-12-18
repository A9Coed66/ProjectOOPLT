package main.test.scene;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ChoiceBoxExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Giá trị 1", "Giá trị 2", "Giá trị 3");

        // Lắng nghe sự kiện khi giá trị được chọn
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Giá trị được chọn: " + newValue);
        });

        StackPane root = new StackPane();
        root.getChildren().add(choiceBox);

        Scene scene = new Scene(root, 300, 150);
        primaryStage.setTitle("ChoiceBox Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
