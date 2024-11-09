package User;

import java.util.Scanner;
import java.util.concurrent.Callable;

public class GetUserInputCallable implements Callable<String> {
    Scanner scanner;

    @Override
    public String call() throws Exception {
        return scanner.nextLine();
    }

    public GetUserInputCallable(Scanner scanner) {
        this.scanner = scanner;
    }
}
