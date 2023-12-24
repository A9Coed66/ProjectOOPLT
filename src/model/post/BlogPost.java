package model.post;



public class BlogPost extends Post{
	
//	**
//	Attribute
//	**
	
	private String title;
	private float nftPrice;
//	**
//	Getter and Setter
//	**	
	
	public String getTitle() {
		return title;
	}
	public float getNftPrice() {
		return nftPrice;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setNftPrice(float nftPrice) {
		this.nftPrice = nftPrice;
	}

//	**
//	Constructor
//	**
	public BlogPost(String content, String imageUrl, String author, String dateCreated, String url, String reply, String like, String avatarUrl, String title, float nftPrice, String hashtag) {
		super(content, imageUrl, author, dateCreated, url, reply, like, avatarUrl,hashtag);
		this.title = title;
		this.nftPrice = nftPrice;
	}
	

}
