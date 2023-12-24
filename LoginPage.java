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
    private JPanel registrationPanel; 

    public LoginPage() {
        
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
        jcomp6.setBackground(green);
        jcomp7.setBackground(green); 

       
        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = jcomp1.getText();
                String enteredPassword = jcomp2.getText();

                if (checkCredentials(enteredUsername, enteredPassword)) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(LoginPage.this);
                    frame.dispose(); 
                    openMainPage();
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Invalid username or password!");
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

    private boolean checkCredentials(String username, String password) {
        try (FileReader fileReader = new FileReader("users.json")) {
            Gson gson = new Gson();
            Type userListType = new TypeToken<List<User>>(){}.getType();
            List<User> userList = gson.fromJson(fileReader, userListType);

            if (userList != null) {
                for (User user : userList) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        return true; 
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false; 
    }

    private void openMainPage() {
        // Open MainPage
        MainPage mainPage = new MainPage();
        JFrame mainFrame = new JFrame("Main Page");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add(mainPage);
        mainFrame.setSize(669, 450);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void addRegistrationContent() {
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPanel.add(registrationPage);
        registrationPanel
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
