package com.Vxrtrauter.NullWare.command.commands;

import com.Vxrtrauter.NullWare.command.Command;

import static com.Vxrtrauter.NullWare.client.MessageHandler.sendMessage;

public class NullwareCommand implements Command {

    @Override
    public String getName() {
        return "nullware";
    }

    @Override
    public String getDescription() {
        return "Displays information about NullWare.";
    }

    @Override
    public void execute(String[] args) {
        sendMessage("NullWare v1.0 - Created by Vxrtrauter");
    }
}
