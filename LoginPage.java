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

