package controller.algorithm;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.connector.TwitterDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.post.BlogPost;
import model.post.Tweet;

public class JsonFileReadAlgorithm {
	private String filepath;

	public JsonFileReadAlgorithm(String filepath) {
		super();
		this.filepath = filepath;
	}
	
	public  static ObservableList<Tweet> jsonReadAlgorithm() {
		
		ObservableList<Tweet> tweetPosts = FXCollections.observableArrayList();
		String filePath = "data/json/datatwitter.json";

        // Sử dụng ObjectMapper để chuyển đổi JSON từ tệp thành List<TwitterData>
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<TwitterDB> twitterDataList = Arrays.asList(objectMapper.readValue(new File(filePath), TwitterDB[].class));

            // Bây giờ bạn có thể làm việc với List<TwitterData>
            for (TwitterDB twitterData : twitterDataList) {
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
