import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationPage extends JPanel {
    public JTextArea jcomp1;
    public JPasswordField jcomp2;
    public JTextArea jcompname;
    public JLabel jlabelname;
    public JLabel jcomp3;
    public JLabel jcomp4;
    public JButton jcomp6;
    public JButton jcomp7;
    public JLabel imageLabel;

    public List<User> userList; // New list to store users

    public RegistrationPage() {
        jcomp1 = new JTextArea(2, 30);
        jcomp2 = new JPasswordField(30);
        Dimension preferredSize = jcomp2.getPreferredSize();
        preferredSize.height *= 2; // Doubling the height
        jcomp2.setPreferredSize(preferredSize);
        jcompname = new JTextArea(2, 30);
        jlabelname = new JLabel("Name: ");
        jcomp3 = new JLabel("Username: ");
        jcomp4 = new JLabel("Password: ");

        jcomp6 = new JButton("Login");
        jcomp6.setBackground(new Color(0, 128, 0)); // Set the login button to green
        jcomp7 = new JButton("Register");
        jcomp7.setBackground(new Color(0, 128, 0)); // Set the register button to green

// Set the background of JTextArea and JPasswordField to sky blue
        Color skyBlue = new Color(135, 206, 235);
        jcomp1.setBackground(skyBlue);
        jcomp2.setBackground(skyBlue);
        jcompname.setBackground(skyBlue);

        ImageIcon imageIcon = new ImageIcon("image.png");
        imageLabel = new JLabel(imageIcon);

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

        add(imageLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        userList = new ArrayList<>(); // Initialize user list

        jcomp7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jcompname.getText();
                String username = jcomp1.getText();
                String password = new String(jcomp2.getPassword()); // Use getPassword for JPasswordField

                // Check if any field is empty
                if ((name.isEmpty() ||
                        !(name.matches("[a-zA-Z]+")))||
                        (username.isEmpty() || !(username.matches("[a-zA-Z0-9!@#$%^&*()_+-]+")) )|| 
                        (password.isEmpty()) || !(password.matches("[a-zA-Z0-9!@#$%^&*()_+-]+")) ) 
                {
                    JOptionPane.showMessageDialog(RegistrationPage.this, " something is wrong with Name,username or password .");
                } else {
                    // Check if the username already exists
                    if (userExists(username)) 
                    {
                        JOptionPane.showMessageDialog(RegistrationPage.this, "Account with this username already exists!");
                    } 
                    else 
                    {
                        User newUser = new User(name, username, password);
                        userList.add(newUser); // Add user to the list
                        jcompname.setText("");
                        jcomp1.setText("");
                        jcomp2.setText("");
                        saveUsersToJson();
                        JOptionPane.showMessageDialog(RegistrationPage.this, "Registration Successful! You may now login.");
                    }
                }
            }
        });



        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                addLoginPageContent();
                revalidate();
                repaint();
            }
        });
    }

    public boolean userExists(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true; // User already exists
            }
        }
        return false; // User does not exist
    }

    public void addLoginPageContent() {
        abcd loginPage = new abcd(); 
        add(loginPage); // calling the add method
    }

    public void saveUsersToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(userList);

        try (FileWriter fileWriter = new FileWriter("users.json")) {
            fileWriter.write(json);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Register - Astawash");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            RegistrationPage panel = new RegistrationPage();
            frame.getContentPane().add(panel);
            frame.setSize(600, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
