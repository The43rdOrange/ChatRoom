package Threads;

public final class LoopingThread extends NewThreading{
    /**
     *
     * @param thread the thread that <code>this</code> instance wraps
     * @param continueRunning determines if the thread starts running just before the constructor returns or not
     */
    public LoopingThread(Thread thread, boolean continueRunning) {
        super(thread, continueRunning);

        if (continueRunning)
            loop();
    }

    /**
     * The <code>loop</code> function can be called an unlimited amount of times and will not change anything after the first call.
     * The loop will stop executing once <code>setContinueRunning(false)</code> is called
     */
    public void loop(){
        Thread t = new Thread(getThread());
        setContinueRunning(true);

        if(isContinueRunning())
            t.start();

        while(isContinueRunning()){
            if(!t.isAlive()){
                t = new Thread(getThread());
                t.start();
            }
        }
    }
}