package data.connector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.post.BlogPost;
import model.post.Post;

public class BlogPostDB implements IPostDB{
	private ObservableList<Post> posts = FXCollections.observableArrayList();
	public ObservableList<Post> init(String filePath){
	
		 String line = "";
	     String cvsSplitBy = ",";

	     ObservableList<Post> blogPosts= FXCollections.observableArrayList();
		 try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            // Skipping the header line
	            br.readLine();
	            
	            while ((line = br.readLine()) != null) {
	                // Use comma as separator
	                String[] data = line.split(cvsSplitBy);

	                // Assigning each field to a variable
	                String avatarImage = data[0].replaceAll("^\"|\"$", "");
	                String author = data[1].replaceAll("^\"|\"$", "");
	                String title = data[2].replaceAll("^\"|\"$", "");
	                String link = data[3].replaceAll("^\"|\"$", "");
	                String image = data[4].replaceAll("^\"|\"$", "");
	                String date = data[5].replaceAll("^\"|\"$", "");
	                String tags = data[6].replaceAll("^\"|\"$", "");
	                String contents = data[7].replaceAll("^\"|\"$", "");
	                String price = data[8].replaceAll("^\"|\"$", "");
	                String vote = data[9].replaceAll("^\"|\"$", "");
	                String comment = data[10].replaceAll("^\"|\"$", "");
	                System.out.println(price);
	                String stringPrice = price.replace("$", "");
	                System.out.println(stringPrice);
	                // Chuyển đổi chuỗi thành số tiền kiểu float
	                float floatPrice = Float.parseFloat(stringPrice);
	                System.out.println(floatPrice);
	                // Processing or printing out the variables
	                blogPosts.add(new BlogPost(contents, image, author, date, link, comment, vote, avatarImage, title, floatPrice, tags));
	                // ... and so on for other variables
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
		 
		 this.posts =blogPosts;
		return blogPosts;
		
		
	 }
	public ObservableList<Post> getPosts( ){
		return null;
	};
}
