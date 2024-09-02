package Users;

import Main.*;
import Networking.HostJoinedChatRoomMessage;
import Networking.Message;
import Users.DataHandling.HostReceiving;
import Users.DataHandling.HostSending;

import java.net.ServerSocket;

public class Host implements User{
    private final Member member;
    private final HostChatRoom hostChatRoom;

    public Host(String username, HostChatRoom hostChatRoom) {
        this.member = new Member(username);
        this.hostChatRoom = new HostChatRoom();
    }

    public String getUsername() {
        return member.getUsername();
    }
    public void RunSendReceiveMessageLoop(){

    }
    public HostChatRoom getHostChatRoom() {
        return hostChatRoom;
    }

    @Override
    public boolean IsPartOfChatRoom() {
        boolean serverSocketIsNull = hostChatRoom.getServerSocket() == null;
        boolean serverSocketIsClosed = hostChatRoom.getServerSocket().isClosed();
        return !(serverSocketIsNull || serverSocketIsClosed);
    }

    @Override
    public boolean JoinChatRoom() {
        try{
            ServerSocket serverSocket = new ServerSocket(8888);
            serverSocket.setSoTimeout(0);
            hostChatRoom.setServerSocket(serverSocket);
            SendMessage(new HostJoinedChatRoomMessage(this));

            return true;
        } catch (Exception e){
            System.out.println("Unable to join the chat room");
        }
        return false;
    }

    @Override
    public void SendMessage(Message message) {
        hostChatRoom.addOutgoingMessage(message);
    }

    @Override
    public Runnable getReceivingThread() {
        return new HostReceiving();
    }

    @Override
    public Runnable getSendingThread() {
        return new HostSending();
    }

    @Override
    public void RunSendReceiveMessageThread() {
        getHostChatRoom().SendReceiveMessageLoop(Host.class);
    }
}
