package traffic;

import java.io.IOException;
import java.util.Arrays;

public enum Command {
    ADD_ROAD("Add road", "1", "Road added"),
    DELETE_ROAD("Delete road", "2", "Road deleted"),
    OPEN_SYSTEM("Open system", "3", "System opened"),
    QUIT("Quit", "0", "Bye!"),
    UNKNOWN("Unknown", "", "Incorrect option"),
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

        Arrays.stream(Command.values())
                .filter(command -> !command.equals(UNKNOWN))
                .forEach(command ->
                        sb.append(command.getCommandInput()).append(". ").append(command.getName()).append("\n"));

        return sb.toString().trim();
    }

    public static Command getCommand(String commandInput) {
        return Arrays.stream(Command.values())
                .filter(command -> command.getCommandInput().equals(commandInput))
                .findFirst()
                .orElse(Command.UNKNOWN);
    }
}
