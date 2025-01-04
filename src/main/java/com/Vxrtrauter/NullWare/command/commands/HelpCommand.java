package com.Vxrtrauter.NullWare.command.commands;

import com.Vxrtrauter.NullWare.command.Command;
import com.Vxrtrauter.NullWare.command.CommandManager;

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
