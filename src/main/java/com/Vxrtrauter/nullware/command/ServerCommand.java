package com.Vxrtrauter.nullware.command;

import net.minecraft.client.Minecraft;

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
        System.out.println("Current server: " + server);
    }
}
