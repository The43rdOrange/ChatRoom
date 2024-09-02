package Networking;

import PersistantData.ToJSON;
import Users.Host;

public class HostJoinedChatRoomMessage implements Message{
    private final String sender;

    public HostJoinedChatRoomMessage(Host host) {
        sender = host.getUsername();
    }

    @Override
    public String getMessageAsString() {
        return getSender() + " joined the chat room as the host!";
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public String toJSON() {
        return
                "HostJoinedChatRoomMessage{" +
                        "{\"sender\":\"" + sender + "\",\"message\":\"" + getMessageAsString() + "\"}" +
                    "}";
    }
}
