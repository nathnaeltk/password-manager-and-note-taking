import java.awt.*;
import javax.swing.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainPage extends JPanel {
    private JMenuBar Options;
    private JList<String> noteList;
    private JScrollPane scrollPane;
    private JButton jcomp5;
    private JButton jcomp6;
    private JButton jcomp7;
    private JButton jcomp8;
    private JLabel jcomp88;
    private JTextArea jcomp9;
    private JLabel jcomp10;
    private JTextArea jcomp11;
    private JLabel jcomp12;
    String filePath = "Data.json";
    private DefaultListModel<String> listModel;

    public MainPage() {
       
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem manage_passwordsItem = new JMenuItem("Manage Passwords");
        optionsMenu.add(manage_passwordsItem);
        JMenuItem new_noteItem = new JMenuItem("New Note");
        optionsMenu.add(new_noteItem);
        JMenuItem new_accountItem = new JMenuItem("New Account");
        optionsMenu.add(new_accountItem);
        JMenuItem logoutItem = new JMenuItem("Logout");
        optionsMenu.add(logoutItem);
        JMenu helpMenu = new JMenu("Help");
        JMenuItem version_informationItem = new JMenuItem("Version Information");
        helpMenu.add(version_informationItem);
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        
        Options = new JMenuBar();
        Options.add(optionsMenu);
        Options.add(helpMenu);
        listModel = new DefaultListModel<>();

        noteList = new JList<>(listModel);
        scrollPane = new JScrollPane(noteList);
        jcomp5 = new JButton("Manage Passwords");
        jcomp6 = new JButton("New Note");
        jcomp7 = new JButton("Save");


        jcomp7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        jcomp8 = new JButton("Logout");
        jcomp88 = new JLabel("Note Title: ");
        jcomp9 = new JTextArea();
        
        jcomp10 = new JLabel("Date: " + java.time.LocalDate.now());
        jcomp11 = new JTextArea();
        jcomp12 = new JLabel("My Notes");

        jcomp11.setToolTipText("Text");

        
        setPreferredSize(new Dimension(669, 368));
        setLayout(null);


                // Add ActionListener to manage passwords button
        jcomp5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPasswordsManager();
            }
        });

        // Add listener for new note button
        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMainPage();
            }
        });

        // Add ActionListener to logout button
        jcomp8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginPage();
            }
        });
        
        add(Options);
        add(scrollPane);
        add(jcomp5);
        add(jcomp6);
        add(jcomp7);
        add(jcomp8);
        add(jcomp88);
        add(jcomp9);
        add(jcomp10);
        add(jcomp11);
        add(jcomp12);

    
        Options.setBounds(0, 0, 735, 20);
        scrollPane.setBounds(60, 115, 150, 200); 
        jcomp5.setBounds(260, 40, 165, 25);
        jcomp6.setBounds(435, 40, 100, 25);
        jcomp7.setBounds(538, 320, 100, 25);
        jcomp8.setBounds(545, 40, 100, 25);
        jcomp88.setBounds(220, 80, 70, 30);
        jcomp9.setBounds(280, 80, 250, 30);
        jcomp10.setBounds(545, 80, 100, 25);
        jcomp11.setBounds(220, 115, 420, 200);
        jcomp12.setBounds(60, 90, 60, 25);
        setBackground(new Color(190, 203, 226)); 

       
       Color buttonColor = new Color(220, 140, 34); 
       Color noteColor = new Color(242, 236, 183);
        
        jcomp11.setBackground(noteColor);

        noteList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    displayNoteForSelectedTitle();
                }
            }
        });
        // This is to read data from file and populate the listModel
        try {
            File file = new File(filePath);
            if (file.exists() && file.length() > 0) {
                FileReader fileReader = new FileReader(file);
                HashMap<String, HashMap<String, String>> data = new Gson().fromJson(fileReader, HashMap.class);
                fileReader.close();

                Set<String> keys = data.keySet();
                for (String key : keys) {
                    listModel.addElement(key);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void openMainPage() {
        // Instantiate PasswordsManager
        MainPage mainPage = new MainPage();
    
        // Create a new JFrame to contain the PasswordsManager window
        JFrame mainPageFrame = new JFrame("Astawash Dashboard");
        mainPageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the PasswordsManager window
    
        // Set the content of the frame to the PasswordsManager instance
        mainPageFrame.getContentPane().add(mainPage);
    
        // Adjust the frame properties and display it
        mainPageFrame.pack();
        mainPageFrame.setLocationRelativeTo(null); // Center the frame on the screen
        mainPageFrame.setVisible(true);
    
        // Hide or dispose of the MainPage frame
        JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // Assuming MainPage is a JPanel
        mainFrame.setVisible(false); // Hides the MainPage frame
    }

    private void openPasswordsManager() {
        // Instantiate PasswordsManager
        PasswordsManager passwordsManager = new PasswordsManager();
    
        // Create a new JFrame to contain the PasswordsManager window
        JFrame passwordsManagerFrame = new JFrame("Passwords Manager");
        passwordsManagerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the PasswordsManager window
    
        // Set the content of the frame to the PasswordsManager instance
        passwordsManagerFrame.getContentPane().add(passwordsManager);
    
        // Adjust the frame properties and display it
        passwordsManagerFrame.pack();
        passwordsManagerFrame.setLocationRelativeTo(null); // Center the frame on the screen
        passwordsManagerFrame.setVisible(true);
    
        // Hide or dispose of the MainPage frame
        JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // Assuming MainPage is a JPanel
        mainFrame.setVisible(false); // Hides the MainPage frame
    }


    // Method to open LoginPage class
private void openLoginPage() {
    // Instantiate LoginPage
    LoginPage loginPage = new LoginPage();

    // Create a new JFrame to contain the LoginPage window
    JFrame loginFrame = new JFrame("Login Page");
    loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the LoginPage window

    // Set the content of the frame to the LoginPage instance
    loginFrame.getContentPane().add(loginPage);

    // Adjust the frame properties and display it
    loginFrame.pack();
    loginFrame.setLocationRelativeTo(null); // Center the frame on the screen
    loginFrame.setVisible(true);

    // Hide or dispose of the MainPage frame
    JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this); // Assuming MainPage is a JPanel
    mainFrame.setVisible(false); // Hides the MainPage frame
    // Alternatively, you can use mainFrame.dispose(); to close the MainPage frame
}
    

// Method to read data from the file and return as a HashMap
private HashMap<String, HashMap<String, String>> readDataFromFile() {
    try {
        File file = new File(filePath);
        if (file.exists() && file.length() > 0) {
            FileReader fileReader = new FileReader(file);

            // Use TypeToken to handle generic types during deserialization
            TypeToken<HashMap<String, HashMap<String, String>>> typeToken = new TypeToken<>() {};
            return new Gson().fromJson(fileReader, typeToken.getType());
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    return new HashMap<>();
}
private void displayNoteForSelectedTitle() {
    String selectedTitle = noteList.getSelectedValue();
    if (selectedTitle != null) {
        HashMap<String, HashMap<String, String>> data = readDataFromFile();
        HashMap<String, String> noteData = data.get(selectedTitle);
        if (noteData != null) {
            String note = noteData.get("note");
            jcomp9.setText(selectedTitle); // Set the selected title to jcomp9
            jcomp11.setText(note);
        }
    }
}
 public void save() {
        String title = jcomp9.getText();
        String note = jcomp11.getText();
        HashMap<String, HashMap<String, String>> new_data = new HashMap<>();
        HashMap<String, String> innerMap = new HashMap<>();
        innerMap.put("note", note);
        new_data.put(title, innerMap);

        if (title.length() == 0 ) {
            JOptionPane.showMessageDialog(this, "Please make sure you haven't left any fields empty!",
                    "Oops", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                File file = new File("Data.json");
                if (!file.exists()) {
                    file.createNewFile();
                }

                // Reading old data
                FileReader fileReader = new FileReader(file);
                HashMap<String, HashMap<String, String>> data = new HashMap<>();
                if (file.length() > 0) {
                    data = new Gson().fromJson(fileReader, HashMap.class);
                }
                fileReader.close();

                // Updating old data with new data
                data.putAll(new_data);

                // Saving the updated data
                FileWriter fileWriter = new FileWriter(file);
                new GsonBuilder().setPrettyPrinting().create().toJson(data, fileWriter);
                fileWriter.flush();
                fileWriter.close();
                // Adding the current title to the listModel
                listModel.addElement(title);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            jcomp9.setText("");
            jcomp11.setText("");
        }
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Astawash Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MainPage());
        frame.pack();
        frame.setVisible(true);
    }
}
         


