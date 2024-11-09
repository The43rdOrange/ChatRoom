package User;

import Message.Message;
import Threads.LoopingThread;
import Threads.NewThreading;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class User {
    final AtomicReference<String> username = new AtomicReference<>();
    final List<Message> inbox = new ArrayList<>();
    final List<Message> outbox = new ArrayList<>();
    final GetUserInputCallable getUserInputCallable = new GetUserInputCallable(new Scanner(System.in));

    UserConnectionToHost userConnectionToHost;

    final Thread outboxThread = new Thread(new Thread(() -> {
        System.out.println("outboxThread is running");
        synchronized (outbox) {
            while (!outbox.isEmpty()) {
                userConnectionToHost.addToOutbox(outbox.removeFirst());
            }
        }
    }));

    public User() {
        super();
        //temp username that will almost certainly be unique for now

        username.set(LocalDateTime.now().toString());
    }

    /**
     * Clears the <code>outbox</code> in this instance and returns all the messages that were contained in <code>outbox</code>
     *
     * @return the messages that were removed from <code>outbox</code> during this function call
     */
    public List<Message> getMessagesFromOutbox() {
        synchronized (outbox) {
            List<Message> messages = outbox.stream().toList();
            outbox.clear();
            return messages;
        }
    }

    private void addMessageToOutbox(Message message) {
        synchronized (outbox) {
            outbox.add(message);
        }
    }


    public void loop() {

        List<LoopingThread> threads = new ArrayList<>();

        threads.add(new LoopingThread(outboxThread, true));

        while (isContinueRunning()) {

            //inbox thread
            processInboxMessages();
            processOutbox();
            //outboxThread.start();
            //to send and receive concurrently, a thread is needed to get user input
            try {
                getUserInputCallable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isContinueRunning() {
        return true;
    }

    /**
     * Processes all messages that are in the inbox. This includes removing the processed messages from the inbox
     */
    private void processInboxMessages() {
        Message msg;
        do {
            msg = removeFirstMessageFromInbox();
            if (msg != null) {
                System.out.println(msg.toDisplayableString());
            }
        } while (msg != null);
    }
    /**
     * Removes and returns the message at the front of inbox
     *
     * @return the first message stored in <code>inbox</code> or <code>null</code> when inbox is empty
     */
    private Message removeFirstMessageFromInbox() {
        Message msg = null;

        synchronized (inbox) {
            if (!inbox.isEmpty()) {
                msg = inbox.removeFirst();
            }
        }
        return msg;
    }
    private void processOutbox() {
        synchronized (outbox) {
            userConnectionToHost.addToOutbox(outbox.stream().toList().get(0));
        }
    }
    public void addToOutbox(Message msg) {
        synchronized (outbox) {
            outbox.add(msg);
        }
    }
    public void JoinChatRoom() {

    }
}

