package data.crawl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class SteemitCrawler{
	static String url = "https://steemit.com/created/nft";
	final static String[] LABELS ={"Avatar","Name","Date","Title","Content","Comment","PostUrl","Hastags","Image","Price","Vote"};
	final static String PATH_TO_WEBDRIVER = "chromedriver-win64/chromedriver.exe";

	
	public static void crawler() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",PATH_TO_WEBDRIVER);
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Tạo danh sách để lưu trữ dữ liệu
        List<String> avatars = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        List<String> contents = new ArrayList<>();
        List<String> comments = new ArrayList<>();
        List<String> posturls = new ArrayList<>();
        List<String> hashtagss = new ArrayList<>();
        List<String> images = new ArrayList<>();
        List<String> prices = new ArrayList<>();
        List<String> votes = new ArrayList<>();
        
        try {
  
            long tmp = 0;
            long height = 1;
            while (true) {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Thread.sleep(5000);
                tmp = height;
                height = (Long) js.executeScript("return Math.max( document.body.scrollHeight, document.body.offsetHeight, document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight);");
                if (tmp == height) break;
            }
            // Thêm tiêu đề cho tệp CSV
            List<WebElement> posts = driver.findElements(By.cssSelector(".articles__summary")); // Thay thế 'selector-for-posts' với selector thích hợp
            for (WebElement post : posts) {
                    // Thu thập dữ liệu từ mỗi bài viết
                    String avatar = post.findElement(By.cssSelector(".Userpic")).getAttribute("style");
                    avatar = avatar.replace("\");", "").replace("background-image: url(\"", "");
                    String name = post.findElement(By.cssSelector(".author")).getText();
                    String title = post.findElement(By.cssSelector(".articles__h2")).getText();
                    String posturl = post.findElement(By.xpath(".//a[@class='timestamp__link']")).getAttribute("href");
                    try {
                    	String image = post.findElement(By.cssSelector(".articles__feature-img img")).getAttribute("srcset");
                        images.add(image);
                    }
                    catch(Exception e){
                    	images.add("");
                    }
                    String date = post.findElement(By.cssSelector(".timestamp__time span")).getAttribute("title");
                    
                    String hashtag = post.findElement(By.xpath(".//span[@class='articles__tag-link']/a")).getText();
                    String content = post.findElement(By.cssSelector(".PostSummary__body")).getText();
                    String price = post.findElement(By.cssSelector(".FormattedAsset")).getText();
                    String vote = post.findElement(By.xpath(".//span[2]/span[1]")).getText();
                    vote.replace("&nbsp;","");
                    String comment = post.findElement(By.xpath(".//span[2]/span[2]/a")).getText();
                    comment.replace("&nbsp;","");
                    
                    avatars.add(avatar);
                    names.add(name);
                    titles.add(title);
                    posturls.add(posturl);
                    dates.add(date);
                    hashtagss.add(hashtag);
                    contents.add(content);
                    prices.add(price);
                    votes.add(vote);
                    comments.add(comment);
      
            }
          
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
	    driver.quit();
	    try {
      

        // Thêm dữ liệu vào DataFrame (điều này phải được điều chỉnh tùy thuộc vào cách bạn tổ chức dữ liệu)
        List<List<String>> dataFrame = new ArrayList<>();
        dataFrame.add(avatars);
        dataFrame.add(names);
        dataFrame.add(dates);
        dataFrame.add(titles);
        dataFrame.add(contents);
        dataFrame.add(comments);
        dataFrame.add(posturls);
        dataFrame.add(hashtagss);
        dataFrame.add(images);
        dataFrame.add(prices);
        dataFrame.add(votes);
        
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
        String fileName = "data/json/post/steemit/blog_timecrawl-" + timestamp + ".json";

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
