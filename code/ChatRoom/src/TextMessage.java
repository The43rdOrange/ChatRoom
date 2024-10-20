public class TextMessage implements Message {
    String username;
    String message;

    public TextMessage(String username, String message) {
        this.username = username;
        this.message = message;
    }

    @Override
    public String getSendersUsername() {
        return username;
    }

    @Override
    public String toDisplayableString() {
        return username + " says " + message;
    }
}
