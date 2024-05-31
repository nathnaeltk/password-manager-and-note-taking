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
    private JPanel registrationPanel;
    private DatabaseConnection dbConnection;

    public LoginPage(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;

        jcomp1 = new JTextArea(2, 30);
        jcomp2 = new JTextArea(2, 30);
        jcomp3 = new JLabel("Username: ");
        jcomp4 = new JLabel("Password: ");
        jcomp6 = new JButton("Login");
        jcomp7 = new JButton("Register");

        ImageIcon imageIcon = new ImageIcon("image.png");
        imageLabel = new JLabel(imageIcon);

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registrationPanel = new JPanel(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(jcomp3, gbc);

        gbc.gridy++;
        inputPanel.add(jcomp1, gbc);

        gbc.gridy++;
        inputPanel.add(jcomp4, gbc);

        gbc.gridy++;
        inputPanel.add(jcomp2, gbc);

        buttonPanel.add(jcomp6);
        buttonPanel.add(jcomp7);

        add(imageLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        Color skyBlue = new Color(190, 203, 226);
        Color green = new Color(220, 140, 34);

        setBackground(skyBlue);
        inputPanel.setBackground(skyBlue);
        buttonPanel.setBackground(skyBlue);

        jcomp6.setBackground(green);
        jcomp7.setBackground(green);

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

    public void openMainPage() {
        MainPage mainPage = new MainPage();
        JFrame mainFrame = new JFrame("Main Page");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add(mainPage);
        mainFrame.setSize(669, 400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public void addRegistrationContent() {
        RegistrationPage registrationPage = new RegistrationPage(dbConnection);
        registrationPanel.add(registrationPage);
        add(registrationPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login - Astawash");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            DatabaseConnection dbConnection = new DatabaseConnection();
            LoginPage panel = new LoginPage(dbConnection);
            frame.getContentPane().add(panel);
            frame.setSize(600, 560);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
