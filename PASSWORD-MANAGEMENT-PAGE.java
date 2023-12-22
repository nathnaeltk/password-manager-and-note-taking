
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MyPanel extends JPanel {
    private JButton jcomp1;
    private JButton jcomp2;
    private JButton jcomp3;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JLabel jcomp6;
    private JTextField jcomp7;
    private JTextField jcomp8;
    private JTextField jcomp9;
    private JList jcomp10;

    public MyPanel() {
        //construct preComponents
        String[] jcomp10Items = {"Item 1", "Item 2", "Item 3"};

        //construct components
        jcomp1 = new JButton ("ADD");
        jcomp2 = new JButton ("DELETE");
        jcomp3 = new JButton ("UPDATE");
        jcomp4 = new JLabel ("   USER OR USERNAME");
        jcomp5 = new JLabel ("    PASSWORD");
        jcomp6 = new JLabel ("  WEBSITE");
        jcomp7 = new JTextField (5);
        jcomp8 = new JTextField (5);
        jcomp9 = new JTextField (5);
        jcomp10 = new JList (jcomp10Items);
        // Add image to the GUI
        ImageIcon imageIcon = new ImageIcon("image.png");
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel);
        imageLabel.setBounds(30, 20, 600, 950);

        //adjust size and set layout
        setPreferredSize (new Dimension (669, 416));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);
        add (jcomp8);
        add (jcomp9);
        add (jcomp10);

        //set component bounds (only needed by Absolute Positioning)
        addButton.setBounds(145, 200, 100, 25);
        deleteButton.setBounds(310, 200, 100, 25);
        updateButton.setBounds(460, 200, 100, 25);
        userLabel.setBounds(25, 70, 105, 30);
        passwordLabel.setBounds(20, 110, 100, 25);
        websiteLabel.setBounds(30, 145, 100, 25);
        userTextField.setBounds(145, 70, 415, 30);
        passwordTextField.setBounds(145, 105, 415, 30);
        websiteTextField.setBounds(145, 145, 415, 30);
        passwordList.setBounds(145, 255, 410, 120);
        setBackground(new Color(135, 206, 235));
        addButton.setBackground(new Color(0, 128, 0)); // green color
        deleteButton.setBackground(new Color(255, 0, 0)); // red color
        updateButton.setBackground(new Color(255, 192, 203)); // sky blue color
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new MyPanel());
        frame.pack();
        frame.setVisible (true);
    }
}
