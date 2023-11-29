package PageControl;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Duration;

public class Post extends JPanel {
    private JLabel avatarLabel;
    private JLabel nameLabel;
    private JLabel timeLabel;
    private JTextArea contentArea;
    private JLabel imageLabel;

    public Post() {
        setLayout(new GridBagLayout());
        setBorder(new LineBorder(Color.GRAY, 15, true));
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(600, 600));

        avatarLabel = new JLabel();
        nameLabel = new JLabel();
        timeLabel = new JLabel();
        contentArea = new JTextArea();
        imageLabel = new JLabel();

        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.anchor = GridBagConstraints.NORTH;
        add(avatarLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.WEST;
        add(nameLabel, constraints);

        constraints.gridy = 1;
        add(timeLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        add(contentArea, constraints);

        constraints.gridy = 3;
        add(imageLabel, constraints);
        contentArea.setPreferredSize(new Dimension(500, 20));
        contentArea.setMinimumSize(new Dimension(500, 20));
    }

    public void displayPost(BufferedImage avatar, String name, LocalDateTime now, String content, BufferedImage image) {
        avatarLabel.setIcon(new ImageIcon(avatar.getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        nameLabel.setText(name);
        timeLabel.setText(getTimeElapsed(now));
        contentArea.setText(content);
        if (image != null) {
            float imageHeight = image.getHeight();
            float imageWidth = image.getWidth();
            float scale = imageWidth/500f;

            imageLabel.setIcon(new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        }
    }

    private String getTimeElapsed(LocalDateTime now) {
        LocalDateTime current = LocalDateTime.now();
        Duration duration = Duration.between(now, current);
        long seconds = duration.getSeconds();
        if (seconds < 60) {
            return seconds + " seconds ago";
        } else if (seconds < 3600) {
            return seconds / 60 + " minutes ago";
        } else if (seconds < 86400) {
            return seconds / 3600 + " hours ago";
        } else {
            return seconds / 86400 + " days ago";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedImage avatar = ImageIO.read(new URL("https://i.imgur.com/h4Es1ZM.jpeg"));
        String name = "username";
        LocalDateTime now = LocalDateTime.now();
        String content = "This is a post";
        BufferedImage image = ImageIO.read(new URL("https://i.imgur.com/h4Es1ZM.jpeg"));

        Post postPanel = new Post();
        postPanel.displayPost(avatar, name, now, content, image);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(postPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
