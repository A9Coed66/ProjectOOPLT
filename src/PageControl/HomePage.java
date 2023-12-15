package PageControl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import com.opencsv.exceptions.CsvValidationException;

public class HomePage extends Page {
    JPanel mainPanel = this.getMainPanel();
    private ArrayList<Post> posts = new ArrayList<>();
    public HomePage(String name) {
        super(name);
//        mainPanel.add(new JLabel("This is the Home Page"));
    }

    public static void main(String[] args){
    }
    @Override
    public void displayPanel() throws IOException {
        // Xóa dữ liệu cũ khi gọi displayPanel()
        mainPanel.removeAll();
        posts.clear();

        // Thực hiện thu thập dữ liệu mới
        getData();
    }

    private void getData() throws IOException {

        String csvFile = "C:\\Users\\admin\\Desktop\\ProjectOOPLT\\data.csv";
//        BufferedImage avatar = ImageIO.read(new URL("https://i.imgur.com/h4Es1ZM.jpeg")); // Assume cột 1 là đường link của avatar
//        String name = "username\n";
//        LocalDateTime uploadTime = LocalDateTime.now(); // Assume cột 3 là thời gian upload
//        String content = "This is a post";
//        BufferedImage image = ImageIO.read(new URL("https://i.imgur.com/h4Es1ZM.jpeg\n")); // Assume cột 5 là đường link của hình ảnh
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine = reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                // Đọc dữ liệu từ file CSV và tạo Post
                BufferedImage avatar = ImageIO.read(new URL(nextLine[0])); // Assume cột 1 là đường link của avatar
                String name = nextLine[1];
                LocalDateTime uploadTime = LocalDateTime.now(); // Assume cột 3 là thời gian upload
                String content = nextLine[3];
                BufferedImage image = ImageIO.read(new URL(nextLine[4])); // Assume cột 5 là đường link của hình ảnh


                // Tạo và lưu trữ Post vào danh sách
                Post postPanel = new Post();
                postPanel.displayPost(avatar, name, uploadTime, content, image);
                posts.add(postPanel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
//        Post postPanel = new Post();
//        postPanel.displayPost(avatar, name, uploadTime, content, image);
//        posts.add(postPanel);
        // Hiển thị các Post trên mainPanel
        for (Post post : posts) {
            mainPanel.add(post);
        }

        // Yêu cầu mainPanel cập nhật lại giao diện
        mainPanel.revalidate();
        mainPanel.repaint();
    }

}
