package model.post;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
	
	@Override
	public String toString() {
		
		String author = this.getAuthor();
		String content = this.getContent();
				content = content.replaceAll("\n", "\\n");
		Instant instant = Instant.parse(this.getDateCreated());
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = dateTime.format(formatter);
		if(author.length()>30) author = author.substring(0,10)+"...";
		if(content.length()>100) content = content.substring(0,40)+"...";
		return String.format("%-20s%-50s%-25s", author, content, formattedTime);
	}
	
	
}
