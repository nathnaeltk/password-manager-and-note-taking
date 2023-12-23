import java.awt.*;
import javax.swing.*;

public class PasswordsManager extends JPanel {
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
        String[] jcomp1Items = {"Website Account 1", "Website Account 1", "Website Account 1", "Website Account 1", "Website Account 1", "Website Account 1", "Website Account 1", "Website Account 1", "Website Account 1"};

        //construct components
        jcomp1 = new JList<>(jcomp1Items);
        jcomp2d = new JTextField(5);
        jcomp11d = new JButton("Search");
        jcomp2 = new JTextField(5);
        jcomp3 = new JLabel("Website Name");
        jcomp4 = new JLabel("Email or Username");
        jcomp5 = new JTextField(5);
        jcomp6 = new JLabel("Password");
        jcomp7 = new JPasswordField(5);
        jcomp10 = new JLabel("Save a New Password");
        jcomp11b = new JButton("Generate Password");
        jcomp11 = new JButton("Save");
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("PasswordsManager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PasswordsManager());
        frame.pack();
        frame.setVisible(true);
    }
}
