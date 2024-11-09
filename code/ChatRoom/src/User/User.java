package User;

import Message.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import Message.TextMessage;

public class User {
    final AtomicReference<String> username = new AtomicReference<>();
    final List<Message> inbox = new ArrayList<>();
    final List<Message> outbox = new ArrayList<>();
    final Scanner scanner = new Scanner(System.in);

    UserConnectionToHost userConnectionToHost;

    Thread getInboxThread(){
        return new Thread(() -> {
            synchronized (inbox){
                Message msg;
                while (!inbox.isEmpty()) {
                    msg = inbox.getFirst();
                    inbox.remove(msg);
                    System.out.println(msg.toDisplayableString());
                }
            }
        });
    }
    Thread getUserInputThread(){
        return new Thread(() -> {
            //System.out.println("Awaiting user input");
            String s = scanner.nextLine();
            //System.out.println("User input was " + s);


            synchronized (outbox) {
                outbox.add(new TextMessage(this.username.get(),s));
            }
        });
    }
    Thread getOutboxThread(){
        return new Thread(() -> {
            synchronized (outbox) {
                while (!outbox.isEmpty()) {
                    userConnectionToHost.addToInbox(outbox.removeFirst());
                }
            }
        });
    }

    public User() {
        //temp username that will almost certainly be unique for now
        username.set(LocalDateTime.now().toString());
    }

    /**
     * The loop that contains all the logic for the user when it comes to sending/receiving data
     */
    public void loop() {
        boolean continueLoop = true;
        Thread getUserInput = null;
        Thread handleOutbox = null;
        Thread handleInbox = null;

        //In java, threads can only be started once. This leads to the bizarre code below
        //The below code, checks if the variable is null, if it is, the variable  will be
        // set. If the thread has finished executing, the thread will be set again as
        // threads can only be started once with the start function. The thread is then
        // started
        while (continueLoop) {

            if (getUserInput == null || !getUserInput.isAlive()) {
                getUserInput = getUserInputThread();
                getUserInput.start();
            }

            if (handleOutbox == null || !handleOutbox.isAlive()) {
                handleOutbox = getOutboxThread();
                handleOutbox.start();
            }

            if (handleInbox == null || !handleInbox.isAlive()) {
                handleInbox = getInboxThread();
                handleInbox.start();
            }
        }
    }

    public void JoinChatRoom() {

    }
}

