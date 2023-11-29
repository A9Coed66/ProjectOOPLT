package PageControl;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;

public class GridWindow {
    public static void main(String[] args) {
        // Tạo một đối tượng JFrame mới
        JFrame frame = new JFrame("Cửa sổ GridLayout");

        // Thiết lập kích thước mặc định cho cửa sổ
        frame.setSize(300, 300);

        // Sử dụng GridLayout
        frame.setLayout(new GridLayout(3, 3));

        // Thêm các nút vào frame
        for (int i = 1; i <= 9; i++) {
            frame.add(new JButton("Nút " + i));
        }

        // Thiết lập hành động mặc định khi đóng cửa sổ
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hiển thị cửa sổ
        frame.setVisible(true);
    }
}
