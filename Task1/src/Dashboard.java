import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    JPanel leftPanel, rightPanel;
    JButton basicButton, scientificButton, currencyButton, temperatureButton;
    CardLayout cardLayout;

    public Dashboard() {

        // Window Settings
        setTitle("Multi Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ================= LEFT PANEL =================
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(250, 600));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel heading = new JLabel("Calculator");
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        basicButton = new JButton("Basic Calculator");
        scientificButton = new JButton("Scientific Calculator");
        temperatureButton = new JButton("Temp Converter");

        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        JButton[] buttons = { basicButton, scientificButton, temperatureButton };
        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.setMaximumSize(new Dimension(220, 45));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(heading);
        leftPanel.add(Box.createVerticalStrut(40));
        leftPanel.add(basicButton);
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(scientificButton);
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(temperatureButton);

        // ================= RIGHT PANEL =================
        cardLayout = new CardLayout();
        rightPanel = new JPanel(cardLayout);

        // Welcome Panel
        JPanel welcomePanel = new JPanel();
        JLabel welcome = new JLabel("Welcome to Multi Calculator");
        welcome.setFont(new Font("Arial", Font.BOLD, 28));
        welcomePanel.add(welcome);

        // Panels
        JPanel basicPanel = new BasicCalculatorGUI();       // external file
        JPanel tempPanel = new TempConverGUI();             // external file
        JPanel scientificPanel = new ScientificCalculator();// external file

        // Placeholder Currency Panel (replace later with API-based GUI)

        // Add panels to card layout
        rightPanel.add(welcomePanel, "Welcome");
        rightPanel.add(basicPanel, "Basic");
        rightPanel.add(scientificPanel, "Scientific");
        rightPanel.add(tempPanel, "Temp");

        // ================= ADD TO FRAME =================
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // ================= ACTION LISTENERS =================
        basicButton.addActionListener(e -> cardLayout.show(rightPanel, "Basic"));
        scientificButton.addActionListener(e -> cardLayout.show(rightPanel, "Scientific"));
        temperatureButton.addActionListener(e -> cardLayout.show(rightPanel, "Temp"));

        pack(); // respect preferred sizes
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}