package model.post;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TweeterPost extends Post{
//	**
//	Attribute
//	**
    @JsonProperty("like")
    private String like;

    @JsonProperty("PostUrl")
    private String postUrl;
    
    @JsonProperty("Reply")
    private String reply;
    
    @JsonProperty("Retweet")
    private String retweet;

    @JsonProperty("TimeStamp")
    private String timeStamp;
    
    @JsonProperty("Tags")
    private String tags;
    
    @JsonProperty("Name")
    private String name;
//	**
//	Getter and Setter
//	**

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
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

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
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

//	**
//	Constructor
//	**
    public TweeterPost(String hashTags, String content, String imageLink, String author) {
		super(hashTags, content, imageLink, author);
		// TODO Auto-generated constructor stub
	}

public TweeterPost(String hashTags, String content, String imageLink, String author, String like, String postUrl, String reply, String retweet, String timeStamp, String tags, String name) {
	super(hashTags, content, imageLink, author);
	this.like = like;
	this.postUrl = postUrl;
	this.reply = reply;
	this.retweet = retweet;
	this.timeStamp = timeStamp;
	this.tags = tags;
	this.name = name;
}
    
    
}
