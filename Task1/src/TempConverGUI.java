import javax.swing.*;
import java.awt.*;

public class TempConverGUI extends JPanel {

    private JTextField inputField;
    private JComboBox<String> fromBox, toBox;
    private JLabel resultLabel;

    public TempConverGUI() {
        setLayout(new GridLayout(5, 1, 10, 10));

        // Input field
        inputField = new JTextField();
        add(new JLabel("Enter Temperature:"));
        add(inputField);

        // Dropdowns
        String[] units = {"C", "F", "K"};
        fromBox = new JComboBox<>(units);
        toBox = new JComboBox<>(units);

        JPanel unitPanel = new JPanel(new FlowLayout());
        unitPanel.add(new JLabel("From:"));
        unitPanel.add(fromBox);
        unitPanel.add(new JLabel("To:"));
        unitPanel.add(toBox);
        add(unitPanel);

        // Convert button
        JButton convertBtn = new JButton("Convert");
        convertBtn.addActionListener(e -> convertTemperature());
        add(convertBtn);

        // Result label
        resultLabel = new JLabel("Result: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(resultLabel);
    }

    private void convertTemperature() {
        try {
            double temp = Double.parseDouble(inputField.getText());
            char from = fromBox.getSelectedItem().toString().charAt(0);
            char to = toBox.getSelectedItem().toString().charAt(0);

            double result = TempConver.convert(temp, from, to);
            resultLabel.setText("Result: " + result + " " + to);
        } catch (Exception ex) {
            resultLabel.setText("Error: Invalid input");
        }
    }
}
