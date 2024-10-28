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
    UserConnectionToHost userConnectionToHost;

    Thread inboxThread = new Thread(() -> {
        synchronized (inbox){
            Message msg;
            while (!inbox.isEmpty()) {
                msg = inbox.getFirst();
                inbox.remove(msg);
                System.out.println(msg.toDisplayableString());
            }
        }
    });
    Thread getUserInputThread = new Thread(() -> {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();


        synchronized (outbox) {
            outbox.add(new TextMessage(this.username.get(),s));
        }
    });
    Thread outboxThread = new Thread(() -> {
        synchronized (outbox) {
            while (!outbox.isEmpty()) {
                userConnectionToHost.addToOutbox(outbox.removeFirst());
            }
        }
    });

    public User() {
        //temp username that will almost certainly be unique for now
        username.set(LocalDateTime.now().toString());
    }

    public void loop() {
        boolean continueLoop = true;

        //start threads if they aren't already running
        while (continueLoop) {
            if (!inboxThread.isAlive()) {
                inboxThread.start();
            }

            if (!outboxThread.isAlive()) {
                outboxThread.start();
            }

            if (!getUserInputThread.isAlive()) {
                getUserInputThread.start();
            }
        }
    }

    public void JoinChatRoom() {

    }
}

