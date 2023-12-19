package controller.algorithm;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.connector.TwitterDB;
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
	
	public List<String> jsonReadAlgorithm() {
		
		List<String> tweetPostList = new ArrayList<>();
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
            	
//                Text text1 = new Text("This is ");
//                text1.setFont(Font.font("Arial", 12));
//
//                Text text2 = new Text("bold");
//                text2.setFont(Font.font("Arial", FontWeight.BOLD, 12));
//                text2.setFill(Color.RED);
//
//                Text text3 = new Text(" and this is ");
//                text3.setFont(Font.font("Arial", 12));
//
//                Text text4 = new Text("italic");
//                text4.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
//                text4.setFill(Font.);
//
//                Text text5 = new Text(".");
//                text5.setFont(Font.font("Arial", 12));
//
//                TextFlow textFlow = new TextFlow(text1, text2, text3, text4, text5);
            	tweetPostList.add((new Tweet(content,imageUrl,author,dateCreated,url,reply,like,avatarUrl,hashtag,retweet)).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return tweetPostList;
	}
	
//	public static void main(String[] args) {
//		String filePath = "data/json/datatwitter.json";
//
//        // Sử dụng ObjectMapper để chuyển đổi JSON từ tệp thành List<TwitterData>
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            List<TwitterDB> twitterDataList = Arrays.asList(objectMapper.readValue(new File(filePath), TwitterDB[].class));
//
//            // Bây giờ bạn có thể làm việc với List<TwitterData>
//            for (TwitterDB twitterData : twitterDataList) {
//                System.out.println("UserName: " + twitterData.getUserName());
//                System.out.println("Tweet: " + twitterData.getTweet());
//                System.out.println();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//	}
}
