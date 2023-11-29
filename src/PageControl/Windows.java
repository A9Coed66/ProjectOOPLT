package PageControl;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Windows {
    public static void main(String[] args) {
        // Tạo một đối tượng JFrame mới
        JFrame frame = new JFrame("Cửa sổ đơn giản");

        // Thiết lập kích thước mặc định cho cửa sổ
        frame.setSize(350, 200);

        // Tạo một đối tượng JLabel mới
        JLabel label = new JLabel("Xin chào, đây là một cửa sổ đơn giản!", JLabel.CENTER);

        // Thêm label vào frame
        frame.getContentPane().add(label);

        // Thiết lập hành động mặc định khi đóng cửa sổ
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hiển thị cửa sổ
        frame.setVisible(true);
    }
}
