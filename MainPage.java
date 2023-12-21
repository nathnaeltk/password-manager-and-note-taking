import java.awt.*;
import javax.swing.*;

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

    public MainPage() {
        //construct preComponents
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

        //construct components
        Options = new JMenuBar();
        Options.add(optionsMenu);
        Options.add(helpMenu);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 1; i <= 15; i++) {
            listModel.addElement("Note " + i);
        }
        noteList = new JList<>(listModel);
        scrollPane = new JScrollPane(noteList);
        jcomp5 = new JButton("Manage Passwords");
        jcomp6 = new JButton("New Note");
        jcomp7 = new JButton("Save");
        jcomp8 = new JButton("Logout");
        jcomp88 = new JLabel("Note Title: ");
        jcomp9 = new JTextArea();
        // format the day today into the label string
        jcomp10 = new JLabel("Date: "+java.time.LocalDate.now());
        jcomp11 = new JTextArea();
        jcomp12 = new JLabel("My Notes");

        //set components properties
        jcomp11.setToolTipText("Text");

        //adjust size and set layout
        setPreferredSize(new Dimension(669, 368));
        setLayout(null);

        //add components
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

        //set component bounds (only needed by Absolute Positioning)
        Options.setBounds(0, 0, 735, 20);
        scrollPane.setBounds(60, 115, 150, 200); // Adjust size as needed
        jcomp5.setBounds(260, 40, 165, 25);
        jcomp6.setBounds(435, 40, 100, 25);
        jcomp7.setBounds(538, 320, 100, 25);
        jcomp8.setBounds(545, 40, 100, 25);
        jcomp88.setBounds(220, 80, 70, 30);
        jcomp9.setBounds(280, 80, 250, 30);
        jcomp10.setBounds(545, 80, 100, 25);
        jcomp11.setBounds(220, 115, 420, 200);
        jcomp12.setBounds(60, 90, 60, 25);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Astawash Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MainPage());
        frame.pack();
        frame.setVisible(true);
    }
}
