package Main;

import Networking.Message;
import Users.Host;
import Users.Member;
import Users.User;
import net.jcip.annotations.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public abstract class ChatRoom {

    final List<Message> outgoingMessages = new ArrayList<>();
    final List<Message> incomingMessages = new ArrayList<>();
    final List<Message> messageLog = new ArrayList<>();
    /**
     * Adds <code>message</code> to the end of <code>outgoingMessages</code>
     * @param message the message that gets added to <code>outgoingMessages</code>
     */
    @GuardedBy("incomingMessages")
    public final synchronized void addOutgoingMessage(Message message) {
        outgoingMessages.add(message);
    }
    /**
     * Adds <code>message</code> to <code>incomingMessages</code>
     * @param message the message that gets added to <code>incomingMessages</code>
     */
    @GuardedBy("incomingMessages")
    public final synchronized void addIncomingMessage(Message message) {
        incomingMessages.add(message);
    }

    /**
     *
     * @return <code>true</code> if this instance has messages to send. <code>false</code> otherwise
     */
    @GuardedBy("outgoingMessages")
    public final synchronized boolean HasMessagesToSend(){
            return !outgoingMessages.isEmpty();
    }

    /**
     *
     * @return <code>true</code> if there's incoming messages to process. <code>false</code> otherwise
     */
    @GuardedBy("incomingMessages")
    public final synchronized boolean HasIncomingMessagesToProcess(){
        return !incomingMessages.isEmpty();
    }

    /**
     * Processes all incoming messages in the chat room. Default implementation prints incoming messages to
     * the console and then removes the messages from <code>incomingMessages</code>
     */
    @GuardedBy("incomingMessages")
    public synchronized void ProcessIncomingMessages(){
        for (Message message : incomingMessages) {
                System.out.println(message.getMessageAsString());
                incomingMessages.remove(message);
            }
    }

    public void SendReceiveMessageLoop(Class<? extends User> clazz){

        String username = User.GetValidUsernameFromConsole();
        User user;

        if (clazz == Host.class){
            user = new Host(username, new HostChatRoom());
        }
        else if (clazz == Member.class){
            user = new Member(username);
        }
        else {
            throw new InvalidParameterException("Parameter not supported");
        }

        if(!user.JoinChatRoom()){
            System.out.println(username + " was unable to join the chat room");
            return;
        }

        System.out.println("You have joined the chat room.");

        try(ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2)){
            threadPool.scheduleWithFixedDelay(user.getReceivingThread(), 0,100, TimeUnit.MILLISECONDS);
            threadPool.scheduleWithFixedDelay(user.getSendingThread(), 0,100, TimeUnit.MILLISECONDS);

            //noinspection StatementWithEmptyBody
            while (user.IsPartOfChatRoom());

        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("idk something went wrong");
            System.err.println(illegalArgumentException.getMessage());
        }
    }
}
