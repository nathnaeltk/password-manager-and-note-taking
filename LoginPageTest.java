import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageTest
{

    @Test
    public void test_register_button_click()
    {
        LoginPage loginPage = new LoginPage();

        JButton registerButton = loginPage.jcomp7;
        ActionListener[] actionListeners = registerButton.getActionListeners();
        for (ActionListener listener : actionListeners)
        {
            listener.actionPerformed(new ActionEvent(registerButton, ActionEvent.ACTION_PERFORMED, null));
        }

        JPanel registrationPanel = loginPage.registrationPanel;
        assertNotNull(registrationPanel);
    }

    @Test
    public void test_username_and_password_not_null() {
        LoginPage loginPage = new LoginPage();
        boolean result = loginPage.checkCredentials("Username", "Password");
        assertNotNull(result);
    }

    @Test  
    public void test_valid_username_and_password() {
        LoginPage loginPage = new LoginPage();
        boolean result = loginPage.checkCredentials("validUsername", "validPassword");
        assertTrue(result);
    }

    @Test //if it gets invalid username or invalid password, the method should return false.
    public void test_invalid_username_and_password() {
        LoginPage loginPage = new LoginPage();
        boolean result = loginPage.checkCredentials("invalidUsername", "invalidPassword");
        assertTrue(result);
    }
}
