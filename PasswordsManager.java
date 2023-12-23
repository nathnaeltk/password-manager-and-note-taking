import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PasswordsManager extends JPanel {
    // Components declaration
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
    private JLabel imageLabel; // New JLabel for the image

    public PasswordsManager() {
        //construct preComponents
        Set<String> websiteNames = loadWebsiteNamesFromJson("Password.json");
        String[] jcomp1Items = websiteNames.toArray(new String[0]);

        //construct components
        jcomp1 = new JList<>(jcomp1Items);
        // Action listener
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
        // Action listeners
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
        // Action listeners
        jcomp11b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });
        jcomp11 = new JButton("Save");
        // Action listeners
        jcomp11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        jcomp12 = new JButton("Logout");
        jcomp13 = new JButton("Notes");

        //adjust size and set layout
        setPreferredSize(new Dimension(669, 368));
        setLayout(null);

        //add components
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

        //set component bounds (only needed by Absolute Positioning)
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
        setBackground(new Color(190, 203, 226)); // Sky Blue color


        // colors just for button elements
        Color buttonColor = new Color(220, 140, 34); 
        jcomp11d.setBackground(buttonColor);
        jcomp11b.setBackground(buttonColor);
        jcomp11.setBackground(buttonColor);
        jcomp12.setBackground(buttonColor);
        jcomp13.setBackground(buttonColor);


        // Load and add the image
        ImageIcon imageIcon = new ImageIcon("image.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(253, 133, Image.SCALE_SMOOTH); // Adjust size as needed
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        imageLabel = new JLabel(scaledImageIcon);
        add(imageLabel);
        imageLabel.setBounds(25, -20, 200, 200); // Adjust position as needed
    }

    private void generatePassword() {
        // Generate a strong password and set it in the password field
        int passwordLength = 12; 
        String generatedPassword = generateStrongPassword(passwordLength);
        jcomp7.setText(generatedPassword);
    }

    private String generateStrongPassword(int length) {
        // Generate a random strong password
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
        // Search for the entered website in the JSON data
        String enteredWebsite = capitalize(jcomp2d.getText().trim());

        if (!enteredWebsite.isEmpty()) {
            try {
                File file = new File("Password.json");
                if (file.exists() && file.length() > 0) {
                    FileReader fileReader = new FileReader(file);

                    // Use TypeToken to handle generic types during deserialization
                    TypeToken<HashMap<String, HashMap<String, String>>> typeToken = new TypeToken<>() {};
                    HashMap<String, HashMap<String, String>> data = new Gson().fromJson(fileReader, typeToken.getType());
                    fileReader.close();

                    // Check if the entered website exists in the JSON data
                    if (data.containsKey(enteredWebsite)) {
                        // Website found, display details in a pop-up message
                        HashMap<String, String> websiteDetails = data.get(enteredWebsite);
                        String email = websiteDetails.get("email");
                        String password = websiteDetails.get("password");

                        String message = "Website: " + enteredWebsite + "\n"
                                + "Email: " + email + "\n"
                                + "Password: " + password;
                        JOptionPane.showMessageDialog(this, message, "Password Details", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Website not found, display a message
                        JOptionPane.showMessageDialog(this, "Website not found! please make sure that their is no typing error", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    private void displayPasswordDetails() {
        // Get the selected website name
        String selectedWebsite = jcomp1.getSelectedValue();

        if (selectedWebsite != null) {
            try {
                File file = new File("Password.json");
                if (file.exists() && file.length() > 0) {
                    FileReader fileReader = new FileReader(file);

                    // Use TypeToken to handle generic types during deserialization
                    TypeToken<HashMap<String, HashMap<String, String>>> typeToken = new TypeToken<>() {};
                    HashMap<String, HashMap<String, String>> data = new Gson().fromJson(fileReader, typeToken.getType());
                    fileReader.close();

                    // Get the details for the selected website
                    HashMap<String, String> websiteDetails = data.get(selectedWebsite);
                    if (websiteDetails != null) {
                        String email = websiteDetails.get("email");
                        String password = websiteDetails.get("password");

                        // Display details in a pop-up message
                        String message = "Website: " + selectedWebsite + "\n"
                                + "Email: " + email + "\n"
                                + "Password: " + password;
                        JOptionPane.showMessageDialog(this, message, "Password Details", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        }

    private Set<String> loadWebsiteNamesFromJson(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists() && file.length() > 0) {
                FileReader fileReader = new FileReader(file);

                // Use TypeToken to handle generic types during deserialization
                TypeToken<HashMap<String, HashMap<String, String>>> typeToken = new TypeToken<>() {};
                HashMap<String, HashMap<String, String>> data = new Gson().fromJson(fileReader, typeToken.getType());
                fileReader.close();

                return data.keySet(); // Return the set of website names
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new HashSet<>(); // Return an empty set if there is an issue
    }

    public void save() {
        String website = jcomp2.getText().trim();
        char[] password = jcomp7.getPassword();
        String email = jcomp5.getText().trim();
        HashMap<String, HashMap<String, String>> new_data = new HashMap<>();
        HashMap<String, String> innerMap = new HashMap<>();
        innerMap.put("email", email);
        innerMap.put("password", String.valueOf(password));
        new_data.put(capitalize(website), innerMap);
    
        if (website.length() == 0 || password.length == 0) {
            JOptionPane.showMessageDialog(this, "Please make sure you haven't left any fields empty!",
                    "Oops", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                File file = new File("Password.json");
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
    
                // Reload website names from the updated JSON file
                Set<String> websiteNames = loadWebsiteNamesFromJson("Password.json");
                String[] jcomp1Items = websiteNames.toArray(new String[0]);
    
                // Update the JList
                jcomp1.setListData(jcomp1Items);
            } catch (IOException ex) {
                ex.printStackTrace();
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
