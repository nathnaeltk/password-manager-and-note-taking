import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordManagerGUI {

    private JFrame mainFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;

    public PasswordManagerGUI() {
        setUpGUI();
        // Assume database is initialized and filled with website credentials
        database = new HashtablePassword();
    }
    private void autofillCredentials(String website) {
        if (database.containsKey(website)) {
            PasswordEntry credentials = database.get(website);
            // Assuming PasswordEntry contains username and password fields
            String username = credentials.getUsername();
            String password = credentials.getPassword();
            
            // Assuming JTextField components for username and password already exist
            websiteField.setText(website);
            usernameField.setText(username);
            passwordField.setText(password);
        }
    }

    private void setUpGUI() {
        mainFrame = new JFrame("Password Manager");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 250);
        mainFrame.setLayout(new BorderLayout(10, 10));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username:", SwingConstants.RIGHT);
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(150, 30));
        usernameField.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel passwordLabel = new JLabel("Password:", SwingConstants.RIGHT);
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(150, 30));
        passwordField.setFont(new Font("Arial", Font.BOLD, 14));

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayManagementGUI();
            }
        });
        websiteField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String website = websiteField.getText();
                autofillCredentials(website);
            }
        });

        createAccountButton = new JButton("Create Account");

        fieldsPanel.add(usernameLabel);
        fieldsPanel.add(usernameField);
        fieldsPanel.add(passwordLabel);
        fieldsPanel.add(passwordField);

        buttonsPanel.add(loginButton);
        buttonsPanel.add(createAccountButton);

        mainFrame.add(fieldsPanel, BorderLayout.CENTER);
        mainFrame.add(buttonsPanel, BorderLayout.SOUTH);

        mainFrame.setVisible(true);
    }

    private void displayManagementGUI() {
        JFrame managementFrame = new JFrame("Manage Passwords");
        managementFrame.setSize(500, 400);
        managementFrame.setLayout(new BorderLayout(10, 10));
        managementFrame.setLocationRelativeTo(null);
        managementFrame.setResizable(false);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel websiteLabel = new JLabel("Website:", SwingConstants.RIGHT);
        JTextField websiteField = new JTextField();
        JLabel usernameLabel = new JLabel("Username:", SwingConstants.RIGHT);
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:", SwingConstants.RIGHT);
        JTextField passwordField = new JTextField();

        inputPanel.add(websiteLabel);
        inputPanel.add(websiteField);
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton saveButton = new JButton("Save");
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");

        buttonsPanel.add(saveButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(updateButton);

        managementFrame.add(inputPanel, BorderLayout.CENTER);
        managementFrame.add(buttonsPanel, BorderLayout.SOUTH);

        managementFrame.setVisible(true);
    }
        // Example usage within an event listener when a website is entered or selected
    websiteField.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String website = websiteField.getText();
            autofillCredentials(website);
        }
    });
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PasswordManagerGUI();
            }
        });
    }


    

