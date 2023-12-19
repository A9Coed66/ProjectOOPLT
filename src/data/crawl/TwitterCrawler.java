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
		static String query;
		static int number;
		final static String[] LABELS ={"Avatar","Name","UserName","TimeStamp","Tweet","Reply","Retweet","Like","PostUrl","Hastags","Tags","Image"};
		final static String PATH_TO_JSON ="D:\\a_learning_code\\java\\ProjectOOPLT\\data\\json\\datatwitter.json";
		final static String PATH_TO_WEBDRIVER = "C:\\Users\\Admin\\Downloads\\Compressed\\chromedriver-win64\\chromedriver.exe";
		
	    public static void main(String args1,int args2) {
	    	query = args1;
	    	number = args2;
	        // Đặt đường dẫn đến Chromedriver.exe
	        System.setProperty("webdriver.chrome.driver",PATH_TO_WEBDRIVER );       
	        // Khởi tạo WebDriver
	        WebDriver driver = new ChromeDriver();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	
	        // Mở trang đăng nhập Twitter
	        driver.get("https://twitter.com/login");
	        driver.manage().window().maximize();
	        driver.manage().deleteAllCookies();
	        // Tìm kiếm và đặt các yếu tố cần thiết
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='text']")));
	        driver.findElement(By.xpath("//input[@name='text']")).sendKeys("crawler21oop");
	        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='password']")));
	        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("ooplt2023");
	        driver.findElement(By.xpath("//span[contains(text(),'Log in')]")).click();
	        	
	        // Chờ đến khi trang chính xuất hiện
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-testid='SearchBox_Search_Input']")));
	
	        // Tạo danh sách để lưu trữ dữ liệu
	        List<String> avatars = new ArrayList<>();
	        List<String> names = new ArrayList<>();
	        List<String> userNames = new ArrayList<>();
	        List<String> timeStamps = new ArrayList<>();
	        List<String> tweets = new ArrayList<>();
	        List<String> replys = new ArrayList<>();
	        List<String> reTweets = new ArrayList<>();
	        List<String> likes = new ArrayList<>();
