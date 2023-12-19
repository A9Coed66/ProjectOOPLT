package model.post;

public class Tweet extends Post{

//	**
//	Attribute
//	**
	private String hashtag;
	private String retweet;
//	**
//	Getter and Setter
//	**
	public String getHashtag() {
		return hashtag;
	}
	public String getRetweet() {
		return retweet;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public void setRetweet(String retweet) {
		this.retweet = retweet;
	}
	//	**
//	Constructor
//	**
	public Tweet(String content, String imageUrl, String author, String dateCreated, String url, String reply, String like, String avatarUrl) {
		super(content, imageUrl, author, dateCreated, url, reply, like, avatarUrl);
	}
	public Tweet(String content, String imageUrl, String author, String dateCreated, String url, String reply, String like, String avatarUrl, String hashtag, String retweet) {
		super(content, imageUrl, author, dateCreated, url, reply, like, avatarUrl);
		this.hashtag = hashtag;
		this.retweet = retweet;
	}
	
	
}
