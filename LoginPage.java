import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends JPanel {
    private JTextArea jcomp1;
    private JTextArea jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JButton jcomp6;
    private JButton jcomp7;
    private JLabel imageLabel;
    private JPanel registrationPanel; // Panel to hold registration content
    private DatabaseConnection dbConnection; // Database connection

    public LoginPage(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;

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

        // Set background colors
        Color skyBlue = new Color(190, 203, 226); // RGB color for sky blue
        Color green = new Color(220, 140, 34); // RGB color for green

        setBackground(skyBlue); // Set the background color of the main panel to sky blue
        inputPanel.setBackground(skyBlue); // Set the background color of the input panel to sky blue
        buttonPanel.setBackground(skyBlue); // Set the background color of the button panel to sky blue

        jcomp6.setBackground(green); // Set the login button color to green
        jcomp7.setBackground(green); // Set the register button color to green

        // Action listener for the login button
        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String enteredUsername = jcomp1.getText().trim();
                    String enteredPassword = jcomp2.getText().trim();

                    if ((enteredUsername.isEmpty() || enteredPassword.isEmpty()) || !(enteredUsername.matches("[a-zA-Z]+")) || !(enteredPassword.matches("[a-zA-Z0-9!@#$%^&*()_+-]+"))) {
                        throw new IllegalArgumentException("Invalid username or password.");
                    }

                    if (checkCredentials(enteredUsername, enteredPassword)) {
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(LoginPage.this);
                        frame.dispose();
                        openMainPage();
                    } else {
                        JOptionPane.showMessageDialog(LoginPage.this, "Invalid username or password!");
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(LoginPage.this, ex.getMessage());
                }
            }
        });

        // Action listener for the register button
        jcomp7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                addRegistrationContent();
                revalidate();
                repaint();
            }
        });
    }

    // Method to check user credentials in the database
    public boolean checkCredentials(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Method to open the main page after successful login
    public void openMainPage() {
        MainPage mainPage = new MainPage();
        JFrame mainFrame = new JFrame("Main Page");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add(mainPage);
        mainFrame.setSize(669, 400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    // Method to add registration content to the panel
    public void addRegistrationContent() {
        RegistrationPage registrationPage = new RegistrationPage(dbConnection);
        registrationPanel.add(registrationPage);
        add(registrationPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login - Astawash");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            DatabaseConnection dbConnection = new DatabaseConnection(); // Initialize your DatabaseConnection object here
            LoginPage panel = new LoginPage(dbConnection);
            frame.getContentPane().add(panel);
            frame.setSize(600, 560);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
