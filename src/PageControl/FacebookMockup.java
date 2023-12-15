package PageControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class FacebookMockup {
    private static final JButton previousButton = null;
    private static final JButton currentButton = null;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);
    private final Dimension buttonDimension = new Dimension(200, 80);
    // Create custom menu bar using JPanel
    private static final JPanel menuBar = new JPanel();


    public static void main(String[] args) throws IOException {
        new FacebookMockup();
    }

    public FacebookMockup() throws IOException {
        // Create new JFrame
        JFrame mainFrame = new JFrame("Facebook Mockup");

        // Generate default size for windows
        mainFrame.setSize(1080, 720);

        // Set background color to dark gray
        mainFrame.getContentPane().setBackground(Color.BLACK);

        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));
        menuBar.setBackground(Color.BLACK);

        // Create buttons for the menu bar
        HomePage homePage = new HomePage("Home Page");
        homePage.generateButton();
        choosePage(homePage);

        SearchPage searchPage = new SearchPage("Search Page");
        searchPage.generateButton();
        choosePage(searchPage);

        ExplorePage explorePage = new ExplorePage("Explore Page");
        explorePage.generateButton();
        choosePage(explorePage);

        // Add buttons to the menu bar
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(homePage.getButton());
        menuBar.add(searchPage.getButton());
        menuBar.add(explorePage.getButton());
        menuBar.add(Box.createHorizontalGlue());

        // Add menu bar to the frame
        mainFrame.add(menuBar, BorderLayout.NORTH);

        // Create panels for each page
        JPanel homePagePanel = homePage.getMainPanel();
//        homePagePanel.add(new JLabel("This is the Home Page"));

        JPanel searchPagePanel = new JPanel();
        searchPagePanel.add(new JLabel("This is the Search Page"));

        JPanel explorePagePanel = new JPanel();
        explorePagePanel.add(new JLabel("This is the Explore Page"));

        // Add panels to main panel with card layout
        mainPanel.add(homePagePanel, "Home Page");
        mainPanel.add(searchPagePanel, "Search Page");
        mainPanel.add(explorePagePanel, "Explore Page");

        // Add main panel to the frame
        mainFrame.add(mainPanel, BorderLayout.CENTER);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);


    }

    private void choosePage(Page thisPage) {
        JButton button = thisPage.getButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, button.getName());
//                System.out.println(button.getName());
                resetButtonColors(menuBar);
                button.setForeground(Color.CYAN);
                try {
                    thisPage.displayPanel();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void resetButtonColors(JPanel menuBar) {
        for (Component component : menuBar.getComponents()) {
            if (component instanceof JButton) {
                component.setForeground(Color.WHITE);
            }
        }
    }
}