package data.connector;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.post.Post;
import model.post.Tweet;

public class TweetDB implements IPostDB{
	private ObservableList<Post> posts = FXCollections.observableArrayList();
	public ObservableList<Post> init(String filePath){
		ObservableList<Post> tweetPosts = FXCollections.observableArrayList();
//		String filePath = "data/json/post/nitter/tweet_top10collection_timecrawl-20231221_184804.json";

        // Sử dụng ObjectMapper để chuyển đổi JSON từ tệp thành List<TwitterData>
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<TweetDataFrame> twitterDataList = Arrays.asList(objectMapper.readValue(new File(filePath), TweetDataFrame[].class));

            // Bây giờ bạn có thể làm việc với List<TwitterData>
            for (TweetDataFrame twitterData : twitterDataList) {
//                System.out.println("UserName: " + twitterData.getUserName());
//                System.out.println("Tweet: " + twitterData.getTweet());
//                System.out.println();
            	String content = twitterData.getTweet();
            	String imageUrl = twitterData.getImage();
            	String author = twitterData.getUserName();
            	String dateCreated = twitterData.getTimeStamp();
            	String url = twitterData.getPostUrl();
            	String reply = twitterData.getReply();
            	String like = twitterData.getLike();
            	String avatarUrl = twitterData.getAvatar();
            	String hashtag = twitterData.getHashtags();
            	String retweet = twitterData.getRetweet();
            	tweetPosts.add(new Tweet(content,imageUrl,author,dateCreated,url,reply,like,avatarUrl,hashtag,retweet));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.posts= tweetPosts;
		return tweetPosts;
	};
	
	public ObservableList<Post> getPosts(){
		return this.posts;
	}
	
    
	
}
class TweetDataFrame{
	@JsonProperty("Hastags")
    private String hashtags;
    
    @JsonProperty("Avatar")
    private String avatar;

    @JsonProperty("UserName")
    private String userName;

    @JsonProperty("Like")
    private String like;

    @JsonProperty("Tweet")
    private String tweet;

    @JsonProperty("PostUrl")
    private String postUrl;

    @JsonProperty("Reply")
    private String reply;

    @JsonProperty("Retweet")
    private String retweet;

    @JsonProperty("Image")
    private String image;

    @JsonProperty("TimeStamp")
    private String timeStamp;
    
    @JsonProperty("Collection")
    private String collection;

    @JsonProperty("Tags")
    private String tags;

    @JsonProperty("Name")
    private String name;

	public String getHashtags() {
		return hashtags;
	}

	public void setHashtags(String hashtags) {
		this.hashtags = hashtags;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getPostUrl() {
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getRetweet() {
		return retweet;
	}

	public void setRetweet(String retweet) {
		this.retweet = retweet;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
