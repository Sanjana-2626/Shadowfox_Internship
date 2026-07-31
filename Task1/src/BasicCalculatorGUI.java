import javax.swing.*;
import java.awt.*;

public class BasicCalculatorGUI extends JPanel {

    private JTextField display;

    public BasicCalculatorGUI() {
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(display, BorderLayout.NORTH);

        // Button grid
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        buttonPanel.setPreferredSize(new Dimension(250, 250));

        String[] btnLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "AC", "=", "+"
        };

        for (String text : btnLabels) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            buttonPanel.add(btn);

            btn.addActionListener(e -> {
                String cmd = e.getActionCommand();
                if (cmd.equals("AC")) {
                    display.setText("");
                } else if (cmd.equals("=")) {
                    try {
                        String exp = display.getText();
                        char operator = ' ';
                        if (exp.contains("+")) operator = '+';
                        else if (exp.contains("-")) operator = '-';
                        else if (exp.contains("*")) operator = '*';
                        else if (exp.contains("/")) operator = '/';
                        else if (exp.contains("%")) operator = '%';

                        String[] parts = exp.split("\\" + operator);
                        double num1 = Double.parseDouble(parts[0].trim());
                        double num2 = Double.parseDouble(parts[1].trim());

                        double result = Calculator.calculate(num1, num2, operator);
                        display.setText(String.valueOf(result));
                    } catch (Exception ex) {
                        display.setText("Error");
                    }
                } else {
                    display.setText(display.getText() + cmd);
                }
            });
        }

        // Wrap button panel to keep it centered
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.add(buttonPanel);

        add(wrapper, BorderLayout.SOUTH);
    }
}
