package Threads;

public abstract class NewThreading {

    private final Thread thread;
    private boolean continueRunning;

    protected NewThreading(Thread thread, boolean continueRunning) {
        this.thread = thread;
        this.continueRunning = continueRunning;
    }

    protected final boolean isContinueRunning() {
        return continueRunning;
    }

    protected final void setContinueRunning(boolean continueRunning) {
        this.continueRunning = continueRunning;
    }

    protected abstract void loop();
}