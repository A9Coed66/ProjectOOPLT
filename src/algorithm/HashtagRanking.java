package algorithm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class HashtagRanking {
    private static int maxLikes = 0;
    private static int maxReplies = 0;
    private static int maxRetweets = 0;
    private static int maxAppearances = 0;

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read the tweets from datatwitter.json
            JsonNode tweetsNode = objectMapper.readTree(new File("data/json/post/twitter/datatwitter.json"));

            // Count hashtag occurrences and calculate engagement metrics
            Map<String, HashtagStats> hashtagStatsMap = new HashMap<>();

            for (JsonNode tweetNode : tweetsNode) {
                if (tweetNode.has("Hastags")) {
                    int likes = tweetNode.get("Like").asInt();
                    int replies = tweetNode.get("Reply").asInt();
                    int retweets = tweetNode.get("Retweet").asInt();

                    String hashtags = tweetNode.get("Hastags").asText().toLowerCase();
                    String[] hashtagArray = hashtags.split("#");

                    // Skip the first element as it's empty
                    for (int i = 1; i < hashtagArray.length; i++) {
                        String hashtag = "#" + hashtagArray[i].split("\\s")[0];  // Extract the hashtag, excluding any additional characters

                        // Update hashtag stats
                        hashtagStatsMap.computeIfAbsent(hashtag, k -> new HashtagStats()).updateStats(likes, replies, retweets);
                    }
                }
            }

            // Update max values for normalization
            for (HashtagStats stats : hashtagStatsMap.values()) {
                maxLikes = Math.max(maxLikes, stats.likes);
                maxReplies = Math.max(maxReplies, stats.replies);
                maxRetweets = Math.max(maxRetweets, stats.retweets);
                maxAppearances = Math.max(maxAppearances, stats.appearances);
            }

            // Create a list of hashtag entries for sorting
            List<Map.Entry<String, HashtagStats>> hashtagList = new ArrayList<>(hashtagStatsMap.entrySet());

            // Sort the hashtags based on a custom metric (e.g., normalized total engagement) in descending order
            hashtagList.sort(Comparator.comparingDouble(entry -> ((Entry<String, HashtagStats>) entry).getValue().getNormalizedTotalEngagement()).reversed());

            // Display the ranking
            System.out.println("Hashtag Ranking:");
            for (int i = 0; i < hashtagList.size(); i++) {
                Map.Entry<String, HashtagStats> entry = hashtagList.get(i);
                printStats(i + 1, entry.getKey(), entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Map.Entry<String, HashtagStats>> calculateTopHashtags(List<JsonNode> tweets) {
        // Count hashtag occurrences and calculate engagement metrics
        Map<String, HashtagStats> hashtagStatsMap = new HashMap<>();

        for (JsonNode tweetNode : tweets) {
            if (tweetNode.has("Hastags")) {
                int likes = tweetNode.get("Like").asInt();
                int replies = tweetNode.get("Reply").asInt();
                int retweets = tweetNode.get("Retweet").asInt();

                String hashtags = tweetNode.get("Hastags").asText().toLowerCase();
                String[] hashtagArray = hashtags.split("#");

                // Skip the first element as it's empty
                for (int i = 1; i < hashtagArray.length; i++) {
                    String hashtag = "#" + hashtagArray[i].split("\\s")[0];  // Extract the hashtag, excluding any additional characters

                    // Update hashtag stats
                    hashtagStatsMap.computeIfAbsent(hashtag, k -> new HashtagStats()).updateStats(likes, replies, retweets);
                }
            }
        }

        // Update max values for normalization
        for (HashtagStats stats : hashtagStatsMap.values()) {
            maxLikes = Math.max(maxLikes, stats.likes);
            maxReplies = Math.max(maxReplies, stats.replies);
            maxRetweets = Math.max(maxRetweets, stats.retweets);
            maxAppearances = Math.max(maxAppearances, stats.appearances);
        }

        // Create a list of hashtag entries for sorting
        List<Map.Entry<String, HashtagStats>> hashtagList = new ArrayList<>(hashtagStatsMap.entrySet());

        // Sort the hashtags based on a custom metric (e.g., normalized total engagement) in descending order
        hashtagList.sort(Comparator.comparingDouble(entry -> ((Entry<String, HashtagStats>) entry).getValue().getNormalizedTotalEngagement()).reversed());

        return hashtagList;
    }

    private static void printStats(int rank, String hashtag, HashtagStats stats) {
        System.out.println(rank + ". " + hashtag + ": " + stats);
    }

    public static class HashtagStats {
        private int likes;
        private int replies;
        private int retweets;
        private int appearances;

        public void updateStats(int likes, int replies, int retweets) {
            this.likes += likes;
            this.replies += replies;
            this.retweets += retweets;
            this.appearances++;
        }

        public double getNormalizedTotalEngagement() {
            // Calculate the total engagement with a 0.5 weight for each metric
            double normalizedLikes = this.likes / (double) maxLikes;
            double normalizedReplies = this.replies / (double) maxReplies;
            double normalizedRetweets = this.retweets / (double) maxRetweets;
            double normalizedAppearances = (double) this.appearances / maxAppearances;

            return 0.5 * normalizedAppearances + (0.5/3) * (normalizedLikes + normalizedReplies + normalizedRetweets);
        }

        @Override
        public String toString() {
            // Return a string representation of the normalized metrics
            return "Normalized Engagement - Likes: " + likes +
                    ", Replies: " + replies +
                    ", Retweets: " + retweets +
                    ", Appearances: " + appearances +
                    ", Total: " + getNormalizedTotalEngagement();
        }
    }
}