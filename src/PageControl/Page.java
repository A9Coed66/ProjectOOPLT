package PageControl;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public abstract class Page {
    private static JPanel mainPanel = new JPanel();
    private JButton button = new JButton();
    private Dimension buttonDimension = new Dimension(200, 80);

    public Page(String name) {
        this.button.setText(name);
        this.button.setName(name);
    }

    public JButton getButton(){
        return button;
    }

    public JPanel getMainPanel(){return mainPanel;}

    public void generateButton() {
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setPreferredSize(buttonDimension);
    }

    public void resetButtonColors(JPanel menuBar) {
        for (Component component : menuBar.getComponents()) {
            if (component instanceof JButton) {
                component.setForeground(Color.WHITE);
            }
        }
    }

    public abstract void displayPanel() throws IOException;
}
