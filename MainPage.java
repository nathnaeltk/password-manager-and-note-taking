import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
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

        jcomp5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openPasswordsManager();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginPage();
            }
        });

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

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT title FROM notes";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                listModel.addElement(title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openMainPageExternal() {
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

    public void openPasswordsManager() throws SQLException {
        PasswordsManager passwordsManager = new PasswordsManager();
   
        JFrame passwordsManagerFrame = new JFrame("Passwords Manager");
        passwordsManagerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   
        passwordsManagerFrame.getContentPane().add(passwordsManager);
   
        passwordsManagerFrame.pack();
        passwordsManagerFrame.setLocationRelativeTo(null);
        passwordsManagerFrame.setVisible(true);
   
        JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        mainFrame.setVisible(false);
    }
    
    

    private void openLoginPage() {
        JFrame loginPageFrame = new JFrame("Login Page");
        loginPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        LoginPage loginPage = new LoginPage(null);
        loginPageFrame.getContentPane().add(loginPage);
    
        loginPageFrame.pack();
        loginPageFrame.setLocationRelativeTo(null);
        loginPageFrame.setVisible(true);
    
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        currentFrame.dispose();
    }

    public void save() {
        String title = jcomp9.getText();
        String note = jcomp11.getText();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please make sure you haven't left any fields empty!",
                    "Oops", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "INSERT INTO notes (title, content) VALUES (?, ?) ON DUPLICATE KEY UPDATE content = VALUES(content)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, title);
                stmt.setString(2, note);
                stmt.executeUpdate();
                
                if (!listModel.contains(title)) {
                    listModel.addElement(title);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            jcomp9.setText("");
            jcomp11.setText("");
        }
    }

    private void displayNoteForSelectedTitle() {
        String selectedTitle = noteList.getSelectedValue();
        if (selectedTitle != null) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "SELECT content FROM notes WHERE title = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, selectedTitle);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String note = rs.getString("content");
                    jcomp9.setText(selectedTitle); 
                    jcomp11.setText(note);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Astawash Dashboard");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            MainPage mainPage = new MainPage();
            frame.getContentPane().add(mainPage);
    
            frame.pack();
            frame.setLocationRelativeTo(null); 
            frame.setVisible(true);
        });
    }
}
