package data.connector;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.post.BlogPost;
import model.post.Post;

public class BlogPostDB implements IPostDB{
	private ObservableList<Post> posts = FXCollections.observableArrayList();
	public ObservableList<Post> init(String filePath){
		ObservableList<Post> blogPosts = FXCollections.observableArrayList();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<BlogDataFrame> blogDataList = Arrays.asList(objectMapper.readValue(new File(filePath), BlogDataFrame[].class));

            for (BlogDataFrame blogData : blogDataList) {
            	String content = blogData.getContent();
            	String imageUrl = blogData.getImage();
            	String author = blogData.getName();
            	String dateCreated = blogData.getDate();
            	String url = blogData.getPostUrl();
            	String reply = blogData.getComment();
            	String avatarUrl = blogData.getAvatar();
            	String hashtag = blogData.getHashtags();
            	String like = blogData.getVote();
            	String title = blogData.getTitle();
            	
            	float price = Float.parseFloat(blogData.getPrice().replace("$",""));
            	blogPosts.add(new BlogPost(content,imageUrl,author,dateCreated,url,reply,like,avatarUrl,title,price, hashtag));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.posts= blogPosts;
		return blogPosts;
	};
	
	public ObservableList<Post> getPosts(){
		return this.posts;
	}
	
    
	
}

class BlogDataFrame {
    @JsonProperty("Comment")
    private String comment;

    @JsonProperty("Hastags")
    private String hashtags;

    @JsonProperty("Vote")
    private String vote;

    @JsonProperty("Price")
    private String price;

    @JsonProperty("Content")
    private String content;

    @JsonProperty("PostUrl")
    private String postUrl;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Image")
    private String image;

    @JsonProperty("Avatar")
    private String avatar;

    public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getHashtags() {
		return hashtags;
	}

	public void setHashtags(String hashtags) {
		this.hashtags = hashtags;
	}

	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostUrl() {
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("Date")
    private String date;

    @JsonProperty("Name")
    private String name;

   
}
