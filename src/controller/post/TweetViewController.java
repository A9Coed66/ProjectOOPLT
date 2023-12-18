package controller.post;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.post.BlogPost;
import model.post.TweeterPost;

public class TweetViewController {
	
	
	private TweeterPost tweeterPost;
	private int maxTextLength = 180; 
	private Boolean isRefresh = true;
	
    public TweetViewController(TweeterPost tweeterPost, int maxTextLength) {
		super();
		this.tweeterPost = tweeterPost;
		this.maxTextLength = maxTextLength;
	}

    public void setData(TweeterPost tweeterPost, Boolean isRefresh) {
    	this.tweeterPost = tweeterPost;
    	this.isRefresh = isRefresh;
    	
    	authorlbl.setText(tweeterPost.getAuthor());
    	timeStamplbl.setText(tweeterPost.getTimeStamp());
    	hashTaglbl.setText(tweeterPost.getHashtag());
    	taglbl.setText(tweeterPost.getTag());
    	
        if(tweeterPost.getContent().length() >= maxTextLength) {
        	contentlbl.setText(tweeterPost.getContent().substring(0, maxTextLength)+"...");
        } else {
        	contentlbl.setText(tweeterPost.getContent());
        }
        moreButton.setVisible(isRefresh);

        Image image = new Image(tweeterPost.getImageLink(), true);
//        blogimageview.setImage(image);
    }
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label authorlbl;

    @FXML
    private Text contentlbl;

    @FXML
    private Label hashTaglbl;

    @FXML
    private Label likelbl;

    @FXML
    private Button moreButton;

    @FXML
    private Label replylbl;

    @FXML
    private Label retweetlbl;

    @FXML
    private Label taglbl;

    @FXML
    private Label timeStamplbl;

    @FXML
    private ImageView tweeterImageView;
    
    @FXML
    void moreButtonPressed(ActionEvent event) {
//    	maxTextLength = 1000;
//    	if(blogPost.getContent().length() >= maxTextLength) {
//        	textArea.setText(blogPost.getContent().substring(0, maxTextLength)+"...");
//        } else {
//        	textArea.setText(blogPost.getContent());
//        }
//    	moreButton.setVisible(false);
    	
    	
//    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//    	final String BLOG_POST_FXML_FILE_PATH = "/view/post/BlogView.fxml";
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(BLOG_POST_FXML_FILE_PATH));
//		BlogViewController blogPostViewController = new BlogViewController(blogPost, maxTextLength);
//		fxmlLoader.setController(blogPostViewController);
//		Parent root = fxmlLoader.load();
//		blogPostViewController.setData(blogPost, false);
//		stage.setTitle("Blog");
//		stage.setScene(new Scene(root));
//		stage.show();
    }

    @FXML
    void initialize() {
        assert authorlbl != null : "fx:id=\"authorlbl\" was not injected: check your FXML file 'TweetView.fxml'.";
        assert contentlbl != null : "fx:id=\"contentlbl\" was not injected: check your FXML file 'TweetView.fxml'.";
        assert hashTaglbl != null : "fx:id=\"hashTaglbl\" was not injected: check your FXML file 'TweetView.fxml'.";
        assert likelbl != null : "fx:id=\"likelbl\" was not injected: check your FXML file 'TweetView.fxml'.";
        assert moreButton != null : "fx:id=\"moreButton\" was not injected: check your FXML file 'TweetView.fxml'.";
        assert replylbl != null : "fx:id=\"replylbl\" was not injected: check your FXML file 'TweetView.fxml'.";
        assert retweetlbl != null : "fx:id=\"retweetlbl\" was not injected: check your FXML file 'TweetView.fxml'.";
        assert taglbl != null : "fx:id=\"taglbl\" was not injected: check your FXML file 'TweetView.fxml'.";
        assert timeStamplbl != null : "fx:id=\"timeStamplbl\" was not injected: check your FXML file 'TweetView.fxml'.";

    }

}
