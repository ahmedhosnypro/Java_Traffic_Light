package traffic;

public enum Command {
    ADD("Add", 1),
    DELETE("Delete", 2),
    SYSTEM("System", 3),
    QUIT("Quit", 0),
    ;

    private final String name;
    private final int argValue;

    Command(String name, int argValue) {
        this.name = name;
        this.argValue = argValue;
    }

    public String getName() {
        return name;
    }

    public int getArgValue() {
        return argValue;
    }

    public static String getOrderedMenu() {
        StringBuilder sb = new StringBuilder();
        for (Command command : Command.values()) {
            sb.append(command.getArgValue()).append(". ").append(command.getName()).append("\n");
        }
        return sb.toString().trim();
    }
}
