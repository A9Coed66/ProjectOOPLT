package data.connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonFileExample {
    public static void main(String[] args) {
        // Đường dẫn đến tệp JSON của bạn
        String filePath = "D:\\a_learning_code\\java\\ProjectOOPLT\\data\\json\\datatwitter.json";

        // Sử dụng ObjectMapper để chuyển đổi JSON từ tệp thành List<TwitterData>
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<TwitterDB> twitterDataList = Arrays.asList(objectMapper.readValue(new File(filePath), TwitterDB[].class));

            // Bây giờ bạn có thể làm việc với List<TwitterData>
            for (TwitterDB twitterData : twitterDataList) {
                System.out.println("UserName: " + twitterData.getUserName());
                System.out.println("Tweet: " + twitterData.getTweet());
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
