import net.jcip.annotations.ThreadSafe;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class User {
    final AtomicReference<String> username = new AtomicReference<>();
    final List<Message> inbox = new ArrayList<>();
    final AtomicReference<String> mostRecentUserInput = new AtomicReference<>();
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


        synchronized (mostRecentUserInput) {
            //whilst the most recent user input has not been processed, wait

            //noinspection StatementWithEmptyBody
            while (!mostRecentUserInput.get().equals(s)) {}
            //update most recent user input
            mostRecentUserInput.set(s);
        }
    });
    Thread outboxThread = new Thread(() -> {
        synchronized (mostRecentUserInput) {
            if (!mostRecentUserInput.get().isEmpty()) {
                userConnectionToHost.addToOutbox(new TextMessage(username.get(), mostRecentUserInput.get()));
            }
        }
    });

    public User() {
        username.set(LocalDateTime.now().toString());
    }
}

