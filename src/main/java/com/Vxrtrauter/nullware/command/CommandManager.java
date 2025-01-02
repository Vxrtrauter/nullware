package com.Vxrtrauter.nullware.command;

import java.util.HashMap;
import java.util.Map;

import static com.Vxrtrauter.nullware.client.MessageHandler.sendMessage;

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
            sendMessage("Unknown command: " + commandName);
            return true;
        }
    }

    public static void printHelp() {
        sendMessage("Available commands:");
        for (Command command : commands.values()) {
            sendMessage(PREFIX + command.getName() + " §7-§f " + command.getDescription());
        }
    }


}