//	        List<String> views = new ArrayList<>();
	        List<String> posturls = new ArrayList<>();
	        List<String> images = new ArrayList<>();
	        List<String> hastagss = new ArrayList<>();
	        List<String> tagss = new ArrayList<>();
	        


	        // Tìm kiếm và lấy dữ liệu
	        WebElement searchBox = driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']"));
	        searchBox.sendKeys(query);
	        searchBox.sendKeys(Keys.ENTER);
	        
	        // Lấy danh sách các bài đăng
	        List<WebElement> articles = driver.findElements(By.xpath("//article[@data-testid='tweet']"));
	        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            long bheight = 0;
	        // In số lượng bài đăng
	        System.out.println(articles.size());
	        while (true) {
	        	
	        	// Lặp qua từng bài đăng
	       
	        	for (WebElement article : articles) {
		            try {
		            	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[normalize-space()]")));
		            	WebElement avatarElement = article.findElement(By.xpath(".//img[contains(@src,'profile_images')]"));
		                WebElement nameElement = article.findElement(By.xpath(".//span[normalize-space()]"));
		                WebElement timeElement = article.findElement(By.xpath(".//time"));
		                WebElement replyElement = article.findElement(By.xpath(".//div[@data-testid='reply']"));
		                WebElement reTweetElement = article.findElement(By.xpath(".//div[@data-testid='retweet']"));
		                WebElement likeElement = article.findElement(By.xpath(".//div[@data-testid='like']"));
		                WebElement viewElement = article.findElement(By.xpath(".//a[contains(@aria-label,'view')]"));
		                WebElement tweetElement = article.findElement(By.xpath(".//div[@data-testid='tweetText']"));
		                WebElement postLinkElement = article.findElement(By.cssSelector("a[aria-label*='ago']"));
		             // Lấy thông tin từ các yếu tố
		                String avatar = avatarElement.getText();
		                String name = nameElement.getText();
		                String userName = article.findElement(By.xpath(".//span[contains(text(), '@')]")).getText();
		                String timeStamp = timeElement.getAttribute("datetime");
		                String reply = replyElement.getText();
		                String reTweet = reTweetElement.getText();
		                String like = likeElement.getText();
		                String tweet = tweetElement.getText();
		                String posturl = postLinkElement.getAttribute("href");
		                try {
		                	List<WebElement> hastagElements = article.findElements(By.xpath(".//*[contains(@href,'/hashtag')]"));
			                StringBuffer hastags = new StringBuffer();
			                for(WebElement hastagElement:hastagElements) {
			                	hastags.append(hastagElement.getText());
			                }
			                hastagss.add(hastags.toString());
						} catch (Exception e) {
							hastagss.add("");
							System.out.println("Bài viết không có hastag");
						}
		               
			            try {
			            	   WebElement imageElement = article.findElement(By.xpath(".//img[@alt='Image']"));
			            	   String image = imageElement.getAttribute("src");
			            	   images.add(image);
			             } catch (Exception e) {
			            	 	images.add("");
			            	   System.out.println("Bài viết không có ảnh");
			             }
			            
			            try {
			            	List<WebElement> tagElements = article.findElements(By.xpath(".//a[starts-with(text(), '@')]"));
			            	StringBuffer tags = new StringBuffer();
			                for(WebElement tagElement:tagElements) {
			                	tags.append(tagElement.getText());
			                }
			                tagss.add(tags.toString());
						} catch (Exception e) {
							tagss.add("");
							System.out.println("Bài viết không có tag");
						}
		                
		
		                // Thêm thông tin vào danh sách
			            
			            avatars.add(avatar);
		                names.add(name);
		                userNames.add(userName);
		                timeStamps.add(timeStamp);
		                replys.add(reply);
		                reTweets.add(reTweet);
		                likes.add(like);
		                tweets.add(tweet);
		                posturls.add(posturl);
		                
		               
		
		                // In thông tin
		                System.out.println("Name: " + name);
		                System.out.println("UserName: " + userName);
		                System.out.println("TimeStamp: " + timeStamp);
		                System.out.println("Reply: " + reply);
		                System.out.println("reTweet: " + reTweet);
		                System.out.println("Like: " + like);
		                
		
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
	        	try {
	       
	        		JavascriptExecutor js = (JavascriptExecutor) driver;
	        		js.executeScript("window.scrollBy(0,document.body.scrollHeight );");
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//article[@data-testid='tweet']")));
					articles = driver.findElements(By.xpath("//article[@data-testid='tweet']"));
				} catch (Exception e) {
					System.out.println("Khong tim them tweets");
					break;
				}
	        	if(tweets.size() > number)  break;
	        
	            jsExecutor = (JavascriptExecutor) driver;
	            long height = (Long) jsExecutor.executeScript("return Math.max( document.body.scrollHeight, document.body.offsetHeight, document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight);");
	            if(bheight == height) break;
	        }
	        
	
	        // Đóng trình duyệt
	        driver.quit();
	        try {
	          
	
	            // Thêm dữ liệu vào DataFrame (điều này phải được điều chỉnh tùy thuộc vào cách bạn tổ chức dữ liệu)
	            List<List<String>> dataFrame = new ArrayList<>();
	            dataFrame.add(avatars);
	            dataFrame.add(names);
	            dataFrame.add(userNames);
	            dataFrame.add(timeStamps);
	            dataFrame.add(tweets);
	            dataFrame.add(replys);
	            dataFrame.add(reTweets);
	            dataFrame.add(likes);
	            dataFrame.add(posturls);
	            dataFrame.add(hastagss);
	            dataFrame.add(tagss);
	            dataFrame.add(images);
	            
	            // Tạo dữ liệu từ DataFrame
	            List<Map<String,String>> jsonData = createJsonData(dataFrame);
	
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

}