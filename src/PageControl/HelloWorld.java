package PageControl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloWorld extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloWorld.class.getResource("program.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("hello");
        primaryStage.setScene(scene);
        primaryStage.show();

//    primaryStage.setTitle("Hello World!");
//    Button btn = new Button();
//    btn.setText("Click me!");
//    btn.setOnAction(event -> System.out.println("Hello World!"));
//    StackPane root = new StackPane();
//    root.getChildren().add(btn);
//    primaryStage.setScene(new Scene(root, 300, 250));
//    primaryStage.show();
    }
}
