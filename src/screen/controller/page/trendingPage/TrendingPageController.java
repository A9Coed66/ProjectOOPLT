package screen.controller.page.trendingPage;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import algorithm.HashtagRanking;
import algorithm.HashtagRanking.HashtagStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import screen.controller.page.analyzePage.CorrelationAnalysisControllerv1;
import screen.controller.page.searchPage.SearchPageController;

public class TrendingPageController {
	@FXML
    private ToggleButton DayButton;

    @FXML
    private ToggleButton MonthButton;

    @FXML
    private ToggleGroup TimeToggle;

    @FXML
    private ToggleButton WeekButton;

    @FXML
    private Button analysisButton;

    @FXML
    private Button searchButton;

    @FXML
    private VBox trendingBox;
    
    @FXML
    private ListView<String> hashtagListView;

    private ObservableList<String> hashtagList = FXCollections.observableArrayList();


    @FXML
    private void dayButtonPressed() {
        filterAndDisplayTopHashtags("day");
    }

    // This method is called when the Week button is pressed
    @FXML
    private void weekButtonPressed() {
        filterAndDisplayTopHashtags("week");
    }

    // This method is called when the Month button is pressed
    @FXML
    private void monthButtonPressed() {
        filterAndDisplayTopHashtags("month");
    }
    
    @FXML
    void analysisButtonPressed(ActionEvent event) {
    	try{
			final String ANALYSIS_PAGE_FXML_FILE_PATH = "/screen/view/page/CorrelationAnalysisPagev1.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ANALYSIS_PAGE_FXML_FILE_PATH));
			CorrelationAnalysisControllerv1 controller = new CorrelationAnalysisControllerv1();
			fxmlLoader.setController(controller);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/screen/controller/page/analyzePage/chart.css").toExternalForm());
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void searchButtonPressed(ActionEvent event) {
    	try {
			final String SEARCH_PAGE_FXML_FILE_PATH = "/screen/view/page/SearchPageView.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SEARCH_PAGE_FXML_FILE_PATH));
			SearchPageController searchPageController = new SearchPageController();
			fxmlLoader.setController(searchPageController);
			Parent root = fxmlLoader.load();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    public void initialize() {
    	hashtagListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                // Set the font size for each cell
                if (!empty) {
                    setText(item);
                    setFont(Font.font(22)); // Adjust the font size as needed
                } else {
                    setText(null);
                }
            }
        });
    	hashtagListView.setFixedCellSize(61.5);
        // Display the top hashtags for the default time interval (Month)
        filterAndDisplayTopHashtags("month");
    }

    private void filterAndDisplayTopHashtags(String timeInterval) {
        List<JsonNode> filteredTweets = filterTweetsByTimeInterval(timeInterval);
        List<Map.Entry<String, HashtagStats>> topHashtags = calculateTopHashtags(filteredTweets);
        updateUI(topHashtags);
    }

    public List<JsonNode> filterTweetsByTimeInterval(String timeInterval) {
    	ObjectMapper objectMapper = new ObjectMapper();
    	List<JsonNode> filteredTweets = new ArrayList<>();

        // Define the date format used in the "TimeStamp" field
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Calculate the current time to compare with tweet timestamps
        long currentTime = System.currentTimeMillis();

        // Define time intervals in milliseconds
        long dayInterval = 24 * 60 * 60 * 1000;  // 1 day
        long weekInterval = 7 * dayInterval;      // 1 week
        long monthInterval = 30 * dayInterval;    // 1 month (approximate)

        try {
            // Read the tweets from the specified JSON file
            JsonNode tweetsNode = objectMapper.readTree(new File("data/json/post/nitter/tweet_1d_top10collection_timecrawl-20231224_231828.json"));

            // Iterate through each tweet and filter based on the time interval
            for (JsonNode tweetNode : tweetsNode) {
                // Parse the timestamp from the tweet
                String timestampString = tweetNode.get("TimeStamp").asText();
                timestampString = timestampString.replace(" · ", " "); // Remove non-standard character
                SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy h:mm a z", Locale.ENGLISH);
                
                inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date tweetTimestamp = inputFormat.parse(timestampString);

                // Calculate the time difference between the current time and tweet timestamp
                long timeDifference = currentTime - tweetTimestamp.getTime();

                // Filter tweets based on the selected time interval
                if (timeInterval.equals("day") && timeDifference <= dayInterval) {
                    filteredTweets.add(tweetNode);
                } else if (timeInterval.equals("week") && timeDifference <= weekInterval) {
                    filteredTweets.add(tweetNode);
                } else if (timeInterval.equals("month") && timeDifference <= monthInterval) {
                    filteredTweets.add(tweetNode);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return filteredTweets;
    }


    public List<Map.Entry<String, HashtagStats>> calculateTopHashtags(List<JsonNode> tweets) {
        return HashtagRanking.calculateTopHashtags(tweets);
    }

    private void updateUI(List<Map.Entry<String, HashtagStats>> topHashtags) {
        hashtagList.clear();
        for (int i = 0; i < Math.min(topHashtags.size(), 100); i++) {
            Map.Entry<String, HashtagStats> entry = topHashtags.get(i);
            String hashtag = entry.getKey();
            HashtagStats stats = entry.getValue();
            hashtagList.add((i + 1) + ". " + hashtag);
        }
        hashtagListView.setItems(hashtagList);
    }

}

