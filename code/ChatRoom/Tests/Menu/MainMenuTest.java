package Menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainMenuTest {
    @Test
    public void EnsureMainMenuHas4ConstantsTest(){
        Assertions.assertEquals(4,MainMenu.values().length);
    }
    @Test
    public void GetMenuEntryTest() {
        Assertions.assertEquals("0. Host chat room",MainMenu.values()[0].GetMenuEntry());
        Assertions.assertEquals("1. Join chat room",MainMenu.values()[1].GetMenuEntry());
        Assertions.assertEquals("2. View old chats",MainMenu.values()[2].GetMenuEntry());
        Assertions.assertEquals("3. Quit",MainMenu.values()[3].GetMenuEntry());
    }
}
