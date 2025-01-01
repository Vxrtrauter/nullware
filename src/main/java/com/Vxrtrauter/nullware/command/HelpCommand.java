package com.Vxrtrauter.nullware.command;

public class HelpCommand implements Command {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Displays a list of available commands.";
    }

    @Override
    public void execute(String[] args) {
        CommandManager.printHelp();
    }
}
