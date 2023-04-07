package traffic;

import java.util.Arrays;

public enum Command {
    ADD_ROAD("Add road", "1", "Road added"),
    DELETE_ROAD("Delete road", "2", "Road deleted"),
    OPEN_SYSTEM("Open system", "3", "System opened"),
    QUIT("Quit", "0", "Bye!"),
    ;

    private final String name;
    private final String commandInput;
    private final String response;

    Command(String name, String commandInput, String response) {
        this.name = name;
        this.commandInput = commandInput;
        this.response = response;
    }

    public String getName() {
        return name;
    }

    public String getCommandInput() {
        return commandInput;
    }

    public String getResponse() {
        return response;
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("Menu:").append("\n");
        for (Command command : Command.values()) {
            sb.append(command.getCommandInput()).append(". ").append(command.getName()).append("\n");
        }
        return sb.toString().trim();
    }

    public static Command getCommand(String commandInput) {
        return Arrays.stream(Command.values())
                .filter(command -> command.getCommandInput().equals(commandInput))
                .findFirst()
                .orElse(Command.QUIT);
    }
}
