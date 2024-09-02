package Users;

import Main.MemberChatRoom;
import Networking.Message;
import Users.DataHandling.MemberRecieving;
import Users.DataHandling.MemberSending;

public class Member implements User{
    private String userName;
    private MemberChatRoom memberChatRoom;

    public Member(String username) {
        this.userName = username;
        this.memberChatRoom = new MemberChatRoom();
    }

    @Override
    public boolean IsPartOfChatRoom() {
        return false;
    }

    @Override
    public boolean JoinChatRoom() {
        return false;
    }

    @Override
    public void SendMessage(Message message) {

    }

    @Override
    public Runnable getReceivingThread() {
        return new MemberRecieving();
    }

    @Override
    public Runnable getSendingThread() {
        return new MemberSending();
    }

    @Override
    public void RunSendReceiveMessageThread() {
        memberChatRoom.SendReceiveMessageLoop(Member.class);
    }

    @Override
    public String getUsername() {
        return userName;
    }
}
