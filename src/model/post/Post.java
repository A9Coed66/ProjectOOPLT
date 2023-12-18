package model.post;


public class Post {
//	**
//	Attribute
//	**
	private String hashTags;
	private String content;
	private String imageLink;
	private String author;
	
//	**
//	Getter and Setter
//	**
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getHashTags() {
		return hashTags;
	}
	public void setHashTags(String hashTags) {
		this.hashTags = hashTags;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
	
//	**
//	Constructor
//	**
	public Post(String hashTags, String content, String imageLink, String author) {
		super();
		this.hashTags = hashTags;
		this.content = content;
		this.imageLink = imageLink;
		this.author = author;
	}
}
