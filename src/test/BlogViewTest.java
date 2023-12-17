package test;



import controller.post.BlogViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.post.BlogPost;
public class BlogViewTest extends Application{
	private static BlogPost blogPost;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		final String BLOG_POST_FXML_FILE_PATH = "/view/post/BlogView.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(BLOG_POST_FXML_FILE_PATH));
//		ViewStoreController viewStoreController = new ViewStoreController(store);
		BlogViewController blogPostViewController = new BlogViewController(blogPost, 180);
		fxmlLoader.setController(blogPostViewController);
		Parent root = fxmlLoader.load();
		blogPostViewController.setData(blogPost, true);
		primaryStage.setTitle("Blog");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		
		
		blogPost = new BlogPost(" #nft â€¢", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
		                + "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
		                + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
		                + "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in "
		                + "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
		                + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia "
		                + "deserunt mollit anim id est laborum.", 
				"https://steemitimages.com/640x480/https://pbs.twimg.com/media/GBgq7PFaMAAmg9F.jpg", 
				"dzare698", 
				"The Best NFT Game", 
				"/hive-154886/@dzare698/1736184863219462191", 
				"12/17/2023  12:42:00 AM", 
				"No responses yet", 
				200.2f, 
				21);
		
		launch(args);
	}
}
