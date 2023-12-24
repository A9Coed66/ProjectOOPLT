package model.post;


public class Post {
//	**
//	Attribute
//	**
	private String content;
	private String imageUrl;
	private String author;
	private String dateCreated;
	private String url;
	private String reply;
	private String like;
	private String avatarUrl;
	private String hashtag;
	
//	**
//	Getter and Setter
//	**
	public String getContent() {
		return content;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public String getAuthor() {
		return author;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public String getUrl() {
		return url;
	}
	public String getReply() {
		return reply;
	}
	public String getLike() {
		return like;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
//	**
//	Constructor
//	**
	public Post() {
		super();
	}
	
	public Post(String content, String imageUrl, String author, String dateCreated, String url, String reply, String like, String avatarUrl, String hashtag) {
		super();
		this.content = content;
		this.imageUrl = imageUrl;
		this.author = author;
		this.dateCreated = dateCreated;
		this.url = url;
		this.reply = reply;
		this.like = like;
		this.avatarUrl = avatarUrl;
		this.hashtag = hashtag;
	}
	
	@Override
	public String toString() {
		return author + " " + dateCreated + " " + content;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	
	
}
