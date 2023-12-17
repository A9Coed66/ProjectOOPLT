package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PostController {

    @FXML
    private Label contentLabel;

    @FXML
    private Label headingLabel;

    @FXML
    private Label likeLabel;

    @FXML
    private Label shareLabel;


    public void setData(String heading, String content,String like,String share){
        headingLabel.setText(heading);
        contentLabel.setText(content) ;
        likeLabel.setText(like);
        shareLabel.setText(share);
    }
}
