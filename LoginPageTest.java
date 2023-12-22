import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageTest
{
    //test 1
    @Test
    public void test_valid_username_and_password()
    {
        LoginPage loginPage = new LoginPage();
        loginPage.jcomp1.setText("valid_username");
        loginPage.jcomp2.setText("valid_password");

        JButton loginButton = loginPage.jcomp6;
        ActionListener[] actionListeners = loginButton.getActionListeners();
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(new ActionEvent(loginButton, ActionEvent.ACTION_PERFORMED, null));
        }

        // Assert that the login page is closed and the main page is opened
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(loginPage);
        assertFalse(frame.isVisible());
    }

    // test 2
    @Test
    public void test_invalid_username_or_password()
    {
        LoginPage loginPage = new LoginPage();
        loginPage.jcomp1.setText("invalid_username");
        loginPage.jcomp2.setText("invalid_password");

        JButton loginButton = loginPage.jcomp6;
        ActionListener[] actionListeners = loginButton.getActionListeners();
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(new ActionEvent(loginButton, ActionEvent.ACTION_PERFORMED, null));
        }

        // Assert that an error message is displayed
        JOptionPane pane = (JOptionPane) SwingUtilities.getAncestorOfClass(JOptionPane.class, loginPage);
        assertNotNull(pane);
        assertEquals("Invalid username or password!", pane.getMessage());
    }

    //test 3
    @Test
    public void test_username_or_password_with_white_spaces()
    {
        LoginPage loginPage = new LoginPage();
        loginPage.jcomp1.setText("  username_with_spaces  ");
        loginPage.jcomp2.setText("  password_with_spaces  ");

        JButton loginButton = loginPage.jcomp6;
        ActionListener[] actionListeners = loginButton.getActionListeners();
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(new ActionEvent(loginButton, ActionEvent.ACTION_PERFORMED, null));
        }

        // Assert that an error message is displayed
        JOptionPane pane = (JOptionPane) SwingUtilities.getAncestorOfClass(JOptionPane.class, loginPage);
        assertNotNull(pane);
        assertEquals("Invalid username or password!", pane.getMessage());
    }

    //test 4
    @Test
    public void test_username_or_password_with_special_characters()
    {
        LoginPage loginPage = new LoginPage();
        loginPage.jcomp1.setText("username_with_@");
        loginPage.jcomp2.setText("password_with_!");

        JButton loginButton = loginPage.jcomp6;
        ActionListener[] actionListeners = loginButton.getActionListeners();
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(new ActionEvent(loginButton, ActionEvent.ACTION_PERFORMED, null));
        }

        // Assert that an error message is displayed
        JOptionPane pane = (JOptionPane) SwingUtilities.getAncestorOfClass(JOptionPane.class, loginPage);
        assertNotNull(pane);
        assertEquals("Invalid username or password!", pane.getMessage());
    }

    //test 5
    @Test
    public void test_username_or_password_with_long_length()
    {
        LoginPage loginPage = new LoginPage();
        loginPage.jcomp1.setText("username_with_length_greater_than_30_characters");
        loginPage.jcomp2.setText("password_with_length_greater_than_30_characters");

        JButton loginButton = loginPage.jcomp6;
        ActionListener[] actionListeners = loginButton.getActionListeners();
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(new ActionEvent(loginButton, ActionEvent.ACTION_PERFORMED, null));
        }

        // Assert that an error message is displayed
        JOptionPane pane = (JOptionPane) SwingUtilities.getAncestorOfClass(JOptionPane.class, loginPage);
        assertNotNull(pane);
        assertEquals("Invalid username or password!", pane.getMessage());
    }

    //test 6
    @Test
    public void test_register_button_redirects_to_registration_page()
    {
        LoginPage loginPage = new LoginPage();

        JButton registerButton = loginPage.jcomp7;
        ActionListener[] actionListeners = registerButton.getActionListeners();
        for (ActionListener listener : actionListeners)
        {
            listener.actionPerformed(new ActionEvent(registerButton, ActionEvent.ACTION_PERFORMED, null));
        }

        // Assert that the registration panel is added to the main panel
        JPanel registrationPanel = loginPage.registrationPanel;
        assertNotNull(registrationPanel);
    }

}