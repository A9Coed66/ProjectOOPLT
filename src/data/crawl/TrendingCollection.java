package data.crawl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


public class TrendingCollection{
	static String url = "https://www.okx.com/vi/web3/marketplace/rankings";
	final static String[] LABELS ={"Collection","Volume","FloorPrice","Liquidity","Listed","Base"};
	final static String PATH_TO_WEBDRIVER = "C:\\Users\\Admin\\Downloads\\Compressed\\chromedriver-win64\\chromedriver.exe";
	
	public static void crawler() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",PATH_TO_WEBDRIVER);
        WebDriver driver = new ChromeDriver();
        List<String> collections = new ArrayList<String>();
        List<String> volumes = new ArrayList<String>();
        List<String> floorPrices = new ArrayList<String>();
        List<String> liquiditys = new ArrayList<String>();
        List<String> listeds = new ArrayList<String>();
        List<String> bases = new ArrayList<String>();
        
        
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
					// TODO: handle exception
				}
			 }
			 
			 List<WebElement> webElements = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div[3]/div/div[1]/div/a/div"));
			 for (WebElement webElement : webElements) {
				String collection = webElement.findElement(By.xpath(".//div[2]/div/div[2]/div/div[1]/span")).getText();
				System.out.println(collection);
				String volume = webElement.findElement(By.xpath(".//div[3]/div/span")).getText();
//				System.out.println(volume);
				String floorPrice = webElement.findElement(By.xpath(".//div[5]")).getText();
//				System.out.println(floorPrice);
				String liquidity = webElement.findElement(By.xpath(".//div[6]/div")).getText();
//				System.out.println(liquidity);
				String listed = webElement.findElement(By.xpath(".//div[7]")).getText();
//				System.out.println(listed);
				String base = webElement.findElement(By.xpath(".//div[3]/img")).getAttribute("alt");
//				System.out.println(base);
				collections.add(collection);
				volumes.add(volume);
				floorPrices.add(floorPrice);
				liquiditys.add(liquidity);
				listeds.add(listed);
				bases.add(base);
			}
			 
		}
		
		
        
		// Đóng trình duyệt
        driver.quit();
        try {
          

            // Thêm dữ liệu vào DataFrame (điều này phải được điều chỉnh tùy thuộc vào cách bạn tổ chức dữ liệu)
            List<List<String>> dataFrame = new ArrayList<>();
            dataFrame.add(collections);
            dataFrame.add(volumes);
            dataFrame.add(floorPrices);
            dataFrame.add(liquiditys);
            dataFrame.add(listeds);
            dataFrame.add(bases);
        
            
            // Tạo dữ liệu từ DataFrame
            List<Map<String,String>> jsonData = createJsonData(dataFrame);

            // Xuất dữ liệu ra file JSON
            writeJsonFile(jsonData);
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
	
	    private static void writeJsonFile(List<Map<String,String>> jsonData) throws IOException {
	    	ObjectMapper objectMapper = new ObjectMapper();
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        String fileName = "data/json/collection/okx/topcollection_" + timestamp + ".json";

	        try {
	        	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	 	        objectMapper.writeValue(new File(fileName), jsonData);
	            System.out.println("Đã ghi vào file " + fileName + " thành công.");
	        } catch (IOException e) {
	          
	        	 e.printStackTrace();
	        }
	       
	    }

    public static void main(String[] args) throws InterruptedException {
		crawler();
	}
}