package Menu;

import Main.Main;
import java.util.Scanner;

public enum MainMenu implements MenuOption {
    Host {
        @Override
        public void Execute() {
            System.out.println("Host not yet implemented");
            /*
            String username = User.GetValidUsernameFromConsole();

            Host host = new Host(username);
            host.JoinChatRoom();


            //wait for host to leave chat room
            do {
                //host
            } while (host.isPartOfChatRoom());
            */
        }

        @Override
        public String GetUserFriendlyDescription() {
            return "Host chat room";
        }
    },
    Member {
        @Override
        public void Execute() {
            System.out.println("Not yet implemented");
            /*


            String username = User.GetValidUsernameFromConsole();

            Member member = new Member(username);
            member.JoinChatRoom();

            //wait for member to leave chat room
            do {

            } while (member.isPartOfChatRoom());
             */
        }

        @Override
        public String GetUserFriendlyDescription() {
            return "Join chat room as host";
        }
    },
    Member {
        @Override
        public String GetUserFriendlyDescription() {
            return "Join chat room as member";
        }

        @Override
        public void Execute() {
            System.out.println("Member not yet implemented");
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

            /*
            System.out.println("minValue = " + minValue);
            System.out.println("maxValue = " + maxValue);
            System.out.println("userSelectedIndex = " + userSelectedIndex);
            System.out.println("userSelectedIndex >= minValue :" + (userSelectedIndex >= minValue ? "True":"False"));
            System.out.println("userSelectedIndex <= maxValue :" + (userSelectedIndex <= maxValue ? "True":"False"));
            System.out.println("isValid: " + isValid);
            */

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
