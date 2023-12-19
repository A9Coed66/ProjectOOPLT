package model.post;



public class BlogPost extends Post{
	
//	**
//	Attribute
//	**
	
	private String title;
	private float nftPrice;
	private String tag;
//	**
//	Getter and Setter
//	**	
	
	public String getTitle() {
		return title;
	}
	public float getNftPrice() {
		return nftPrice;
	}
	public String getTag() {
		return tag;
	}

	
//	**
//	Constructor
//	**
	public BlogPost(String content, String imageUrl, String author, String dateCreated, String url, String reply, String like, String avatarUrl) {
		super(content, imageUrl, author, dateCreated, url, reply, like, avatarUrl);
	}
	
	public BlogPost(String content, String imageUrl, String author, String dateCreated, String url, String reply, String like, String avatarUrl, String title, float nftPrice, String tag) {
		super(content, imageUrl, author, dateCreated, url, reply, like, avatarUrl);
		this.title = title;
		this.nftPrice = nftPrice;
		this.tag = tag;
	}
}
