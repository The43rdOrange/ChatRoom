package Menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import java.util.List;

public class MenuOptionTest {
    @Test
    public void EnsureMenuOptionHasExactlyThreeFunctionsTestWithCorrectNames(){
        MenuOption menuOption = new MenuOption() {
            @Override
            public String GetUserFriendlyDescription() {
                return "";
            }

            @Override
            public String GetMenuEntry() {
                return "";
            }

            @Override
            public void Execute() {

            }
        };

        List<Method> methods = List.of(menuOption.getClass().getDeclaredMethods());


        Assertions.assertEquals(3,methods.size());

        Assertions.assertEquals(1, methods.stream().filter(
                x -> x.getName().equals("GetUserFriendlyDescription")
        ).count());

        Assertions.assertEquals(1, methods.stream().filter(
                x -> x.getName().equals("Execute")
        ).count());

        Assertions.assertEquals(1, methods.stream().filter(
                x -> x.getName().equals("GetMenuEntry")
        ).count());

    }

}
