package main.test.scene;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CustomToggleSwitch extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Tạo một ToggleButton và đặt kiểu cách tùy chỉnh để trông như công tắc trượt
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
        toggleButton.setMaxSize(80, 40);
        toggleButton.setMinSize(80, 40);

        // Xử lý sự kiện khi ToggleButton thay đổi trạng thái
        toggleButton.setOnAction(event -> {
            if (toggleButton.isSelected()) {
                toggleButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            } else {
                toggleButton.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
            }
        });

        // Tạo layout và thêm ToggleButton
        StackPane root = new StackPane();
        root.getChildren().add(toggleButton);

        // Tạo Scene và hiển thị Stage
        Scene scene = new Scene(root, 300, 150);
        primaryStage.setTitle("Custom Toggle Switch Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
