<<<<<<< HEAD
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
    private JPasswordField  jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JButton jcomp6;
    private JButton jcomp7;
    private JLabel imageLabel;
    private JPanel registrationPanel; // Panel to hold registration content

    public LoginPage() {
        // Construct components
        jcomp1 = new JTextArea(2, 30);
        jcomp2 = new JPasswordField(30);
        Dimension preferredSize = jcomp2.getPreferredSize();
        preferredSize.height *= 2; // Doubling the height
        jcomp2.setPreferredSize(preferredSize);
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
=======
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginPage extends JPanel {
    private JMenuBar options;
    private JList<String> noteList;
    private JScrollPane scrollPane;
    private JButton managePasswordsButton;
    private JButton newNoteButton;
    private JButton saveButton;
    private JButton logoutButton;
    private JLabel noteTitleLabel;
    private JTextArea noteTextArea;
    private JLabel dateLabel;
    private JTextArea noteContentArea;
    private JLabel myNotesLabel;

    private MyPanel myPanel;

    public LoginPage() {
        // Construct preComponents
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem managePasswordsItem = new JMenuItem("Manage Passwords");
        managePasswordsItem.addActionListener(e -> showMyPanel());
        optionsMenu.add(managePasswordsItem);
        JMenuItem newNoteItem = new JMenuItem("New Note");
        optionsMenu.add(newNoteItem);
        JMenuItem newAccountItem = new JMenuItem("New Account");
        optionsMenu.add(newAccountItem);
        JMenuItem logoutItem = new JMenuItem("Logout");
        optionsMenu.add(logoutItem);
        JMenu helpMenu = new JMenu("Help");
        JMenuItem versionInformationItem = new JMenuItem("Version Information");
        helpMenu.add(versionInformationItem);
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        // Construct components
        options = new JMenuBar();
        options.add(optionsMenu);
        options.add(helpMenu);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 1; i <= 15; i++) {
            listModel.addElement("Note " + i);
        }
        noteList = new JList<>(listModel);
        scrollPane = new JScrollPane(noteList);
        managePasswordsButton = new JButton("Manage Passwords");
        managePasswordsButton.addActionListener(e -> showMyPanel());
        newNoteButton = new JButton("New Note");
        saveButton = new JButton("Save");
        logoutButton = new JButton("Logout");
        noteTitleLabel = new JLabel("Note Title: ");
        noteTextArea = new JTextArea();
        dateLabel = new JLabel("Date: " + java.time.LocalDate.now());
        noteContentArea = new JTextArea();
        myNotesLabel = new JLabel("My Notes");

        // Set components properties
        noteContentArea.setToolTipText("Text");

        // Adjust size and set layout
        setPreferredSize(new Dimension(669, 368));
        setLayout(null);

        // Add components
        add(options);
        add(scrollPane);
        add(managePasswordsButton);
        add(newNoteButton);
        add(saveButton);
        add(logoutButton);
        add(noteTitleLabel);
        add(noteTextArea);
        add(dateLabel);
        add(noteContentArea);
        add(myNotesLabel);

        // Set component bounds (only needed by Absolute Positioning)
        options.setBounds(0, 0, 735, 20);
        scrollPane.setBounds(60, 115, 150, 200); // Adjust size as needed
        managePasswordsButton.setBounds(260, 40, 165, 25);
        newNoteButton.setBounds(435, 40, 100, 25);
        saveButton.setBounds(538, 320, 100, 25);
        logoutButton.setBounds(545, 40, 100, 25);
        noteTitleLabel.setBounds(220, 80, 70, 30);
        noteTextArea.setBounds(280, 80, 250, 30);
        dateLabel.setBounds(545, 80, 100, 25);
        noteContentArea.setBounds(220, 115, 420, 200);
        myNotesLabel.setBounds(60, 90, 60, 25);

        // Initialize MyPanel
        myPanel = new MyPanel();
    }

    private void showMyPanel() {
        JFrame frame = new JFrame("Manage Passwords");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(myPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Astawash Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new LoginPage());
        frame.pack();
        frame.setVisible(true);
    }
}

class MyPanel extends JPanel {
    // Existing MyPanel code, unchanged
    private JButton submitButton;
    private JButton displayButton;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField websiteField;
    private JTextArea displayArea;

    private ArrayList<String> usernames;
    private ArrayList<String> passwords;
    private ArrayList<String> websites;
    public MyPanel() {
    submitButton = new JButton("Submit");
    displayButton = new JButton("Display");
    usernameField = new JTextField();
   passwordField = new JTextField();
    websiteField = new JTextField();
    JLabel usernameLabel = new JLabel("Username");
   JLabel passwordLabel = new JLabel("Password");
    JLabel websiteLabel = new JLabel("Website");

    usernames = new ArrayList<>();
    passwords = new ArrayList<>();
    websites = new ArrayList<>();

    setLayout(new GridLayout(0, 2)); // Set layout to grid with 2 columns

    add(usernameLabel);
    add(usernameField);
    add(passwordLabel);
    add(passwordField);
   add(websiteLabel);
    add(websiteField);
    add(submitButton);
    add(displayButton);

    submitButton.addActionListener(e -> storeValues());
    displayButton.addActionListener(e -> displayValues());

    displayArea = new JTextArea(3, 20);
    displayArea.setEditable(false);
    add(new JScrollPane(displayArea)); // Add a scroll pane containing the display area
}

   
    private void storeValues() {
        usernames.add(usernameField.getText());
        passwords.add(passwordField.getText());
        websites.add(websiteField.getText());
    }

    
    private void displayValues() {
   JFrame displayFrame = new JFrame("Display Values");
   JTextArea displayTextArea = new JTextArea(10, 30);
    displayTextArea.setEditable(false);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < usernames.size(); i++) {
        sb.append(" Username: ").append(usernames.get(i))
          .append(" \n Password: ").append(passwords.get(i))
          .append(" \n Website: ").append(websites.get(i)).append("\n\n");
   }
    displayTextArea.setText(sb.toString());
    JScrollPane scrollPane = new JScrollPane(displayTextArea);
   displayFrame.getContentPane().add(scrollPane);
    displayFrame.setSize(400, 400);
    displayFrame.setVisible(true);
}}

>>>>>>> 2892b66cd6aca0b14d2db361abe3519bc5ff2b5d
