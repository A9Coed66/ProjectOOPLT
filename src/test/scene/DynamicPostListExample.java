package test.scene;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DynamicPostListExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Tạo danh sách các mục bài viết
        ObservableList<Post> posts = FXCollections.observableArrayList();
        posts.add(new Post("Bài viết 1", new Image("https://pbs.twimg.com/media/GBhfmLyWwAAtGTk?format=jpg")));
        posts.add(new Post("Bài viết 2", new Image("https://pbs.twimg.com/media/GBhfmLyWwAAtGTk?format=jpg")));
        posts.add(new Post("Bài viết 3", new Image("https://pbs.twimg.com/media/GBhfmLyWwAAtGTk?format=jpgi")));

        // Tạo ListView và cài đặt CellFactory để hiển thị các bài viết
        ListView<Post> listView = new ListView<>(posts);
        listView.setCellFactory(param -> new PostListCell());

        // Tạo giao diện VBox và thêm ListView vào đó
        VBox root = new VBox(listView);

        // Tạo Scene và hiển thị nó trên Stage
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Lớp đại diện cho một bài viết
    public static class Post {
        private String title;
        private Image image;

        public Post(String title, Image image) {
            this.title = title;
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public Image getImage() {
            return image;
        }
    }

    // Lớp tùy chỉnh để hiển thị các mục bài viết trong ListView
    public static class PostListCell extends ListCell<Post> {
        @Override
        protected void updateItem(Post post, boolean empty) {
            super.updateItem(post, empty);
            if (post != null) {
                // Tạo ImageView để hiển thị ảnh
                ImageView imageView = new ImageView(post.getImage());
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);

                // Đặt nội dung của ô hiển thị là ImageView và tiêu đề của bài viết
                setGraphic(new VBox(imageView, new Label(post.getTitle())));
            } else {
                setGraphic(null);
            }
        }
    }
}
