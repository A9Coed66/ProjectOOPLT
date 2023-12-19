package data.crawl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.collection.TopCollection;


public class TrendingCollection{
	static String url = "https://www.okx.com/vi/web3/marketplace/rankings";
	final static String[] LABELS ={"Collection","Volume","FloorPrice","Liquidity","listed","base"};
	final static String PATH_TO_JSON ="D:\\a_learning_code\\java\\ProjectOOPLT\\data\\json\\collectiontrending.json";
	final static String PATH_TO_WEBDRIVER = "C:\\Users\\Admin\\Downloads\\Compressed\\chromedriver-win64\\chromedriver.exe";
	
	public static void runTestSelenium() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",PATH_TO_WEBDRIVER);
        WebDriver driver = new ChromeDriver();
        List<TopCollection> collections = new ArrayList<>();
        int top = 0;
		for(int i = 1 ; i <= 21; i++) {
			String page = String.format("%s/page/%d", url,i);
			 driver.get(page);
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			 int j = 0;
			 while(true) {
				 j++;
				 if(j>10) break;
				 try {
					driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div[3]/div/div[1]/div/a[100]"));
					break;
				} catch (Exception e) {
					js.executeScript("window.scrollBy(0, 400)");
				}
			 }
			 
			 List<WebElement> webElements = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div[3]/div/div[1]/div/a/div"));
			 for (WebElement webElement : webElements) {
				top += 1;
				String name = webElement.findElement(By.xpath(".//div[2]/div/div[2]/div/div[1]/span")).getText();
				String volume = webElement.findElement(By.xpath(".//div[3]/div/span")).getText();
				String floorPrice = webElement.findElement(By.xpath(".//div[5]")).getText();
				String liquidity = webElement.findElement(By.xpath(".//div[6]/div")).getText();
				String listed = webElement.findElement(By.xpath(".//div[7]")).getText();
				String base = webElement.findElement(By.xpath(".//div[3]/img")).getAttribute("alt");
				TopCollection collection = new TopCollection(top,name,volume,floorPrice,liquidity,listed,base);
				collection.print();
				collections.add(collection);
			}
			 
		}	
		// Đóng trình duyệt
        driver.quit();
        try {
     // Tạo dữ liệu từ DataFrame
            List<Map<String,TopCollection>> jsonData = createJsonData(collections);

            // Xuất dữ liệu ra file JSON
            writeJsonFile(jsonData, PATH_TO_JSON);
            System.out.println("Dữ liệu đã được xuất ra file JSON thành công.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }

	    private static List<Map<String, String>> createJsonData(List<List<String>> dataFrame) {
	        List<Map<String, String>> jsonData = new ArrayList<>();
	
	        for (int i = 0; i < dataFrame.get(0).size(); i++) {
	            Map<String, String> record = new HashMap<>();
	            for (int j = 0; j < dataFrame.size(); j++) {
	                record.put(LABELS[j], dataFrame.get(j).get(i));
	            }
	            jsonData.add(record);
	        }
	
	        return jsonData;
	    }
	
	    private static void writeJsonFile(List<Map<String,String>> jsonData, String jsonFilePath) throws IOException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	        objectMapper.writeValue(new File(jsonFilePath), jsonData);
	    }

    public static void main(String[] args) throws InterruptedException {
		runTestSelenium();
	}
}