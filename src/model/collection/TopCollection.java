package model.collection;

public class TopCollection{
	private int top;
	private String name ;
	private String volume ;
	private String floorPrice ;
	private String liquidity ;
	private String listed;
	
	
	
	public TopCollection  (int top, String name, String volume, String floorPrice, String liquidity,String listed, String base) {
		super();
		this.top = top;
		this.name = name;
		this.volume = volume;
		this.floorPrice = floorPrice;
		this.liquidity = liquidity;
		this.listed = listed;
		this.base = base;
	
	}
	public void getAttribute(int i) {
		
	}
	public void setAttribute(int i) {
		
	}
	public void print() {
		System.out.println("Top:"+this.top+" Name:"+ this.name+" Volume:"+this.volume+" FloorPrice:"+this.floorPrice+ " Liquidity:"+this.liquidity+ " Listed:"+ this.listed +" Base:" +this.base);
	}
	
	public String getListed() {
		return listed;
	}
	public void setListed(String listed) {
		this.listed = listed;
	}
	private String base ;
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getFloorPrice() {
		return floorPrice;
	}
	public void setFloorPrice(String floorPrice) {
		this.floorPrice = floorPrice;
	}
	public String getLiquidity() {
		return liquidity;
	}
	public void setLiquidity(String liquidity) {
		this.liquidity = liquidity;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	
    
}
