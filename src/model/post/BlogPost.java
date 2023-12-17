package model.post;



public class BlogPost extends Post{
	
//	**
//	Attribute
//	**
	private String postTitle;
	private String postLink;
	private String postDate;
	private String postResponse;
	private float nftPrice;
	private int postVoteNumber;
	
//	**
//	Getter and Setter
//	**	

	public String getpostTitle() {
		return postTitle;
	}

	public String getPostResponse() {
		return postResponse;
	}

	public void setPostResponse(String postResponse) {
		this.postResponse = postResponse;
	}

	public void setpostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostLink() {
		return postLink;
	}

	public void setPostLink(String postLink) {
		this.postLink = postLink;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public float getNftPrice() {
		return nftPrice;
	}

	public void setNftPrice(float nftPrice) {
		this.nftPrice = nftPrice;
	}

	public int getPostVoteNumber() {
		return postVoteNumber;
	}

	public void setPostVoteNumber(int postVoteNumber) {
		this.postVoteNumber = postVoteNumber;
	}

	
//	**
//	Constructor
//	**
	
	public BlogPost(String hashTags, String content, String imageLink, String author) {
		super(hashTags, content, imageLink, author);
		// TODO Auto-generated constructor stub
	}

	public BlogPost(String hashTags, String content, String imageLink, String author, String postTitle, String postLink, String postDate, float nftPrice, int postVoteNumber) {
		super(hashTags, content, imageLink, author);
		this.postTitle = postTitle;
		this.postLink = postLink;
		this.postDate = postDate;
		this.nftPrice = nftPrice;
		this.postVoteNumber = postVoteNumber;
	}

	public BlogPost(String hashTags, String content, String imageLink, String author, String postTitle, String postLink, String postDate, String postResponse, float nftPrice, int postVoteNumber) {
		super(hashTags, content, imageLink, author);
		this.postTitle = postTitle;
		this.postLink = postLink;
		this.postDate = postDate;
		this.postResponse = postResponse;
		this.nftPrice = nftPrice;
		this.postVoteNumber = postVoteNumber;
	}
	
	
	
}
