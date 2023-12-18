package analyze;

public class DataPoint {
    private String name;
    private int nftRanking;
    private int hashtagRanking;

    public DataPoint(String name, int nftRanking, int hashtagRanking) {
        this.name = name;
        this.nftRanking = nftRanking;
        this.hashtagRanking = hashtagRanking;
    }

    public String getName() {
        return name;
    }

    public int getNftRanking() {
        return nftRanking;
    }

    public int getHashtagRanking() {
        return hashtagRanking;
    }
}

