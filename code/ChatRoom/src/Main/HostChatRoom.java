package Main;

import Networking.Message;
import Users.Host;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Contains information about how
 */
public class HostChatRoom extends ChatRoom{
    private ServerSocket serverSocket;

    private final Dictionary<Socket,String> memberSocketUsernameDictionary;

    public HostChatRoom() {
        memberSocketUsernameDictionary = new Hashtable<>();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void AddMessageToMessageLog(Message message){
        messageLog.add(message);
    }
    public void PrintMessageLog(){
        for (Message message : messageLog){
            System.out.println(message);
        }
    }
}
