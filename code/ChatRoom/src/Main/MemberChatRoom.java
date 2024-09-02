package Main;

import Networking.Message;
import Users.Host;
import Users.Member;
import Users.User;

import java.net.Socket;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MemberChatRoom extends ChatRoom {
    private final List<Message> messageLog = new ArrayList<Message>();
    private Socket socketToHost;

    public MemberChatRoom(Socket socketToHost) {
        this.socketToHost = socketToHost;
    }
}
