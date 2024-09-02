package Menu;

import Main.*;
import java.util.Scanner;

import Users.*;

public enum MainMenu implements MenuOption {
    Host {
        @Override
        public void Execute() {
            Member host = new Member("Host");
            host.RunSendReceiveMessageThread();
        }

        @Override
        public String GetUserFriendlyDescription() {
            return "Host chat room";
        }
    },
    Member {
        @Override
        public void Execute() {

            //ChatRoom chatRoom = new MemberChatRoom();
        }

        @Override
        public String GetUserFriendlyDescription() {
            return "Join chat room";
        }
    },
    ViewHistoricChats {
        @Override
        public String GetUserFriendlyDescription() {
            return "View old chats";
        }

        @Override
        public void Execute() {
            System.out.println("Not yet implemented");
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
        int maxValue = MainMenu.values().length - 1;
        int minValue = 0;

        int userSelectedIndex;
        boolean isValid;
        do {
            System.out.print("\nEnter the index of the item you'd like to select: ");
            Scanner scanner = new Scanner(System.in);
            userSelectedIndex = scanner.nextInt();
            isValid = maxValue >= userSelectedIndex && minValue <= userSelectedIndex;


            if (!isValid) {
                System.out.println("A value of " + userSelectedIndex + " was not valid");
            }

        } while (!isValid);
        return MainMenu.values()[userSelectedIndex];
    }

    public String GetMenuEntry() {
        return this.ordinal() + ". " + this.GetUserFriendlyDescription();
    }
}
