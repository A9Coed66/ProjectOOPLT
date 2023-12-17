package data.crawl;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class TwitterCrawler {
		private static String query;
	    public static void main(String args) {
	    	query = args;
	        // Đặt đường dẫn đến Chromedriver.exe
	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Downloads\\Compressed\\chromedriver-win64\\chromedriver.exe");       
	        // Khởi tạo WebDriver
	        WebDriver driver = new ChromeDriver();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	        // Mở trang đăng nhập Twitter
	        driver.get("https://twitter.com/login");
	        driver.manage().window().maximize();
	        driver.manage().deleteAllCookies();
	        // Tìm kiếm và đặt các yếu tố cần thiết
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='text']")));
	        driver.findElement(By.xpath("//input[@name='text']")).sendKeys("crawl_nigh12359");
	        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='password']")));
	        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("onlyforcrawl1");
	        driver.findElement(By.xpath("//span[contains(text(),'Log in')]")).click();
	        	
	        // Chờ đến khi trang chính xuất hiện
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-testid='SearchBox_Search_Input']")));
	
	        // Tạo danh sách để lưu trữ dữ liệu
	        List<String> names = new ArrayList<>();
	        List<String> userNames = new ArrayList<>();
	        List<String> timeStamps = new ArrayList<>();
	        List<String> tweets = new ArrayList<>();
	        List<String> replys = new ArrayList<>();
	        List<String> reTweets = new ArrayList<>();
	        List<String> likes = new ArrayList<>();

	        
	
	        // Tìm kiếm và lấy dữ liệu
//	        String subject = "#NFT min_faves:50";
	        WebElement searchBox = driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']"));
	        searchBox.sendKeys(query);
	        searchBox.sendKeys(Keys.ENTER);
	
	        // Chờ đến khi nút Media xuất hiện và nhấp vào nó
	        //Twitter mới thay đổi giao diện nên cái này không cần dùng nữa :v
//	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Media')]")));
//	        WebElement media = driver.findElement(By.xpath("//span[contains(text(),'Media')]"));
//	        media.click();
	
	        // Lấy danh sách các bài đăng
	        List<WebElement> articles = driver.findElements(By.xpath("//article[@data-testid='tweet']"));
	
	        // In số lượng bài đăng
	        System.out.println(articles.size());
	        while (true) {
	        	// Lặp qua từng bài đăng
	        	for (WebElement article : articles) {
		            try {
		            	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[normalize-space()]")));
		                WebElement userNameElement = article.findElement(By.xpath(".//span[normalize-space()]"));
		                WebElement timeElement = article.findElement(By.xpath(".//time"));
		                WebElement replyElement = article.findElement(By.xpath(".//div[@data-testid='reply']"));
		                WebElement reTweetElement = article.findElement(By.xpath(".//div[@data-testid='retweet']"));
		                WebElement likeElement = article.findElement(By.xpath(".//div[@data-testid='like']"));
		                WebElement tweetElement = article.findElement(By.xpath(".//div[@data-testid='tweetText']"));
		
		                // Lấy thông tin từ các yếu tố
		                String Name = userNameElement.getText();
		                String UserName = article.findElement(By.xpath(".//span[contains(text(), '@')]")).getText();
		                String TimeStamp = timeElement.getAttribute("datetime");
		                String Reply = replyElement.getText();
		                String reTweet = reTweetElement.getText();
		                String Like = likeElement.getText();
		                String Tweet = tweetElement.getText();
		
		                // Thêm thông tin vào danh sách
		                names.add(Name);
		                userNames.add(UserName);
		                timeStamps.add(TimeStamp);
		                replys.add(Reply);
		                reTweets.add(reTweet);
		                likes.add(Like);
		                tweets.add(Tweet);
		
		                // In thông tin
		                System.out.println("Name: " + Name);
		                System.out.println("UserName: " + UserName);
		                System.out.println("TimeStamp: " + TimeStamp);
		                System.out.println("Reply: " + Reply);
		                System.out.println("reTweet: " + reTweet);
		                System.out.println("Like: " + Like);
		                
		
		                // Lấy thông tin tweet (đoạn mã đã bị comment bỏ)
		                // ...
		
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
	        	try {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//article[@data-testid='tweet']")));
					articles = driver.findElements(By.xpath("//article[@data-testid='tweet']"));
				} catch (Exception e) {
					System.out.println("Khong tim them tweets");
					break;
				}
	        	if(tweets.size() > 20)  break;
	        }
	        
	
	        // Đóng trình duyệt
	        driver.quit();
	        try {
	          
	
	            // Thêm dữ liệu vào DataFrame (điều này phải được điều chỉnh tùy thuộc vào cách bạn tổ chức dữ liệu)
	            // Ví dụ: giả sử bạn đã có danh sách các dữ liệu từ Names đến Likes
	            List<List<String>> dataFrame = new ArrayList<>();
	            dataFrame.add(names);
	            dataFrame.add(userNames);
	            dataFrame.add(timeStamps);
	            dataFrame.add(tweets);
	            dataFrame.add(replys);
	            dataFrame.add(reTweets);
	            dataFrame.add(likes);
	
	            // Tạo dữ liệu từ DataFrame
	            List<Map<String, String>> jsonData = createJsonData(dataFrame);
	
	            // Xuất dữ liệu ra file JSON
	            writeJsonFile(jsonData, "D:\\a_learning_code\\java\\ProjectOOPLT\\data\\json\\datatwitter.json");
	
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
		                record.put(dataFrame.get(j).get(0), dataFrame.get(j).get(i));
		            }
		            jsonData.add(record);
		        }
		
		        return jsonData;
		    }
		
		    private static void writeJsonFile(List<Map<String, String>> jsonData, String jsonFilePath) throws IOException {
		        ObjectMapper objectMapper = new ObjectMapper();
		        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		        objectMapper.writeValue(new File(jsonFilePath), jsonData);
		    }

}