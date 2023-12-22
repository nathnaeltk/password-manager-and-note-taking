import org.junit.jupiter.api.Test;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationPageTest
{



        //user can register with valid name,username and password
        @Test
        public void test_valid_registration ()
        {
            RegistrationPage registrationPage = new RegistrationPage();
            registrationPage.jcompname.setText("John Doe");
            registrationPage.jcomp1.setText("johndoe");
            registrationPage.jcomp2.setText("password");

            registrationPage.jcomp7.doClick();

            assertTrue(registrationPage.userList.size() == 1);
            assertEquals("John Doe", registrationPage.userList.get(0).getName());
            assertEquals("johndoe", registrationPage.userList.get(0).getUsername());
            assertEquals("password", registrationPage.userList.get(0).getPassword());
        }

        @Test
        //user cannot register with an existing username
        public void test_existing_username ()
        {
            RegistrationPage registrationPage = new RegistrationPage();
            registrationPage.jcompname.setText("John Doe");
            registrationPage.jcomp1.setText("johndoe");
            registrationPage.jcomp2.setText("password");

            // Add a user with the same username
            User existingUser = new User("Jane Smith", "johndoe", "password123");
            registrationPage.userList.add(existingUser);

            registrationPage.jcomp7.doClick();

            assertTrue(registrationPage.userList.size() == 1);
            assertEquals("Jane Smith", registrationPage.userList.get(0).getName());
            assertEquals("johndoe", registrationPage.userList.get(0).getUsername());
            assertEquals("password123", registrationPage.userList.get(0).getPassword());
        }

        @Test
        // user cannot register with empty name,username and password
        public void test_empty_fields ()
        {
            RegistrationPage registrationPage = new RegistrationPage();
            registrationPage.jcompname.setText("");
            registrationPage.jcomp1.setText("");
            registrationPage.jcomp2.setText("");

            registrationPage.jcomp7.doClick();

            assertFalse(registrationPage.userList.isEmpty());
        }

        //user can see registration success message after finishing



}