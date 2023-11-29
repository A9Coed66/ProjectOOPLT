package PageControl;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        // Tạo một JFrame (cửa sổ)
        JFrame frame = new JFrame("Interactive Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);

        // Tạo một JPanel để chứa các thành phần
        JPanel panel = new JPanel();

        // Tạo một nhãn
        JLabel label = new JLabel("Enter your name:");

        // Tạo một ô văn bản
        JTextField textField = new JTextField(15);

        // Tạo một nút
        JButton button = new JButton("Say Hello");

        // Xử lý sự kiện khi nút được nhấn
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                label.setText("Hello, " + name + "!");
            }
        });

        // Thêm các thành phần vào panel
        panel.add(label);
        panel.add(textField);
        panel.add(button);

        // Thêm panel vào frame
        frame.getContentPane().add(panel);

        // Hiển thị cửa sổ
        frame.setVisible(true);
    }
}