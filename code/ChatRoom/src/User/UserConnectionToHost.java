package User;

import Message.Message;

public interface UserConnectionToHost {
    void addToInbox(Message message);
}