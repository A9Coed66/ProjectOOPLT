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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import data.connector.CollectionDB;

public class TopCollectionTweetCrawler{
	static String url = "https://nitter.net/search?f=tweets";
	final static String[] LABELS ={"Avatar","Name","UserName","TimeStamp","Tweet","Reply","Retweet","Like","PostUrl","Hastags","Tags","Image"};
	final static String PATH_TO_WEBDRIVER = "C:\\Users\\Admin\\Downloads\\Compressed\\chromedriver-win64\\chromedriver.exe";

	
	public static void crawler() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",PATH_TO_WEBDRIVER);
        WebDriver driver = new ChromeDriver();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Tạo danh sách để lưu trữ dữ liệu
        List<String> avatars = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> userNames = new ArrayList<>();
        List<String> timeStamps = new ArrayList<>();
        List<String> tweets = new ArrayList<>();
        List<String> replys = new ArrayList<>();
        List<String> reTweets = new ArrayList<>();
        List<String> likes = new ArrayList<>();
        List<String> posturls = new ArrayList<>();
        List<String> images = new ArrayList<>();
        List<String> hashtagss = new ArrayList<>();
        List<String> tagss = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(new File("data/json/collection/okx/topcollection_20231221_160726.json"));
            int i = 0;
            for (JsonNode node : rootNode) {
            	i++;
            	CollectionDB collectionDB = objectMapper.treeToValue(node, CollectionDB.class);
            	String query = collectionDB.getCollection()+"\n";
            	 driver.get(url);
                 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='q']")));
                 driver.findElement(By.xpath("//input[@name='q']")).sendKeys(query);
                 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='timeline-item ']")));
                 List<WebElement> items = driver.findElements(By.xpath("//div[@class='timeline-item ']"));
         	        while (true) {
         	        	// Lặp qua từng bài đăng
         	        	for (WebElement item : items) {
         		            try {
         		            	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@class='timeline-item ']")));
         		            	WebElement avatarElement = item.findElement(By.xpath(".//img[@class='avatar round']"));
         		                WebElement nameElement = item.findElement(By.xpath(".//a[@class='fullname']"));
         		                WebElement usernameElement = item.findElement(By.xpath("//a[@class='username']"));
         		                WebElement timeElement = item.findElement(By.xpath(".//span[@class='tweet-date']/a"));
         		                WebElement replyElement = item.findElement(By.xpath(".//span[@class='icon-comment']"));
         		                WebElement reTweetElement = item.findElement(By.xpath(".//span[@class='icon-retweet']"));
         		                WebElement likeElement = item.findElement(By.xpath(".//span[@class='icon-heart']"));
         		                WebElement tweetElement = item.findElement(By.xpath(".//div[@class='tweet-content media-body']"));
         		                WebElement postLinkElement = item.findElement(By.xpath(".//span[@class='tweet-date']/a"));
         		             // Lấy thông tin từ các yếu tố
         		                String avatar = avatarElement.getAttribute("src");
         		                String name = nameElement.getText();
         		                String userName = usernameElement.getText();
         		                String timeStamp = timeElement.getAttribute("title");
         		                String reply = replyElement.getText();
         		                String reTweet = reTweetElement.getText();
         		                String like = likeElement.getText();
         		                String tweet = tweetElement.getText();
         		                String posturl = postLinkElement.getAttribute("href");
         		             
         		              // Không phải tweet nào cũng có ảnh, nên rất nhiều ngoại lệ
         			            try {
         			            	   WebElement imageElement = item.findElement(By.xpath(".//a[@class='still-image']"));
         			            	   String image = imageElement.getAttribute("href");
         			            	   images.add(image);
         			             } catch (Exception e) {
         			            	 	images.add("");
         			            	   System.out.println("Bài viết không có ảnh");
         			             }
         			         // Lấy hashtag thành chuỗi hashtags
         		                Pattern patternHastag = Pattern.compile("#\\w+");
         		                Matcher matcherHastag = patternHastag.matcher(tweet);
         		                StringBuilder stringBuilder1 = new StringBuilder();
         		                while (matcherHastag.find()) {
         		                    String hashtag = matcherHastag.group();
         		                    stringBuilder1.append(hashtag);
         		                }
         		                String hashtags = stringBuilder1.toString().trim();
         			            
         		             // Lấy Tag thành chuỗi Tags
         		                Pattern patternTag = Pattern.compile("@\\w+");
         		                Matcher matcherTag = patternTag.matcher(tweet);
         		                StringBuilder stringBuilder2 = new StringBuilder();
         		                while (matcherTag.find()) {
         		                    String tag = matcherTag.group();
         		                    stringBuilder2.append(tag);
         		                }
         		                String tags = stringBuilder2.toString().trim();
         		                tagss.add(tags);
         			            hashtagss.add(hashtags);
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
         		                System.out.println("Tweet: " + tweet);
         		                System.out.println("Like: " + like);
         		                
         		
         		            } catch (Exception e) {
         		                e.printStackTrace();
         		            }
         	        	}
         	        	try {
         	        		driver.findElement(By.xpath("//div[@class ='show-more']")).click();
         	        		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//div[@class='timeline-item ']")));
         	        		items = driver.findElements(By.xpath("//div[@class='timeline-item ']"));
         					
         				} catch (Exception e) {
         					System.out.println("Hết bài viết");
         					break;
         				}
         	        }
            	if(i == 10) break;
            }

        } catch (IOException e) {
            e.printStackTrace();
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
	            dataFrame.add(hashtagss);
	            dataFrame.add(tagss);
	            dataFrame.add(images);
	            
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
		        String fileName = "data/json/post/nitter/tweet_top10collection_timecrawl-" + timestamp + ".json";

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