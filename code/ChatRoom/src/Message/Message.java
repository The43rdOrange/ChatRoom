package Message;

public interface Message {
    static Message fromString(String s) {
        return new TextMessage("tempUsername",s);
    }

    public String getSendersUsername();
    public String toDisplayableString();
}
