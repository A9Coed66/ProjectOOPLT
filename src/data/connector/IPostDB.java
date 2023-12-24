package data.connector;

import javafx.collections.ObservableList;
import model.post.Post;

public interface IPostDB {
	public ObservableList<Post> getPosts(String filePath);
}
