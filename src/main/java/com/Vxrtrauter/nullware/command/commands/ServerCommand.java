package com.Vxrtrauter.nullware.command.commands;

import com.Vxrtrauter.nullware.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import static com.Vxrtrauter.nullware.client.MessageHandler.sendMessage;

// add functions to gather specific information from server e.g. plugins using different methods
public class ServerCommand implements Command {

    @Override
    public String getName() {
        return "server";
    }

    @Override
    public String getDescription() {
        return "Displays the current server address.";
    }

    @Override
    public void execute(String[] args) {
        String server = Minecraft.getMinecraft().getCurrentServerData() != null
                ? Minecraft.getMinecraft().getCurrentServerData().serverIP
                : "Singleplayer";
        sendMessage("Current server: " + server);
    }
}
