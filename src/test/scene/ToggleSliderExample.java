package test.scene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ToggleSliderExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Tạo một Slider
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(1);
        slider.setValue(0);

        // Tạo một ToggleButton
        ToggleButton toggleButton = new ToggleButton("Bật/Tắt");

        // Xử lý sự kiện khi ToggleButton thay đổi trạng thái
        toggleButton.setOnAction(event -> {
            if (toggleButton.isSelected()) {
                slider.setValue(1);
            } else {
                slider.setValue(0);
            }
        });

        // Xử lý sự kiện khi Slider thay đổi giá trị
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            toggleButton.setSelected(newValue.doubleValue() > 0.5);
        });

        // Tạo layout và thêm các thành phần
        HBox root = new HBox(10);
        root.getChildren().addAll(slider, toggleButton);

        // Tạo Scene và hiển thị Stage
        Scene scene = new Scene(root, 300, 150);
        primaryStage.setTitle("Toggle Slider Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
