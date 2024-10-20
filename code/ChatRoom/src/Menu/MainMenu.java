package Menu;

import Main.Main;
import java.util.Scanner;
import User.User;


public enum MainMenu implements MenuOption {
    JoinChatRoom {
        @Override
        public void Execute() {
            System.out.println("Join Chat Room not yet implemented");
            User user = new User();
            user.JoinChatRoom();
            user.loop();
        }

        @Override
        public String GetUserFriendlyDescription() {
            return "Join Chat Room";
        }
    },
    ViewHistoricChats {
        @Override
        public String GetUserFriendlyDescription() {
            return "View old chats";
        }

        @Override
        public void Execute() {
            System.out.println("ViewHistoricChats - Not yet implemented");
        }
    },
    Quit {
        @Override
        public String GetUserFriendlyDescription() {
            return "Quit";
        }

        @Override
        public void Execute() {
            System.out.println("\nQuiting application");
            Main.quit = true;
        }
    };

    public static MainMenu GetUserSelectedEnum() {
        System.out.println("\nMain Menu");
        for (MainMenu menuOption : MainMenu.values()) {
            System.out.println(menuOption.GetMenuEntry());
        }
        int maxValue;
        int minValue;
        int userSelectedIndex = -1;
        boolean isValid;
        do {
            System.out.print("\nEnter the index of the item you'd like to select: ");
            maxValue = MainMenu.values().length;
            minValue = 1;
            Scanner scanner = new Scanner(System.in);

            try{
                userSelectedIndex = scanner.nextInt();
            } catch (Exception e){
                System.out.println("The input was not a number. Try again");
                isValid = false;
                continue;
            }

            isValid = maxValue >= userSelectedIndex && minValue <= userSelectedIndex;
            if (!isValid) {
                System.out.println("A value of " + userSelectedIndex + " was not valid");
            }

        } while (!isValid);
        return MainMenu.values()[userSelectedIndex-1];
    }

    public String GetMenuEntry() {
        return this.ordinal()+1 + ". " + this.GetUserFriendlyDescription();
    }
}
