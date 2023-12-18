package model.post;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TwitterPost extends Post{

//	**
//	Attribute
//	**
    private String avatarLink;
    private String handle;
    private String time;
    private int likeCount;
    private int retweetCount;

    public TwitterPost(String hashTags, String content, String imageLink, String author) {
        super(hashTags, content, imageLink, author);
    }
}
