import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class page extends JPanel {
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

    public page() {
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
        updateButton.addActionListener(e -> updatePassword());
    }

    private void addPassword() {
        String user = userTextField.getText();
        String password = passwordTextField.getText();
        String website = websiteTextField.getText();
        if (!user.isEmpty() && !password.isEmpty() && !website.isEmpty()) {
            passwordListModel.addElement("USER: " + user + " | "+"PASSWORD: " + password + " | " + "WEBSITE: " + website);
            userTextField.setText("");
            passwordTextField.setText("");
            websiteTextField.setText("");
        }
    }

    private void deletePassword() {
        int selectedIndex = passwordList.getSelectedIndex();
        if (selectedIndex != -1) {
            passwordListModel.remove(selectedIndex);
        }
    }

    private void updatePassword() {
        int selectedIndex = passwordList.getSelectedIndex();
        if (selectedIndex != -1) {
            String user = userTextField.getText();
            String password = passwordTextField.getText();
            String website = websiteTextField.getText();
            if (!user.isEmpty() && !password.isEmpty() && !website.isEmpty()) {
                passwordListModel.set(selectedIndex, user + " | " + password + " | " + website);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Password Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new page());
        frame.pack();
        frame.setVisible(true);
    }
}

