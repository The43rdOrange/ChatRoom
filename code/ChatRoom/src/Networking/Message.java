package Networking;

import PersistantData.ToJSON;

public interface Message extends ToJSON {
    public String getMessageAsString();
    public String getSender();
}
