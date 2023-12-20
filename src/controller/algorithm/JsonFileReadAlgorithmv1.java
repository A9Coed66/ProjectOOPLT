package controller.algorithm;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.connector.TwitterDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.post.Tweet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class JsonFileReadAlgorithmv1 {
	private String filepath;

	public JsonFileReadAlgorithmv1(String filepath) {
		super();
		this.filepath = filepath;
	}

	public ObservableList<Tweet> jsonReadAlgorithm() {
		ObservableList<Tweet> tweetPostList = FXCollections.observableArrayList();
		String filePath = "data/json/datatwitter.json";

		// Sử dụng ObjectMapper để chuyển đổi JSON từ tệp thành List<TwitterData>
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ObservableList<TwitterDB> twitterDataList = FXCollections.observableArrayList(Arrays.asList(objectMapper.readValue(new File(filePath), TwitterDB[].class)));

			// Bây giờ bạn có thể làm việc với List<TwitterData>
			for (TwitterDB twitterData : twitterDataList) {
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

				tweetPostList.add(new Tweet(content, imageUrl, author, dateCreated, url, reply, like, avatarUrl, hashtag, retweet));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tweetPostList;
	}
}
