package Threads;

public abstract class NewThreading {

    private boolean continueRunning;
    private Thread thread;

    public Thread getThread() {
        return thread;
    }

    protected NewThreading(Thread thread, boolean continueRunning) {
        this.continueRunning = continueRunning;
        this.thread = thread;
    }

    protected final boolean isContinueRunning() {
        return continueRunning;
    }

    protected final void setContinueRunning(boolean continueRunning) {
        this.continueRunning = continueRunning;
    }

    abstract void loop();
}