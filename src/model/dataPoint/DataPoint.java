package model.dataPoint;

public class DataPoint {
    private String name;
    private int nftRanking;
    private int tweetblogRanking;

    public DataPoint(String name, int nftRanking, int tweetblogRanking) {
        this.name = name;
        this.nftRanking = nftRanking;
        this.tweetblogRanking = tweetblogRanking;
    }

    public String getName() {
        return name;
    }

    public int getNftRanking() {
        return nftRanking;
    }

    public int getTweetBlogRanking() {
        return tweetblogRanking;
    }
}

