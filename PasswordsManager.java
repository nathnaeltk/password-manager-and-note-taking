import java.awt.*;
import javax.swing.*;

public class PasswordsManager extends JPanel {
    private JList<String> jcomp1;
    private JPasswordField jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JTextField jcomp5;
    private JLabel jcomp6;
    private JTextField jcomp7;
    private JTextField jcomp8;
    private JLabel jcomp9;
    private JLabel jcomp10;
    private JButton jcomp11;
    private JButton jcomp12;
    private JButton jcomp13;
    private JLabel imageLabel; // New JLabel for the image

    public PasswordsManager() {
        //construct preComponents
        String[] jcomp1Items = {"Website Account 1", "Website Account 1", "Website Account 1", "Website Account 1", "Website Account 1", "Website Account 1", "Website Account 1", "Website Account 1", "Website Account 1"};

        //construct components
        jcomp1 = new JList<>(jcomp1Items);
        jcomp2 = new JPasswordField(5);
        jcomp3 = new JLabel("Website Name");
        jcomp4 = new JLabel("Email or Username");
        jcomp5 = new JTextField(5);
        jcomp6 = new JLabel("Password");
        jcomp7 = new JTextField(5);
        jcomp8 = new JTextField(5);
        jcomp9 = new JLabel("Repeat Password");
        jcomp10 = new JLabel("Save a New Password");
        jcomp11 = new JButton("Save");
        jcomp12 = new JButton("Logout");
        jcomp13 = new JButton("Notes");

        //adjust size and set layout
        setPreferredSize(new Dimension(669, 368));
        setLayout(null);

        //add components
        add(jcomp1);
        add(jcomp2);
        add(jcomp3);
        add(jcomp4);
        add(jcomp5);
        add(jcomp6);
        add(jcomp7);
        add(jcomp8);
        add(jcomp9);
        add(jcomp10);
        add(jcomp11);
        add(jcomp12);
        add(jcomp13);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds(35, 140, 160, 175);
        jcomp2.setBounds(330, 140, 265, 35);
        jcomp3.setBounds(235, 145, 100, 25);
        jcomp4.setBounds(215, 185, 120, 25);
        jcomp5.setBounds(330, 180, 265, 35);
        jcomp6.setBounds(265, 225, 100, 25);
        jcomp7.setBounds(330, 220, 265, 40);
        jcomp8.setBounds(330, 265, 265, 40);
        jcomp9.setBounds(220, 270, 105, 30);
        jcomp10.setBounds(405, 110, 150, 25);
        jcomp11.setBounds(495, 305, 100, 25);
        jcomp12.setBounds(495, 40, 100, 25);
        jcomp13.setBounds(390, 40, 100, 25);

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
