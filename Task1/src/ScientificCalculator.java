import javax.swing.*;
import java.awt.*;

public class ScientificCalculator extends JPanel {

    JTextField number1Field;
    JTextField number2Field;
    JTextField resultField;

    JButton sinBtn, cosBtn, tanBtn, logBtn;
    JButton lnBtn, sqrtBtn, squareBtn, powerBtn;
    JButton factBtn, reciprocalBtn, absBtn, clearBtn;
    ScientificLogic logic = new ScientificLogic();

    public ScientificCalculator() {

        setLayout(new BorderLayout(15,15));

        //================ TOP PANEL =================

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(7,1,5,5));

        JLabel title = new JLabel("Scientific Calculator");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel num1 = new JLabel("Number 1 (x)");
        number1Field = new JTextField();

        JLabel num2 = new JLabel("Number 2 (y)");
        number2Field = new JTextField();

        JLabel result = new JLabel("Result");
        resultField = new JTextField();
        resultField.setEditable(false);

        Font fieldFont = new Font("Arial", Font.PLAIN, 18);

        number1Field.setFont(fieldFont);
        number2Field.setFont(fieldFont);
        resultField.setFont(fieldFont);

        topPanel.add(title);
        topPanel.add(num1);
        topPanel.add(number1Field);
        topPanel.add(num2);
        topPanel.add(number2Field);
        topPanel.add(result);
        topPanel.add(resultField);

        add(topPanel, BorderLayout.NORTH);

        //================ BUTTON PANEL =================

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(3,4,8,8));

        sinBtn = new JButton("sin");
        cosBtn = new JButton("cos");
        tanBtn = new JButton("tan");
        logBtn = new JButton("log");

        lnBtn = new JButton("ln");
        sqrtBtn = new JButton("sqrt");
        squareBtn = new JButton("x^2");
        powerBtn = new JButton("x^y");

        factBtn = new JButton("n!");
        reciprocalBtn = new JButton("1/x");
        absBtn = new JButton("abs");
        clearBtn = new JButton("Clear");

        JButton buttons[] = {

                sinBtn, cosBtn, tanBtn, logBtn,
                lnBtn, sqrtBtn, squareBtn, powerBtn,
                factBtn, reciprocalBtn, absBtn, clearBtn

        };

        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        for(JButton b : buttons){

            b.setFont(buttonFont);
            b.setPreferredSize(new Dimension(80,40));
            b.setFocusPainted(false);

            buttonPanel.add(b);

        }

        add(buttonPanel, BorderLayout.CENTER);
        //================ EVENT LISTENERS =================
        sinBtn.addActionListener(e -> {
            try{
    double x = Double.parseDouble(number1Field.getText());
    resultField.setText(String.valueOf(logic.sin(x)));
            }
            catch(Exception ex){
                showError(ex);
            }
        });
  cosBtn.addActionListener(e -> {
    try{
    double x = Double.parseDouble(number1Field.getText());
    resultField.setText(String.valueOf(logic.cos(x)));
}
    catch(Exception ex){
        showError(ex);
    }
});
tanBtn.addActionListener(e -> {
    try{
    double x = Double.parseDouble(number1Field.getText());
    resultField.setText(String.valueOf(logic.tan(x)));
}
    catch(Exception ex){
        showError(ex);
    }
});
logBtn.addActionListener(e -> {
    try{
    double x = Double.parseDouble(number1Field.getText());
    resultField.setText(String.valueOf(logic.log(x)));
}
    catch(Exception ex){
        showError(ex);
    }
});
lnBtn.addActionListener(e -> {
    try{
    double x = Double.parseDouble(number1Field.getText());
    resultField.setText(String.valueOf(logic.ln(x)));
}
    catch(Exception ex){
        showError(ex);
    }
});
sqrtBtn.addActionListener(e -> {
    try{
    double x = Double.parseDouble(number1Field.getText());
    resultField.setText(String.valueOf(logic.sqrt(x)));
}
    catch(Exception ex){
        showError(ex);
    }
});
squareBtn.addActionListener(e -> {
    try{
    double x = Double.parseDouble(number1Field.getText());
    resultField.setText(String.valueOf(logic.square(x)));
}
    catch(Exception ex){
        showError(ex);
    }
});
powerBtn.addActionListener(e -> {
    try{
    double x = Double.parseDouble(number1Field.getText());
    double y = Double.parseDouble(number2Field.getText());
    resultField.setText(String.valueOf(logic.power(x, y)));
}
    catch(Exception ex){
        showError(ex);
    }
});
factBtn.addActionListener(e -> {
    try{
    int x = Integer.parseInt(number1Field.getText());
    resultField.setText(String.valueOf(logic.factorial(x)));
}
    catch(Exception ex){
        showError(ex);
    }
});
reciprocalBtn.addActionListener(e -> {
    try{
    double x = Double.parseDouble(number1Field.getText());
    resultField.setText(String.valueOf(logic.reciprocal(x)));
}
    catch(Exception ex){
        showError(ex);
    }
});
absBtn.addActionListener(e -> {
    try{
    double x = Double.parseDouble(number1Field.getText());
    resultField.setText(String.valueOf(logic.absolute(x)));
}
    catch(Exception ex){
        showError(ex);
    }
});
clearBtn.addActionListener(e -> {
    number1Field.setText("");
    number2Field.setText("");
    resultField.setText("");

});
    }
    private void showError(Exception ex) {
    JOptionPane.showMessageDialog(
        this,
        ex.getMessage(),
        "Error",
        JOptionPane.ERROR_MESSAGE
    );
}

}