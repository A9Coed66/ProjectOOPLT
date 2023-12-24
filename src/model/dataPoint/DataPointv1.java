package model.dataPoint;

public class DataPointv1 {
	// Collection data point properties: name, nftRanking, number tweet blog,
	// ranking tweet blog, volume in a day
	private String name;
	private int nftRanking;
	private float volume; // Co the update thanh list luu volume trong 1-7-30 ngay
	private int numberTweetblog;
	private int tweetRanking;

	public String getName() {
		return name;
	}

	public int getNftRanking() {
		return nftRanking;
	}

	public float getVolume() {
		return volume;
	}

	public int getTweetRanking() {
		return tweetRanking;
	}

	public DataPointv1(String name, int nftRanking, int tweetRanking) {
		this.name = name;
		this.nftRanking = nftRanking;
		this.tweetRanking = tweetRanking;
	}

	public DataPointv1(String name, int nftRanking, float volume) {
		this.name = name;
		this.nftRanking = nftRanking;
		this.volume = volume;
	}

	public Number getProperty(String type) {
		return switch (type) {
		case "Trending" -> nftRanking;
		case "Volume" -> volume;
		case "Hashtag" -> tweetRanking;
		default -> throw new IllegalArgumentException("Invalid type: " + type);
		};
	}
}
