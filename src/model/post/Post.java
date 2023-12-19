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
//	**
//	Constructor
//	**
	public Post() {
		super();
	}
	
	public Post(String content, String imageUrl, String author, String dateCreated, String url, String reply, String like, String avatarUrl) {
		super();
		this.content = content;
		this.imageUrl = imageUrl;
		this.author = author;
		this.dateCreated = dateCreated;
		this.url = url;
		this.reply = reply;
		this.like = like;
		this.avatarUrl = avatarUrl;
	}
}
