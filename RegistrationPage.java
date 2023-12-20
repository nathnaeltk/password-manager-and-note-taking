import javax.swing.*;
import java.awt.*;

public class RegistrationPage extends JPanel {
    private JTextArea jcomp1;
    private JTextArea jcomp2;
    private JTextArea jcompname;
    private JLabel jlabelname;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JButton jcomp7;
    private JLabel imageLabel;

    public RegistrationPage() {
        // Construct components
        jcomp1 = new JTextArea(2, 30); // Increased rows for the JTextArea
        jcomp2 = new JTextArea(2, 30); // Increased rows for the JTextArea
        jcompname = new JTextArea(2, 30);
        jlabelname = new JLabel("Name: ");
        jcomp3 = new JLabel("Username: ");
        jcomp4 = new JLabel("Password: ");
        jcomp7 = new JButton("Register");

        ImageIcon imageIcon = new ImageIcon("image.png");
        imageLabel = new JLabel(imageIcon);

        // Set layout manager to BorderLayout for organized placement
        setLayout(new BorderLayout());
        
        // Create panels to manage components' placement
        JPanel inputPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to inputPanel using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(jlabelname, gbc);
        gbc.gridy++;
        inputPanel.add(jcompname, gbc);
        gbc.gridy++;
        inputPanel.add(jcomp3, gbc);

        gbc.gridy++;
        inputPanel.add(jcomp1, gbc);

        gbc.gridy++;
        inputPanel.add(jcomp4, gbc);

        gbc.gridy++;
        inputPanel.add(jcomp2, gbc);

        // Add components to buttonPanel using FlowLayout
        buttonPanel.add(jcomp7);

        // Add components to main panel using BorderLayout
        add(imageLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Register - Astawash");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            RegistrationPage panel = new RegistrationPage();
            frame.getContentPane().add(panel);
            frame.setSize(600, 600); // Increased window height
            frame.setLocationRelativeTo(null); // Center the window on the screen
            frame.setVisible(true);
        });
    }
}
