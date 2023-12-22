
import org.junit.jupiter.api.Test;


import java.awt.*;


import static org.junit.jupiter.api.Assertions.*;
class MainPageTest
{
    @Test
    public void test_constructor_components_properties()
    {
        MainPage mainPage = new MainPage();

        assertNotNull(mainPage.Options);
        assertNotNull(mainPage.noteList);
        assertNotNull(mainPage.scrollPane);
        assertNotNull(mainPage.jcomp5);
        assertNotNull(mainPage.jcomp6);
        assertNotNull(mainPage.jcomp7);
        assertNotNull(mainPage.jcomp8);
        assertNotNull(mainPage.jcomp88);
        assertNotNull(mainPage.jcomp9);
        assertNotNull(mainPage.jcomp10);
        assertNotNull(mainPage.jcomp11);
        assertNotNull(mainPage.jcomp12);
        assertEquals("Options", mainPage.Options.getMenu(0).getText());
        assertEquals("Help", mainPage.Options.getMenu(1).getText());

        assertEquals("Manage Passwords", mainPage.Options.getMenu(0).getItem(0).getText());
        assertEquals("New Note", mainPage.Options.getMenu(0).getItem(1).getText());
        assertEquals("New Account", mainPage.Options.getMenu(0).getItem(2).getText());
        assertEquals("Logout", mainPage.Options.getMenu(0).getItem(3).getText());

        assertEquals("Version Information", mainPage.Options.getMenu(1).getItem(0).getText());
        assertEquals("About", mainPage.Options.getMenu(1).getItem(1).getText());

        assertEquals(mainPage.listModel, mainPage.noteList.getModel());

        assertEquals("Manage Passwords", mainPage.jcomp5.getText());
        assertEquals("New Note", mainPage.jcomp6.getText());
        assertEquals("Save", mainPage.jcomp7.getText());
        assertEquals("Logout", mainPage.jcomp8.getText());
        assertEquals("Note Title: ", mainPage.jcomp88.getText());

        assertEquals("Text", mainPage.jcomp11.getToolTipText());

        assertEquals(new Dimension(669, 368), mainPage.getPreferredSize());

        assertEquals(new Color(135, 206, 235), mainPage.getBackground());

        assertEquals(new Color(0, 128, 0), mainPage.jcomp5.getBackground());
        assertEquals(new Color(0, 128, 0), mainPage.jcomp6.getBackground());
        assertEquals(new Color(0, 128, 0), mainPage.jcomp7.getBackground());
        assertEquals(new Color(0, 128, 0), mainPage.jcomp8.getBackground());
    }




    }