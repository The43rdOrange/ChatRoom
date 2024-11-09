package User;

import Message.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class UserConnectionToHost {

    final public List<Message> outbox = new ArrayList<Message>();
    private boolean sendReceiveEnabled;

    public void addToOutbox(Message message){
        synchronized(outbox){
            outbox.add(message);
        }
    }
    public void addRangeToOutbox(Collection<Message> message){
        synchronized(outbox){
            outbox.addAll(message);
        }
    }
    public boolean sendReceiveEnabled(){
        return sendReceiveEnabled;
    }
}
