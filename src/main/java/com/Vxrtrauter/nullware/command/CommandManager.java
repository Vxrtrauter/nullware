package com.Vxrtrauter.nullware.command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private static final Map<String, Command> commands = new HashMap<>();
    private static final String PREFIX = ".";

    public static void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }

    public static boolean executeCommand(String input) {
        if (!input.startsWith(PREFIX)) {
            return false;
        }

        String[] args = input.substring(PREFIX.length()).split(" ");
        String commandName = args[0];
        Command command = commands.get(commandName);

        if (command != null) {
            command.execute(args);
            return true;
        } else {
            System.out.println("Unknown command: " + commandName);
            return false;
        }
    }

    public static void printHelp() {
        System.out.println("Available commands:");
        for (Command command : commands.values()) {
            System.out.println("- " + PREFIX + command.getName() + ": " + command.getDescription());
        }
    }
}
