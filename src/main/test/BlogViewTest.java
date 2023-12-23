package main.test;



import screen.controller.post.BlogViewController;
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
		final String BLOG_POST_FXML_FILE_PATH = "/screen/view/post/BlogView.fxml";
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
		
		
		blogPost = new BlogPost("Smart contracts are the digital architects that underpin the trust and transparency within the world of Non-Fungibleâ€"
				+"There's a new festive season going đŸ„ I try to join these festivities when I can. There's 2 ways to join. Both need aâ€"
				+ "đŸ¨đŸ–¼ï¸ Dive into the mesmerizing world of 'Mono Art' NFT collection ! Explore the power of minimalism and discoverâ€"
				+ "\n\n\n"
				+"Project: Midnight Apes (Phase 1) Not Much Time Left Till Mint ! ! ! Promotion of my friend's NFT artâ€"
				,"https://steemitimages.com/640x480/https://cdn.steemitimages.com/DQmR2BQZpkJTihofCf3QPTB2TMDA768qSiRW6x8kV3iU8hL/NFT%20Marketplace%20Development%20(1).jpg"
				,"bitcoinflood"
				,"12/17/2023 15:30"
				,"/nft/@snft/snft-2023-12-18"
				,"9 responses"
				,"30"
				,"https://steemitimages.com/u/dzare698/avatar/small"
				,"Unveiling the whimsical world"
				,321.123f
				," #nft");
		
		launch(args);
	}
}
