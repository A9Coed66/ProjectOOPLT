package model.post;

public class TwitterPost extends Post{

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
	
//	**
//	Constructor
//	**
	public TwitterPost(String content, String imageUrl, String author, String dateCreated, String url, String reply, String like, String avatarUrl) {
		super(content, imageUrl, author, dateCreated, url, reply, like, avatarUrl);
	}
	public TwitterPost(String content, String imageUrl, String author, String dateCreated, String url, String reply, String like, String avatarUrl, String hashtag, String retweet) {
		super(content, imageUrl, author, dateCreated, url, reply, like, avatarUrl);
		this.hashtag = hashtag;
		this.retweet = retweet;
	}
	
	
}
