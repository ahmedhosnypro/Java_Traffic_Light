package traffic;

public class Main {
    private static final String WELCOME = "Welcome to the traffic management system!";

    public static void main(String[] args) {
        System.out.printf("""
                        %s
                        Menu:
                        %s
                        """,
                WELCOME,
                Command.getOrderedMenu());
    }
}
