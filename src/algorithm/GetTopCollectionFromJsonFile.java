package algorithm;

import model.dataPoint.DataPointv1;
import screen.controller.page.trendingPage.TrendingPageController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import algorithm.HashtagRanking.HashtagStats;
import data.connector.CollectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GetTopCollectionFromJsonFile {
	// Khác so với file trước đó, file này làm nhiệm vụ đọc và trả về dữ liệu cho một điểm dataPoint
	private static String filePath;
	private static int numberCollectionGet = 10;
	private static int currentNumberCollectionGet = 0;

	public GetTopCollectionFromJsonFile(String filePath) {
		super();
		this.filePath = filePath;
	}
	
	public ArrayList<DataPointv1> jsonReadAlgorithm() {

		ArrayList<DataPointv1> dataPoints = new ArrayList<DataPointv1>();

        // Sử dụng ObjectMapper để chuyển đổi JSON từ tệp thành List<DataPointv1>
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<CollectionDB> collectionDBList = Arrays.asList(objectMapper.readValue(new File(filePath), CollectionDB[].class));

            // Bây giờ bạn có thể làm việc với List<TwitterData>
            for (CollectionDB collectionData : collectionDBList) {
				if(collectionData.getBase().equals("ETH")){
					if(currentNumberCollectionGet==numberCollectionGet){
						break;
					}
					System.out.println("UserName: " + collectionData.getBase());
					System.out.println("Collection name : " + collectionData.getCollection());
					System.out.println();
					currentNumberCollectionGet+=1;
					String name = collectionData.getCollection();
					int nftRanking = currentNumberCollectionGet;
					float volume = parseFloatWithComma(collectionData.getVolume());
					dataPoints.add(new DataPointv1(name,nftRanking,volume));
				}
	        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        List<JsonNode> filteredTweets = new TrendingPageController().filterTweetsByTimeInterval("month");
        List<Map.Entry<String, HashtagStats>> topHashtags = new TrendingPageController().calculateTopHashtags(filteredTweets);
        for (int i = 0; i < Math.min(topHashtags.size(), 100); i++) {
            Map.Entry<String, HashtagStats> entry = topHashtags.get(i);
            for(DataPointv1 p1: dataPoints) {
            	System.out.println(p1.getName()+ entry.getKey()+topHashtags.size());
            	if((p1.getName().replaceAll(" ", "").toLowerCase()).contains(entry.getKey().replace("#", "").toLowerCase())) {
            		
            		p1.setHashtagRanking(i);
            	}
            }
            
        }
		return dataPoints;
	}
	
	private static float parseFloatWithComma(String input) {
		// Thay thế dấu "," bằng dấu "."
		String formattedInput = input.replace(",", ".");

		// Sử dụng parseFloat để chuyển đổi chuỗi thành kiểu float
		float result = Float.parseFloat(formattedInput);

		return result;
	}
	public static void main(String[] args){
		// test
		new GetTopCollectionFromJsonFile("data/json/collection/okx/topcollection_20231221_160726.json").jsonReadAlgorithm();
	}
}
