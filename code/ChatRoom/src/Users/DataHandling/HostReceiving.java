package Users.DataHandling;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HostReceiving implements Receiving {
    @Override
    public void run() {
        System.out.println("Host Receiving");
    }
}
