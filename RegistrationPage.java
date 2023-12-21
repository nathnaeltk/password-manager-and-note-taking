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
    private JTextArea jcomp1;
    private JPasswordField jcomp2;
    private JTextArea jcompname;
    private JLabel jlabelname;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JButton jcomp6;
    private JButton jcomp7;
    private JLabel imageLabel;

    private List<User> userList; // New list to store users

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
        jcomp7 = new JButton("Register");

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
                String password = jcomp2.getText();

                // Check if the username already exists
                if (userExists(username)) {
                    JOptionPane.showMessageDialog(RegistrationPage.this, "Account with this username already exists!");
                } else {
                    User newUser = new User(name, username, password);
                    userList.add(newUser); // Add user to the list
                    jcompname.setText("");
                    jcomp1.setText("");
                    jcomp2.setText("");
                    saveUsersToJson();
                    JOptionPane.showMessageDialog(RegistrationPage.this, "Registration Successful! You may now login.");
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

        private boolean userExists(String username) {
            for (User user : userList) {
                if (user.getUsername().equals(username)) {
                    return true; // User already exists
                }
            }
            return false; // User does not exist
        }

        private void addLoginPageContent() {
            LoginPage loginPage = new LoginPage(); // Instantiate the LoginPage
            add(loginPage); // Add the LoginPage to the RegistrationPage panel
        }

    private void saveUsersToJson() {
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