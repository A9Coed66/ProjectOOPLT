import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("program.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 0, 0);
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


    public static void main(String[] args) {
        launch(args);
    }
}
