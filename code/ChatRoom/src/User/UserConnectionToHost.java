package User;

import Message.Message;

public interface UserConnectionToHost {
    public void addToOutbox(Message message);
}
