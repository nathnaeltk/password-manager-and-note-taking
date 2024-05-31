import java.awt.*;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.sql.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PasswordsManager extends JPanel {
    private JList<String> jcomp1;
    private JTextField jcomp2d;
    private JButton jcomp11d;
    private JTextField jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JTextField jcomp5;
    private JLabel jcomp6;
    private JPasswordField jcomp7;
    private JLabel jcomp10;
    private JButton jcomp11b;
    private JButton jcomp11;
    private JButton jcomp12;
    private JButton jcomp13;
    private JLabel imageLabel;

    public PasswordsManager() {
        Set<String> websiteNames = loadWebsiteNamesFromDatabase();
        String[] jcomp1Items = websiteNames.toArray(new String[0]);

        jcomp1 = new JList<>(jcomp1Items);
        jcomp1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    displayPasswordDetails();
                }
            }
        });
        jcomp2d = new JTextField(5);
        jcomp11d = new JButton("Search");
        jcomp11d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchWebsite();
            }
        });
        jcomp2 = new JTextField(5);
        jcomp3 = new JLabel("Website Name");
        jcomp4 = new JLabel("Email or Username");
        jcomp5 = new JTextField(5);
        jcomp6 = new JLabel("Password");
        jcomp7 = new JPasswordField(5);
        jcomp10 = new JLabel("Save a New Password");
        jcomp11b = new JButton("Generate Password");
        jcomp11b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });
        jcomp11 = new JButton("Save");
        jcomp11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        jcomp12 = new JButton("Logout");
        jcomp13 = new JButton("Notes");

        setPreferredSize(new Dimension(669, 368));
        setLayout(null);

        add(jcomp1);
        add(jcomp2d);
        add(jcomp11d);
        add(jcomp2);
        add(jcomp3);
        add(jcomp4);
        add(jcomp5);
        add(jcomp6);
        add(jcomp7);
        add(jcomp10);
        add(jcomp11b);
        add(jcomp11);
        add(jcomp12);
        add(jcomp13);

        jcomp1.setBounds(45, 140, 160, 175);
        jcomp2d.setBounds(220, 100, 265, 35);
        jcomp11d.setBounds(495, 105, 100, 25);
        jcomp2.setBounds(330, 160, 265, 35);
        jcomp3.setBounds(235, 165, 100, 25);
        jcomp4.setBounds(215, 205, 120, 25);
        jcomp5.setBounds(330, 200, 265, 35);
        jcomp6.setBounds(265, 245, 100, 25);
        jcomp7.setBounds(330, 240, 265, 40);
        jcomp10.setBounds(405, 130, 150, 25);
        jcomp11b.setBounds(330, 285, 150, 25);
        jcomp11.setBounds(495, 285, 100, 25);
        jcomp12.setBounds(495, 40, 100, 25);
        jcomp13.setBounds(390, 40, 100, 25);
        setBackground(new Color(190, 203, 226));

        jcomp12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginPage();
            }
        });

        jcomp13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMainPage();
            }
        });

        Color buttonColor = new Color(220, 140, 34);
        jcomp11d.setBackground(buttonColor);
        jcomp11b.setBackground(buttonColor);
        jcomp11.setBackground(buttonColor);
        jcomp12.setBackground(buttonColor);
        jcomp13.setBackground(buttonColor);

        ImageIcon imageIcon = new ImageIcon("image.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(253, 133, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        imageLabel = new JLabel(scaledImageIcon);
        add(imageLabel);
        imageLabel.setBounds(25, -20, 200, 200);
    }

    private void openLoginPage() {
        LoginPage loginPage = new LoginPage(null);

        JFrame loginFrame = new JFrame("Login Page");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.getContentPane().add(loginPage);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        mainFrame.setVisible(false);
    }

    private void openMainPage() {
        MainPage mainPage = new MainPage();

        JFrame mainPageFrame = new JFrame("Astawash Dashboard");
        mainPageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainPageFrame.getContentPane().add(mainPage);
        mainPageFrame.pack();
        mainPageFrame.setLocationRelativeTo(null);
        mainPageFrame.setVisible(true);

        JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        mainFrame.setVisible(false);
    }

    private void generatePassword() {
        int passwordLength = 12;
        String generatedPassword = generateStrongPassword(passwordLength);
        jcomp7.setText(generatedPassword);
    }

    private String generateStrongPassword(int length) {
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()-=_+[]{}|;:'\",.<>?/";

        String allChars = uppercase + lowercase + digits + specialChars;
        Random random = new SecureRandom();

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allChars.length());
            password.append(allChars.charAt(index));
        }

        return password.toString();
    }

    private void searchWebsite() {
        String enteredWebsite = capitalize(jcomp2d.getText().trim());

        if (!enteredWebsite.isEmpty()) {
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("SELECT email, password FROM websites WHERE website_name = ?")) {
                pstmt.setString(1, enteredWebsite);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String message = "Website: " + enteredWebsite + "\nEmail: " + email + "\nPassword: " + password;
                    JOptionPane.showMessageDialog(this, message, "Password Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Website not found! Please make sure there is no typing error", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void displayPasswordDetails() {
        String selectedWebsite = jcomp1.getSelectedValue();

        if (selectedWebsite != null) {
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("SELECT email, password FROM websites WHERE website_name = ?")) {
                pstmt.setString(1, selectedWebsite);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String message = "Website: " + selectedWebsite + "\nEmail: " + email + "\nPassword: " + password;
                    JOptionPane.showMessageDialog(this, message, "Password Details", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Set<String> loadWebsiteNamesFromDatabase() {
        Set<String> websiteNames = new HashSet<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT website_name FROM websites")) {
            while (rs.next()) {
                websiteNames.add(rs.getString("website_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return websiteNames;
    }

    public void save() {
        String website = jcomp2.getText().trim();
        char[] password = jcomp7.getPassword();
        String email = jcomp5.getText().trim();

        if (website.length() == 0 || password.length == 0) {
            JOptionPane.showMessageDialog(this, "Please make sure you haven't left any fields empty!", "Oops", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("REPLACE INTO websites (website_name, email, password) VALUES (?, ?, ?)")) {
                pstmt.setString(1, capitalize(website));
                pstmt.setString(2, email);
                pstmt.setString(3, String.valueOf(password));
                pstmt.executeUpdate();

                Set<String> websiteNames = loadWebsiteNamesFromDatabase();
                String[] jcomp1Items = websiteNames.toArray(new String[0]);

                jcomp1.setListData(jcomp1Items);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            jcomp2.setText("");
            jcomp2d.setText("");
            jcomp7.setText("");
            jcomp5.setText("");
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PasswordsManager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PasswordsManager());
        frame.pack();
        frame.setVisible(true);
    }
}
