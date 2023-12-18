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

public class BlogViewController {

	private BlogPost blogPost;
	private int maxTextLength = 180; 
	private Boolean isRefresh = true;
	
    public BlogViewController(BlogPost blogPost, int maxTextLength) {
		super();
		this.blogPost = blogPost;
		this.maxTextLength = maxTextLength;
	}

    public void setData(BlogPost blogPost, Boolean isRefresh) {
    	this.blogPost = blogPost;
    	this.isRefresh = isRefresh;
    	postTitlelbl.setText(blogPost.getpostTitle());
    	postDatelbl.setText(blogPost.getPostDate());
    	authorlbl.setText(blogPost.getAuthor());
    	taglbl.setText(blogPost.getTag());
    	//contentlbl.setText(blogPost.getContent());
    	pricelbl.setText("Price: "+blogPost.getNftPrice()+"");
    	votelbl.setText("Votes: "+blogPost.getPostVoteNumber()+"");
    	responselbl.setText("Responses: "+blogPost.getPostResponse());
    	textArea.setText(blogPost.getContent());

        if(blogPost.getContent().length() >= maxTextLength) {
        	textArea.setText(blogPost.getContent().substring(0, maxTextLength)+"...");
        } else {
        	textArea.setText(blogPost.getContent());
        }
        moreButton.setVisible(isRefresh);

        Image image = new Image(blogPost.getImageLink(), true);
        blogimageview.setImage(image);
    }
    
    @FXML
    void moreButtonPressed(ActionEvent event) throws IOException {
    	maxTextLength = 1000;
    	if(blogPost.getContent().length() >= maxTextLength) {
        	textArea.setText(blogPost.getContent().substring(0, maxTextLength)+"...");
        } else {
        	textArea.setText(blogPost.getContent());
        }
    	moreButton.setVisible(false);
    	
    	
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	final String BLOG_POST_FXML_FILE_PATH = "/view/post/BlogView.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(BLOG_POST_FXML_FILE_PATH));
		BlogViewController blogPostViewController = new BlogViewController(blogPost, maxTextLength);
		fxmlLoader.setController(blogPostViewController);
		Parent root = fxmlLoader.load();
		blogPostViewController.setData(blogPost, false);
		stage.setTitle("Blog");
		stage.setScene(new Scene(root));
		stage.show();
    }
    
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label authorlbl;

    @FXML
    private ImageView blogimageview;

    @FXML
    private Label contentlbl;

    @FXML
    private Label taglbl;

    @FXML
    private Label postDatelbl;

    @FXML
    private Label postTitlelbl;

    @FXML
    private Label pricelbl;

    @FXML
    private Label responselbl;

    @FXML
    private Label votelbl;

    @FXML
    private Text textArea;
    
    @FXML
    private Button moreButton;
    
    @FXML
    void initialize() {
        assert authorlbl != null : "fx:id=\"authorlbl\" was not injected: check your FXML file 'blogPostView.fxml'.";
        assert blogimageview != null : "fx:id=\"blogimageview\" was not injected: check your FXML file 'blogPostView.fxml'.";
        assert contentlbl != null : "fx:id=\"contentlbl\" was not injected: check your FXML file 'blogPostView.fxml'.";
        assert taglbl != null : "fx:id=\"hashTagslbl\" was not injected: check your FXML file 'blogPostView.fxml'.";
        assert postDatelbl != null : "fx:id=\"postDatelbl\" was not injected: check your FXML file 'blogPostView.fxml'.";
        assert postTitlelbl != null : "fx:id=\"postTitlelbl\" was not injected: check your FXML file 'blogPostView.fxml'.";
        assert pricelbl != null : "fx:id=\"pricelbl\" was not injected: check your FXML file 'blogPostView.fxml'.";
        assert responselbl != null : "fx:id=\"responselbl\" was not injected: check your FXML file 'blogPostView.fxml'.";
        assert votelbl != null : "fx:id=\"votelbl\" was not injected: check your FXML file 'blogPostView.fxml'.";
    }

}
