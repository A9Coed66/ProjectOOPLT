package data.connector;
import com.fasterxml.jackson.annotation.JsonProperty;
public class CollectionDB {
	    @JsonProperty("FloorPrice")
	    private String floorPrice;

	    @JsonProperty("Listed")
	    private String listed;

	    @JsonProperty("Volume")
	    private String volume;

	    @JsonProperty("Liquidity")
	    private String liquidity;

	    @JsonProperty("Collection")
	    private String collection;

	    @JsonProperty("Base")
	    private String base;

	    // Các phương thức getter/setter 

	    public String getFloorPrice() {
	        return floorPrice;
	    }

	    public void setFloorPrice(String floorPrice) {
	        this.floorPrice = floorPrice;
	    }

	    public String getListed() {
	        return listed;
	    }

	    public void setListed(String listed) {
	        this.listed = listed;
	    }

	    public String getVolume() {
	        return volume;
	    }

	    public void setVolume(String volume) {
	        this.volume = volume;
	    }

	    public String getLiquidity() {
	        return liquidity;
	    }

	    public void setLiquidity(String liquidity) {
	        this.liquidity = liquidity;
	    }

	    public String getCollection() {
	        return collection;
	    }

	    public void setCollection(String collection) {
	        this.collection = collection;
	    }

	    public String getBase() {
	        return base;
	    }

	    public void setBase(String base) {
	        this.base = base;
	    }
	}

