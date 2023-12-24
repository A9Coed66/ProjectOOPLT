package algorithm;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.connector.TweetDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.post.Tweet;

public class GetTweetPostFromJsonFile {
	private static String filePath;

	public GetTweetPostFromJsonFile(String filePath) {
		super();
		this.filePath = filePath;
	}
	public static ObservableList<Tweet> jsonReadAlgorithm() {
		
		ObservableList<Tweet> tweetPosts = FXCollections.observableArrayList();
//		String filePath = "data/json/post/nitter/tweet_top10collection_timecrawl-20231221_184804.json";

        // Sử dụng ObjectMapper để chuyển đổi JSON từ tệp thành List<TwitterData>
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<TweetDB> twitterDataList = Arrays.asList(objectMapper.readValue(new File(filePath), TweetDB[].class));

            // Bây giờ bạn có thể làm việc với List<TwitterData>
            for (TweetDB twitterData : twitterDataList) {
//                System.out.println("UserName: " + twitterData.getUserName());
//                System.out.println("Tweet: " + twitterData.getTweet());
//                System.out.println();
            	String content = twitterData.getTweet();
            	String imageUrl = twitterData.getImage();
            	String author = twitterData.getUserName();
            	String dateCreated = twitterData.getTimeStamp();
            	String url = twitterData.getPostUrl();
            	String reply = twitterData.getReply();
            	String like = twitterData.getLike();
            	String avatarUrl = twitterData.getAvatar();
            	String hashtag = twitterData.getHashtags();
            	String retweet = twitterData.getRetweet();
            	tweetPosts.add(new Tweet(content,imageUrl,author,dateCreated,url,reply,like,avatarUrl,hashtag,retweet));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return tweetPosts;
	}
}
