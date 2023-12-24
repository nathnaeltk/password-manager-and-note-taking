import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordManagerPanelTest
{
    //the user interface(GUI) can load without errors
    @Test
    public void test_gui_loads_without_errors() {
        PasswordManagerPanel panel = new PasswordManagerPanel();
        assertNotNull(panel);
    }

    // user can add password with valid input
    @Test
    public void test_user_can_add_password_with_valid_input()
    {
        PasswordManagerPanel panel = new PasswordManagerPanel();
        panel.userTextField.setText("testUser");
        panel.passwordTextField.setText("testPassword");
        panel.websiteTextField.setText("testWebsite");
        panel.addPassword();
        assertEquals(1, panel.passwordListModel.size());
        assertEquals("USER: testUser | PASSWORD: testPassword | WEBSITE: testWebsite", panel.passwordListModel.get(0));
    }

    //allow user to delete a password from a list
    @Test
    public void test_user_can_delete_password_from_list()
    {
        PasswordManagerPanel panel = new PasswordManagerPanel();
        panel.passwordListModel.addElement("USER: testUser | PASSWORD: testPassword | WEBSITE: testWebsite");
        panel.passwordList.setSelectedIndex(0);
        panel.deletePassword();
        assertEquals(0, panel.passwordListModel.size());
    }


    @Test
    public void test_user_cannot_add_password_with_empty_fields()
    {
        PasswordManagerPanel panel = new PasswordManagerPanel();
        panel.addPassword();
        assertEquals(0, panel.passwordListModel.size());
    }

    @Test
    public void test_user_cannot_add_password_with_one_field_filled() {
        PasswordManagerPanel panel = new PasswordManagerPanel();
        panel.userTextField.setText("testUser");
        panel.addPassword();
        assertEquals(0, panel.passwordListModel.size());
    }

    @Test
    public void test_user_cannot_delete_password_when_no_password_selected()
    {
        PasswordManagerPanel panel = new PasswordManagerPanel();
        panel.deletePassword();
        assertEquals(0, panel.passwordListModel.size());
    }

}
