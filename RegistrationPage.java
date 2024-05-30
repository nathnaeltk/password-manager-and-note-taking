import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegistrationPage extends JPanel {
    private JTextField jcompname;
    private JTextArea jcomp1;
    private JPasswordField jcomp2;
    private JLabel jlabelname;
    private JButton jcomp6;
    private JButton jcomp7;
    private DatabaseConnection dbConnection;

    public RegistrationPage(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
        jcomp1 = new JTextArea(2, 30);
        jcomp2 = new JPasswordField(30);
        Dimension preferredSize = jcomp2.getPreferredSize();
        preferredSize.height *= 2;
        jcomp2.setPreferredSize(preferredSize);
        jcompname = new JTextField(30);
        jlabelname = new JLabel("Name:");
        JLabel jcomp3 = new JLabel("Username:");
        JLabel jcomp4 = new JLabel("Password:");
        jcomp6 = new JButton("Login");
        jcomp6.setBackground(new Color(0, 128, 0));
        jcomp7 = new JButton("Register");
        jcomp7.setBackground(new Color(0, 128, 0));

        Color skyBlue = new Color(135, 206, 235);
        jcomp1.setBackground(skyBlue);
        jcomp2.setBackground(skyBlue);
        jcompname.setBackground(skyBlue);

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(jlabelname, gbc);
        gbc.gridy++;
        inputPanel.add(jcompname, gbc);
        gbc.gridy++;
        inputPanel.add(jcomp3, gbc);
        gbc.gridy++;
        inputPanel.add(jcomp1, gbc);
        gbc.gridy++;
        inputPanel.add(jcomp4, gbc);
        gbc.gridy++;
        inputPanel.add(jcomp2, gbc);

        buttonPanel.add(jcomp6);
        buttonPanel.add(jcomp7);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        jcomp7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginPage();
            }
        });
    }

    private void registerUser() {
        String name = jcompname.getText().trim();
        String username = jcomp1.getText().trim();
        String password = new String(jcomp2.getPassword());

        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
        } else {
            if (userExists(username)) {
                JOptionPane.showMessageDialog(this, "Account with this username already exists!");
            } else {
                if (saveUserToDatabase(name, username, password)) {
                    JOptionPane.showMessageDialog(this, "Registration Successful! You may now login.");
                } else {
                    JOptionPane.showMessageDialog(this, "Registration failed. Please try again later.");
                }
            }
        }
    }

    private boolean userExists(String username) {
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean exists = resultSet.next();
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean saveUserToDatabase(String name, String username, String password) {
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, username, password) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void openLoginPage() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
        LoginPage loginPage = new LoginPage(dbConnection);
        JFrame loginFrame = new JFrame("Login - Astawash");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.getContentPane().add(loginPage);
        loginFrame.setSize(600, 600);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Register - Astawash");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            DatabaseConnection dbConnection = new DatabaseConnection(); // Initialize your DatabaseConnection object here
            RegistrationPage panel = new RegistrationPage(dbConnection);
            frame.getContentPane().add(panel);
            frame.setSize(600, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
