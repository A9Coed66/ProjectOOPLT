package analyze;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class HashtagRanking {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read the tweets from datatwitter.json
            JsonNode tweetsNode = objectMapper.readTree(new File("data/json/datatwitter.json"));

            // Count hashtag occurrences and calculate engagement metrics
            Map<String, HashtagStats> hashtagStatsMap = new HashMap<>();
            int maxLikes = 0;
            int maxReplies = 0;
            int maxRetweets = 0;
            int maxAppearances = 0;

            for (JsonNode tweetNode : tweetsNode) {
                if (tweetNode.has("Hastags")) {
                    int likes = tweetNode.get("Like").asInt();
                    int replies = tweetNode.get("Reply").asInt();
                    int retweets = tweetNode.get("Retweet").asInt();

                    // Update max values for normalization
                    maxLikes = Math.max(maxLikes, likes);
                    maxReplies = Math.max(maxReplies, replies);
                    maxRetweets = Math.max(maxRetweets, retweets);

                    String hashtags = tweetNode.get("Hastags").asText().toLowerCase();
                    String[] hashtagArray = hashtags.split("#");

                    // Skip the first element as it's empty
                    for (int i = 1; i < hashtagArray.length; i++) {
                        String hashtag = "#" + hashtagArray[i].split("\\s")[0];  // Extract the hashtag, excluding any additional characters

                        // Update max appearances
                        maxAppearances = Math.max(maxAppearances, hashtagStatsMap.computeIfAbsent(hashtag, k -> new HashtagStats()).incrementAppearances());
                        
                        // Update hashtag stats
                        hashtagStatsMap.get(hashtag).updateStats(likes, replies, retweets);
                    }
                }
            }

            // Normalize the engagement metrics
            for (HashtagStats stats : hashtagStatsMap.values()) {
                stats.normalize(maxLikes, maxReplies, maxRetweets, maxAppearances);
            }

            // Create a list of hashtag entries for sorting
            List<Map.Entry<String, HashtagStats>> hashtagList = new ArrayList<>(hashtagStatsMap.entrySet());

            // Sort the hashtags based on a custom metric (e.g., normalized total engagement) in descending order
            hashtagList.sort(Comparator.comparingDouble(entry -> ((Entry<String, HashtagStats>) entry).getValue().getNormalizedTotalEngagement()).reversed());

            // Display the ranking
            System.out.println("Hashtag Ranking:");
            for (int i = 0; i < hashtagList.size(); i++) {
                Map.Entry<String, HashtagStats> entry = hashtagList.get(i);
                System.out.println((i + 1) + ". " + entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class HashtagStats {
        private double normalizedLikes;
        private double normalizedReplies;
        private double normalizedRetweets;
        private double normalizedAppearances;

        public int incrementAppearances() {
            // Increment appearances and return the updated count
            return (int) ++normalizedAppearances;
        }

        public void updateStats(int likes, int replies, int retweets) {
            // Update normalized values
            normalizedLikes += normalize(likes);
            normalizedReplies += normalize(replies);
            normalizedRetweets += normalize(retweets);
        }

        public void normalize(int maxLikes, int maxReplies, int maxRetweets, int maxAppearances) {
            // Normalize the values to be between 0 and 1
            normalizedLikes /= maxLikes;
            normalizedReplies /= maxReplies;
            normalizedRetweets /= maxRetweets;
            normalizedAppearances /= maxAppearances;
        }

        public double getNormalizedTotalEngagement() {
            // Calculate the total engagement with a 0.5 weight for each metric
            return 0.5 * (0.5 * normalizedAppearances + 0.5 * (normalizedLikes + normalizedReplies + normalizedRetweets));
        }

        private double normalize(int value) {
            // Normalize the value to be between 0 and 1
            return (double) value / Math.max(1, value);  // Avoid division by zero
        }

        @Override
        public String toString() {
            return "Normalized Engagement - Likes: " + normalizedLikes +
                    ", Replies: " + normalizedReplies +
                    ", Retweets: " + normalizedRetweets +
                    ", Appearances: " + normalizedAppearances +
                    ", Total: " + getNormalizedTotalEngagement();
        }
    }
}



