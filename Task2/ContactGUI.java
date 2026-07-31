import javax.swing.*;
import java.awt.*;

public class ContactGUI extends JFrame {

    private JTextField nameField, phoneField, emailField;
    private JButton save, delete, update, view, clear;

    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;

    private ContactFunction contactfunction = new ContactFunction();

    public ContactGUI() {

        setTitle("*** Contact Management System ***");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        topPanel = new JPanel();
        middlePanel = new JPanel();
        bottomPanel = new JPanel();

        topPanel.setPreferredSize(new Dimension(0, 200));
        middlePanel.setPreferredSize(new Dimension(0, 100));
        bottomPanel.setPreferredSize(new Dimension(0, 200));

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        createMainForm();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ---------------- Main Form ----------------

    private void createMainForm() {

        topPanel.removeAll();
        middlePanel.removeAll();

        topPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,10,15,10);

        nameField = new JTextField(15);
        phoneField = new JTextField(15);
        emailField = new JTextField(15);

        // Restrict phone field to digits only and max length 10
        phoneField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || phoneField.getText().length() >= 10) {
                    e.consume(); // block non-digits and extra characters
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(new JLabel("Name"), gbc);

        gbc.gridx = 1;
        topPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(new JLabel("Phone Number"), gbc);

        gbc.gridx = 1;
        topPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        topPanel.add(new JLabel("Email"), gbc);

        gbc.gridx = 1;
        topPanel.add(emailField, gbc);

        save = new JButton("Save");
        delete = new JButton("Delete");
        update = new JButton("Update");
        view = new JButton("View");
        clear = new JButton("Clear");

        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));

        middlePanel.add(save);
        middlePanel.add(delete);
        middlePanel.add(update);
        middlePanel.add(view);
        middlePanel.add(clear);

        // SAVE
        save.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if(name.isEmpty() || phone.isEmpty() || email.isEmpty()){
                JOptionPane.showMessageDialog(this,"Fill all fields.");
                return;
            }

            if(phone.length() != 10){
                JOptionPane.showMessageDialog(this,"Phone number must be exactly 10 digits.");
                return;
            }

            contactfunction.add(name,phone,email);
            JOptionPane.showMessageDialog(this,"Contact Added.");
            clearFields();
        });

        // DELETE
        delete.addActionListener(e -> {
            String phone = phoneField.getText().trim();

            if(phone.isEmpty()){
                JOptionPane.showMessageDialog(this,"Enter Phone Number.");
                return;
            }

            contactfunction.delete(phone);
            JOptionPane.showMessageDialog(this,"Delete Operation Completed.");
            clearFields();
        });

        // VIEW
        view.addActionListener(e -> showContacts());

        // CLEAR
        clear.addActionListener(e -> clearFields());

        // UPDATE
        update.addActionListener(e -> showUpdateForm());

        topPanel.revalidate();
        topPanel.repaint();
        middlePanel.revalidate();
        middlePanel.repaint();
    }

    // ---------------- Update Form ----------------

    private void showUpdateForm(){

        topPanel.removeAll();
        topPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField oldPhone = new JTextField(15);
        JTextField newName = new JTextField(15);
        JTextField newPhone = new JTextField(15);
        JTextField newEmail = new JTextField(15);

        // Restrict newPhone field to digits only and max length 10
        newPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || newPhone.getText().length() >= 10) {
                    e.consume();
                }
            }
        });

        gbc.gridx=0; gbc.gridy=0;
        topPanel.add(new JLabel("Previous Phone"),gbc);
        gbc.gridx=1; topPanel.add(oldPhone,gbc);

        gbc.gridx=0; gbc.gridy=1;
        topPanel.add(new JLabel("New Name"),gbc);
        gbc.gridx=1; topPanel.add(newName,gbc);

        gbc.gridx=0; gbc.gridy=2;
        topPanel.add(new JLabel("New Phone"),gbc);
        gbc.gridx=1; topPanel.add(newPhone,gbc);

        gbc.gridx=0; gbc.gridy=3;
        topPanel.add(new JLabel("New Email"),gbc);
        gbc.gridx=1; topPanel.add(newEmail,gbc);

        JButton updateBtn = new JButton("Update Contact");
        JButton cancelBtn = new JButton("Cancel");

        gbc.gridx=0; gbc.gridy=4;
        topPanel.add(updateBtn,gbc);
        gbc.gridx=1; topPanel.add(cancelBtn,gbc);

        updateBtn.addActionListener(e->{
            String oldPh = oldPhone.getText().trim();
            String newPh = newPhone.getText().trim();

            if(newPh.length() != 10){
                JOptionPane.showMessageDialog(this,"New phone number must be exactly 10 digits.");
                return;
            }

            contactfunction.update(
                    oldPh,
                    newName.getText().trim(),
                    newPh,
                    newEmail.getText().trim()
            );

            JOptionPane.showMessageDialog(this,"Contact Updated.");
            createMainForm();
        });

        cancelBtn.addActionListener(e->createMainForm());

        topPanel.revalidate();
        topPanel.repaint();
    }

    // ---------------- View Contacts ----------------

    private void showContacts(){
        bottomPanel.removeAll();
        bottomPanel.setLayout(new BorderLayout());

        if(contactfunction.getContacts().isEmpty()){
            bottomPanel.add(new JLabel("No Contacts Available",
                    SwingConstants.CENTER),BorderLayout.CENTER);
        }else{
            String[] columns={"S.No","Name","Phone","Email"};
            String[][] data=new String[contactfunction.getContacts().size()][4];

            int i=0;
            for(ContactFunction c:contactfunction.getContacts()){
                data[i][0]=String.valueOf(i+1);
                data[i][1]=c.getName();
                data[i][2]=c.getPhone();
                data[i][3]=c.getEmail();
                i++;
            }

            JTable table=new JTable(data,columns);
            JScrollPane scrollPane=new JScrollPane(table);
            bottomPanel.add(scrollPane,BorderLayout.CENTER);
        }

        bottomPanel.revalidate();
        bottomPanel.repaint();
    }

    // ---------------- Clear ----------------

    private void clearFields(){
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ContactGUI());
    }
}
