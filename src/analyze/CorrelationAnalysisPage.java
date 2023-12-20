package analyze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CorrelationAnalysisPage extends Application {

 @Override
 public void start(Stage stage) throws Exception {
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/page/CorrelationAnalysisPage.fxml"));
     CorrelationAnalysisController correlationAnalysisController = new CorrelationAnalysisController();
     fxmlLoader.setController(correlationAnalysisController);
     Parent root = fxmlLoader.load();

     Scene scene = new Scene(root);
     scene.getStylesheets().add(getClass().getResource("chart.css").toExternalForm());
     stage.setScene(scene);
     stage.setTitle("Correlation Analysis");
     stage.show();
 }

 public static void main(String[] args) {
     launch(args);
 }
}

