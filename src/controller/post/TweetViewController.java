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
import model.post.TwitterPost;

public class TweetViewController {
	
	
	private TwitterPost twitterPost;
	private int maxTextLength = 180; 
	private Boolean isRefresh = true;
	
    public TweetViewController(TwitterPost twitterPost, int maxTextLength) {
		super();
		this.twitterPost = twitterPost;
		this.maxTextLength = maxTextLength;
	}

    public void setData(TwitterPost twitterPost, Boolean isRefresh) {
    	this.twitterPost = twitterPost;
    	this.isRefresh = isRefresh;
    	
    	authorlbl.setText(twitterPost.getAuthor());
    	timeStamplbl.setText(twitterPost.getDateCreated());
    	hashTaglbl.setText(twitterPost.getHashtag());
    	replylbl.setText("Reply: "+twitterPost.getReply());
    	likelbl.setText("Like: "+twitterPost.getLike());
    	retweetlbl.setText("Retweet: "+twitterPost.getRetweet());
    	
        if(twitterPost.getContent().length() >= maxTextLength) {
        	contentlbl.setText(twitterPost.getContent().substring(0, maxTextLength)+"...");
            moreButton.setVisible(isRefresh);
        } else {
        	contentlbl.setText(twitterPost.getContent());
        	moreButton.setVisible(false);
        }


        Image image = new Image(twitterPost.getImageUrl(), true);
        tweeterImageView.setImage(image);
        
        Image avatarImage = new Image(twitterPost.getAvatarUrl(), true);
        avatarImageView.setImage(avatarImage);
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
    private ImageView avatarImageView;
    
    @FXML
    void moreButtonPressed(ActionEvent event) throws IOException {
    	maxTextLength = 1000;
    	if(twitterPost.getContent().length() >= maxTextLength) {
    		contentlbl.setText(twitterPost.getContent().substring(0, maxTextLength)+"...");
        } else {
        	contentlbl.setText(twitterPost.getContent());
        }
    	moreButton.setVisible(false);
    	
    	
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	final String TWITTER_POST_FXML_FILE_PATH = "/view/post/TweetView.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(TWITTER_POST_FXML_FILE_PATH));
		TweetViewController tweetViewController = new TweetViewController(twitterPost, maxTextLength);
		fxmlLoader.setController(tweetViewController);
		Parent root = fxmlLoader.load();
		tweetViewController.setData(twitterPost, false);
		stage.setTitle("Twitter");
		stage.setScene(new Scene(root));
		stage.show();
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
