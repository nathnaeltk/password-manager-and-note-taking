import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
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
}

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MyPanel());
        frame.pack();
        frame.setVisible(true);
    }
}

