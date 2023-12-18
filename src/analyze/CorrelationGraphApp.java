package analyze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CorrelationGraphApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/analyze/CorrelationGraph.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(getClass().getResource("chart.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
