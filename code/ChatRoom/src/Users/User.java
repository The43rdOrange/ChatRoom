package Users;

import Main.HostChatRoom;
import Networking.Message;

import java.security.InvalidParameterException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public interface User {
    static String GetValidUsernameFromConsole() {
        boolean usernameValid = false;
        String username;


        Scanner scanner = new Scanner(System.in);
        do{
            System.out.print("Username: ");
            username = scanner.nextLine();
            usernameValid = !username.isEmpty();

            if(!usernameValid){
                System.out.println("username not valid");
            }
        }while(!usernameValid);

        return username;
    }
    boolean IsPartOfChatRoom();
    boolean JoinChatRoom();
    void SendMessage(Message message);
    Runnable getReceivingThread();
    Runnable getSendingThread();
    void RunSendReceiveMessageThread();

    String getUsername();
}
