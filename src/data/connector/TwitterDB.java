package data.connector;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwitterDB {
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
