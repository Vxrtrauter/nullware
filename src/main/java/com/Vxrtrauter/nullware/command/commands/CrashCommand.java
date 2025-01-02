package com.Vxrtrauter.nullware.command.commands;

import com.Vxrtrauter.nullware.command.Command;

public class CrashCommand implements Command {

    @Override
    public String getName() {
        return "crash";
    }

    @Override
    public String getDescription() {
        return "Crashes Servers by Abusing different Packet-Exploits.";
    }

    @Override
    public void execute(String[] args) {
    } // add different crashers (netty, onepacket, old smogclient leaks?)

}
