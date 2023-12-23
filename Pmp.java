import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Pmp extends JPanel {
    private DefaultListModel<String> passwordListModel;
    private JList<String> passwordList;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JLabel websiteLabel;
    private JTextField userTextField;
    private JTextField passwordTextField;
    private JTextField websiteTextField;

    public java() {
        // construct components
        addButton = new JButton("ADD");
        deleteButton = new JButton("DELETE");
        updateButton = new JButton("UPDATE");
        userLabel = new JLabel("   USER OR USERNAME");
        passwordLabel = new JLabel("    PASSWORD");
        websiteLabel = new JLabel("  WEBSITE");
        userTextField = new JTextField(5);
        passwordTextField = new JTextField(5);
        websiteTextField = new JTextField(5);
        passwordListModel = new DefaultListModel<>();
        passwordList = new JList<>(passwordListModel);

        // adjust size and set layout
        setPreferredSize(new Dimension(669, 600));
        setLayout(null);

        // add components
        add(addButton);
        add(deleteButton);
        add(updateButton);
        add(userLabel);
        add(passwordLabel);
        add(websiteLabel);
        add(userTextField);
        add(passwordTextField);
        add(websiteTextField);
        add(passwordList);
        websiteTextField.setBounds(145, 145, 415, 30);
passwordList.setBounds(25, 230, 615, 150);

// Add image to the GUI
ImageIcon imageIcon = new ImageIcon("image.png");
JLabel imageLabel = new JLabel(imageIcon);
add(imageLabel);
imageLabel.setBounds(30, 20, 600, 950);

        // set component bounds (only needed by Absolute Positioning)
        addButton.setBounds(145, 200, 100, 25);
        deleteButton.setBounds(310, 200, 100, 25);
        updateButton.setBounds(460, 200, 100, 25);
        userLabel.setBounds(25, 70, 105, 30);
        passwordLabel.setBounds(20, 110, 100, 25);
        websiteLabel.setBounds(30, 145, 100, 25);
        userTextField.setBounds(145, 70, 415, 30);
        passwordTextField.setBounds(145, 105, 415, 30);
        websiteTextField.setBounds(145, 145, 415, 30);
        passwordList.setBounds(145, 255, 410, 120);
        setBackground(new Color(135, 206, 235));
        addButton.setBackground(new Color(0, 128, 0)); // green color
        deleteButton.setBackground(new Color(255, 0, 0)); // red color
        updateButton.setBackground(new Color(255, 192, 203)); // sky blue color

        // add action listeners for buttons
        addButton.addActionListener(e -> addPassword());
        deleteButton.addActionListener(e -> deletePassword());
        updateButton.addActionListener(e -> updatePassword());passwordList.addListSelectionListener(e -> displayInputFields());}

 private void displayInputFields() {
    if (!passwordList.getValueIsAdjusting()) {
        int selectedIndex = passwordList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedValue = passwordListModel.getElementAt(selectedIndex).toString();
            // Assuming the format "USER: username | PASSWORD: password | WEBSITE: website"
            String[] parts = selectedValue.split(" \\| ");
           if (parts.length == 3) {                userTextField.setText(parts[0].substring(parts[0].indexOf(": ") + 2));
                passwordTextField.setText(parts[1].substring(parts[1].indexOf(": ") + 2));
                websiteTextField.setText(parts[2].substring(parts[2].indexOf(": ") + 2));
            }
 } else {
            userTextField.setText("Enter username");
            passwordTextField.setText("Enter password");
            websiteTextField.setText("Enter website");
        }
    }

    }

   private void addPassword() {
        try {
            String user = userTextField.getText();
            String password = passwordTextField.getText();
            String website = websiteTextField.getText();
            if (!user.isEmpty() && !password.isEmpty() && !website.isEmpty()) {
                passwordListModel.addElement("USER: " + user + " | " + "PASSWORD: " + password + " | " + "WEBSITE: " + website);
                userTextField.setText("");
                passwordTextField.setText("");
                websiteTextField.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception to the console or handle it as required
        }
    }

    private void deletePassword() {
        try {
            int selectedIndex = passwordList.getSelectedIndex();
            if (selectedIndex != -1) {
                passwordListModel.remove(selectedIndex);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception to the console or handle it as required
        }
    }

    private void updatePassword() {
        try {
            int selectedIndex = passwordList.getSelectedIndex();
            if (selectedIndex != -1) {
                String user = userTextField.getText();
                String password = passwordTextField.getText();
                String website = websiteTextField.getText();
                if (!user.isEmpty() && !password.isEmpty() && !website.isEmpty()) {
                    passwordListModel.set(selectedIndex, "USER: " + user + " | " + "PASSWORD: " + password + " | " + "WEBSITE: " + website);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception to the console or handle it as required
        }
    }
        

    public static void main(String[] args) {
        JFrame frame = new JFrame("Password Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new java());
        frame.pack();
        frame.setVisible(true);
    }
}
