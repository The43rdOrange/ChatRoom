package Menu;

public interface MenuOption {
    String GetUserFriendlyDescription();

    String GetMenuEntry();

    void Execute();
}
