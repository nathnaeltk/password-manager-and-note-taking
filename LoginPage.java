import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

public class LoginPage extends JPanel {
    private JTextArea jcomp1;
    private JTextArea jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JButton jcomp6;
    private JButton jcomp7;
    private JLabel imageLabel;
    private JPanel registrationPanel; // Panel to hold registration content

    public LoginPage() {
        // Construct components
        jcomp1 = new JTextArea(2, 30);
        jcomp2 = new JTextArea(2, 30);
        jcomp3 = new JLabel("Username: ");
        jcomp4 = new JLabel("Password: ");
        jcomp6 = new JButton("Login");
        jcomp7 = new JButton("Register");

        ImageIcon imageIcon = new ImageIcon("image.png");
        imageLabel = new JLabel(imageIcon);

        // Set layout manager to BorderLayout for organized placement
        setLayout(new BorderLayout());

        // Create panels to manage components' placement
        JPanel inputPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registrationPanel = new JPanel(new BorderLayout()); // Initialize the registration panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to inputPanel using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(jcomp3, gbc);

        gbc.gridy++;
        inputPanel.add(jcomp1, gbc);

        gbc.gridy++;
        inputPanel.add(jcomp4, gbc);

        gbc.gridy++;
        inputPanel.add(jcomp2, gbc);

        // Add components to buttonPanel using FlowLayout
        buttonPanel.add(jcomp6);
        buttonPanel.add(jcomp7);

        // Add components to main panel using BorderLayout
        add(imageLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // login function
        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = jcomp1.getText();
                String enteredPassword = jcomp2.getText();

                // Check credentials
                if (checkCredentials(enteredUsername, enteredPassword)) {
                    // If credentials match, close login page and open MainPage
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(LoginPage.this);
                    frame.dispose(); // Close login page
                    openMainPage();
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Invalid username or password!");
                }
            }
        });


        // Add ActionListener to the Register button
        jcomp7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the existing content and add the registration content
                removeAll();
                addRegistrationContent();
                revalidate();
                repaint();
            }
        });
    }

    private boolean checkCredentials(String username, String password) {
        // Read users from JSON file and check credentials
        try (FileReader fileReader = new FileReader("users.json")) {
            Gson gson = new Gson();
            Type userListType = new TypeToken<List<User>>(){}.getType();
            List<User> userList = gson.fromJson(fileReader, userListType);

            if (userList != null) {
                for (User user : userList) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        return true; // Match found
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false; // No match found
    }

    private void openMainPage() {
        // Open MainPage
        MainPage mainPage = new MainPage();
        JFrame mainFrame = new JFrame("Main Page");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add(mainPage);
        mainFrame.setSize(669, 400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    // Method to add registration content to the panel
    private void addRegistrationContent() {
        // Import the RegistrationPage content here
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPanel.add(registrationPage); // Add the RegistrationPage content to the registrationPanel
        add(registrationPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login - Astawash");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            LoginPage panel = new LoginPage();
            frame.getContentPane().add(panel);
            frame.setSize(600, 560);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}