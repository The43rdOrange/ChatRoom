package Main;

import Menu.MainMenu;

public class Main {
    public static boolean quit = false;

    public static void main(String[] args) {

        System.out.println("Welcome to the chat room application");
        do {
            MainMenu selectedItem = MainMenu.GetUserSelectedEnum();
            selectedItem.Execute();

        } while (!quit);
    }

}