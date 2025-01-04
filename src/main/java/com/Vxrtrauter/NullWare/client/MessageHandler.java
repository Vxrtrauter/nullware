package com.Vxrtrauter.NullWare.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;


public class MessageHandler {
    public static String chatPrefix = "§c§lNullWare §7» §f";
    public static void sendMessage(String message) {
        if (Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(chatPrefix + message));
        }
    }
}
