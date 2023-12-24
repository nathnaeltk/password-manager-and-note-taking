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
    public JTextArea jcomp1;
    public JTextArea jcomp2;
    public JLabel jcomp3;
    public JLabel jcomp4;
    public JButton jcomp6;
    public JButton jcomp7;
    public JLabel imageLabel;
    public JPanel registrationPanel; // Panel to hold registration content

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

        // Set background colors
        Color skyBlue = new Color(190, 203, 226); // RGB color for sky blue
        Color green = new Color(220, 140, 34); // RGB color for green

        setBackground(skyBlue); // Set the background color of the main panel to sky blue
        inputPanel.setBackground(skyBlue); // Set the background color of the input panel to sky blue
        buttonPanel.setBackground(skyBlue); // Set the background color of the button panel to sky blue


        jcomp6.setBackground(green); // Set the login button color to green
        jcomp7.setBackground(green); // Set the register button color to green


        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String enteredUsername = jcomp1.getText().trim();
                    String enteredPassword = jcomp2.getText().trim();

                    if ((enteredUsername.isEmpty() || enteredPassword.isEmpty()) || !(enteredUsername.matches("[a-zA-Z]+")) || !(enteredPassword.matches("[a-zA-Z0-9!@#$%^&*()_+-]+")) ) {
                        throw new IllegalArgumentException("something is wrong with Username and password .");
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
        return true;
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

    public void addRegistrationContent()
    {

        RegistrationPage registrationPage = new RegistrationPage();
        registrationPanel.add(registrationPage);
        add(registrationPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) throws IllegalArgumentException {
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
