package main.test.scene;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class DatePickerExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        DatePicker datePicker = new DatePicker();

        // Đặt giá trị mặc định (ví dụ: ngày hôm nay)
        // datePicker.setValue(LocalDate.now());

        primaryStage.setScene(new Scene(datePicker, 300, 200));
        primaryStage.setTitle("DatePicker Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
